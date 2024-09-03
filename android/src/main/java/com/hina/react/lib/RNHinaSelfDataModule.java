package com.hina.react.lib;


import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.hina.analytics.common.utils.LogUtils;
import com.hina.analytics.HinaCloudSDK;
import com.hina.react.lib.property.LibMethodInterceptor;
import com.hina.react.lib.property.PluginVersionInterceptor;
import com.hina.react.lib.property.RNPropertyManager;
import com.hina.react.lib.utils.RNUtils;
import com.hina.react.lib.utils.RNViewUtils;

import org.json.JSONObject;


/**
 * 参数类型在@ReactMethod注明的方法中，会被直接映射到它们对应的JavaScript类型
 * String -> String
 * ReadableMap -> Object
 * Boolean -> Bool
 * Integer -> Number
 * Double -> Number
 * Float -> Number
 * Callback -> function
 * ReadableArray -> Array
 */

public class RNHinaSelfDataModule extends ReactContextBaseJavaModule {

    public RNHinaSelfDataModule(ReactApplicationContext reactContext) {
        super(reactContext);
        try {
            reactContext.addLifecycleEventListener(new HinaDataLifecycleListener());
        } catch (Exception e) {

        }
        RNAgent.ignoreView();
        RNPropertyManager.addInterceptor(new PluginVersionInterceptor());
        RNPropertyManager.addInterceptor(new LibMethodInterceptor());
    }

    private static final String MODULE_NAME = "RNHinaSelfDataModule";
    private static final String TAG = "HA.RNHinaSelfDataModule";

    /**
     * 返回一个字符串名字，这个名字在 JavaScript (RN)端标记这个模块。
     */
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void trackViewClick(int viewId) {
        RNAgent.trackViewClick(viewId);
    }

    @ReactMethod
    public void trackViewScreen(ReadableMap params) {
        try {
            if (params != null) {
                JSONObject jsonParams = RNUtils.convertToJSONObject(params);
                JSONObject properties = null;
                if (jsonParams.has("param_data")) {
                    properties = jsonParams.optJSONObject("param_data");
                }
                String url = null;
                if (jsonParams.has("param_url")) {
                    url = jsonParams.getString("param_url");
                }
                if (url == null) {
                    return;
                }
                RNAgent.trackViewScreen(url, properties, true);
            }
        } catch (Exception e) {
            LogUtils.printStackTrace(e);
        }
    }

    @ReactMethod
    public void saveViewProperties(int viewId, boolean clickable, ReadableMap viewProperties) {
        RNAgent.saveViewProperties(viewId, clickable, viewProperties);
    }

    @ReactMethod
    public void setDynamicSuperProperties(ReadableMap dynamicSuperProperties) {
        RNAgent.setDynamicSuperProperties(RNUtils.convertToJSONObject(dynamicSuperProperties));
    }

    @ReactMethod
    public void registerDynamicPlugin() {

    }

    class HinaDataLifecycleListener implements LifecycleEventListener {
        public void onHostResume() {
            RNViewUtils.onActivityResumed(getCurrentActivity());
        }

        public void onHostPause() {
            RNViewUtils.onActivityPaused();
        }

        public void onHostDestroy() {

        }
    }
}
