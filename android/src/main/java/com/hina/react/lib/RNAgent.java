
package com.hina.react.lib;

import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.hina.analytics.common.utils.LogUtils;
import com.hina.analytics.HinaCloudSDK;
import com.hina.analytics.IAutoTrackEventType;
import com.hina.react.lib.data.SAViewProperties;
import com.hina.react.lib.property.RNPropertyManager;
import com.hina.react.lib.utils.RNTouchTargetHelper;
import com.hina.react.lib.utils.RNUtils;
import com.hina.react.lib.utils.RNViewUtils;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.WeakHashMap;

public class RNAgent {
    private static final WeakHashMap jsTouchDispatcherViewGroupWeakHashMap = new WeakHashMap();
    private static SparseArray<SAViewProperties> viewPropertiesArray = new SparseArray();
    private static JSONObject mDynamicSuperProperties;

    public static void handleTouchEvent(
            JSTouchDispatcher jsTouchDispatcher, MotionEvent event, EventDispatcher eventDispatcher) {
        try {
            if (event.getAction() == MotionEvent.ACTION_DOWN) { // ActionDown
                ViewGroup viewGroup = (ViewGroup) jsTouchDispatcherViewGroupWeakHashMap.get(jsTouchDispatcher);
                if (viewGroup == null) {
                    try {
                        Field viewGroupField = jsTouchDispatcher.getClass().getDeclaredField("mRootViewGroup");
                        viewGroupField.setAccessible(true);
                        viewGroup = (ViewGroup) viewGroupField.get(jsTouchDispatcher);
                        jsTouchDispatcherViewGroupWeakHashMap.put(jsTouchDispatcher, viewGroup);
                    } catch (Exception e) {
                        LogUtils.printStackTrace(e);
                    }
                }
                if (viewGroup != null) {
                    View nativeTargetView =
                            RNTouchTargetHelper.findTouchTargetView(
                                    new float[]{event.getX(), event.getY()}, viewGroup);
                    if (nativeTargetView != null) {
                        View reactTargetView = RNTouchTargetHelper.findClosestReactAncestor(nativeTargetView);
                        if (reactTargetView != null) {
                            nativeTargetView = reactTargetView;
                        }
                    }
                    if (nativeTargetView != null) {
                        RNViewUtils.setOnTouchView(nativeTargetView);
                    }
                }
            }
        } catch (Exception ignored) {

        }
    }

    static void trackViewScreen(String url, JSONObject properties, boolean isAuto) {
        try {
            String screenName = url;
            if (properties == null) {
                properties = new JSONObject();
            }
            if (properties.has("H_screen_name")) {
                screenName = properties.getString("H_screen_name");
            }
            String title = screenName;
            if (properties.has("H_title")) {
                title = properties.getString("H_title");
            }
            if (screenName != null) {
                properties.put("H_screen_name", screenName);
            }
            if (title != null) {
                properties.put("H_title", title);
            }
            RNViewUtils.saveScreenAndTitle(screenName, title);
            if (isAuto && (properties.optBoolean("SAIgnoreViewScreen", false)
                    || !HinaCloudSDK.getInstance().isAutoTrackEnabled()
                    || HinaCloudSDK.getInstance().isAutoTrackEventTypeIgnored(IAutoTrackEventType.APP_VIEW_SCREEN))) {
                return;
            }
            HinaCloudSDK.getInstance().trackViewScreen(url, RNPropertyManager.mergeProperty(properties, isAuto));
        } catch (Exception e) {
            LogUtils.printStackTrace(e);
        }
    }

    static void trackViewClick(int viewId) {
        try {
            //关闭 AutoTrack
            if (!HinaCloudSDK.getInstance().isAutoTrackEnabled()) {
                return;
            }
            //H_AppClick 被过滤
            if (HinaCloudSDK.getInstance().isAutoTrackEventTypeIgnored(IAutoTrackEventType.APP_CLICK)) {
                return;
            }
            View clickView = RNViewUtils.getViewByTag(viewId);
            if (clickView != null) {
                JSONObject properties = new JSONObject();
                if (RNViewUtils.getTitle() != null) {
                    properties.put("H_title", RNViewUtils.getTitle());
                }
                if (RNViewUtils.getScreenName() != null) {
                    properties.put("H_screen_name", RNViewUtils.getScreenName());
                }
                SAViewProperties viewProperties = viewPropertiesArray.get(viewId);
                if (viewProperties != null && viewProperties.properties != null && viewProperties.properties.length() > 0) {
                    if (viewProperties.properties.optBoolean("ignore", false)) {
                        return;
                    }
                    viewProperties.properties.remove("ignore");
                    RNUtils.mergeJSONObject(viewProperties.properties, properties);
                }
                HinaCloudSDK.getInstance().trackViewAppClick(clickView, RNPropertyManager.mergeProperty(properties, true));
            }
        } catch (Exception e) {
            LogUtils.printStackTrace(e);
        }
    }

    static void saveViewProperties(int viewId, boolean clickable, ReadableMap viewProperties) {
        if (clickable) {
            viewPropertiesArray.put(viewId, new SAViewProperties(clickable, viewProperties));
        }
    }

    /**
     * 添加 View 调用,Android 插件调用,勿删
     *
     * @param view View
     * @param index index
     */
    public static void addView(View view, int index) {
        SAViewProperties properties = viewPropertiesArray.get(view.getId());
        if (properties != null) {
            properties.setViewClickable(view);
            properties.setViewTag(view);
        }
    }

    /**
     * 忽略 Slider、Switch Android SDK 的采集逻辑，统一通过 Recat Native 采集
     */
    static void ignoreView() {
        try {
            HinaCloudSDK.getInstance().ignoreViewType(Class.forName("com.facebook.react.views.switchview.ReactSwitch"));
        } catch (Exception e) {
            //ignored
        }
        try {
            HinaCloudSDK.getInstance().ignoreViewType(Class.forName("com.facebook.react.views.slider.ReactSlider"));
        } catch (Exception e) {
            //ignored
        }
        try {
            HinaCloudSDK.getInstance().ignoreViewType(Class.forName("com.reactnativecommunity.slider.ReactSlider"));
        } catch (Exception e) {
            //ignored
        }
    }

    public static JSONObject getDynamicSuperProperties() {
        return mDynamicSuperProperties;
    }

    static void setDynamicSuperProperties(JSONObject dynamicSuperProperties) {
        mDynamicSuperProperties = dynamicSuperProperties;
    }
}
