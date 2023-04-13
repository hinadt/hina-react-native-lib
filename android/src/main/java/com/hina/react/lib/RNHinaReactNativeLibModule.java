
package com.hina.react.lib;

import android.text.TextUtils;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.hinacloud.analytics.HinaCloudSDK;
import com.hinacloud.analytics.ICommonProperties;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class RNHinaReactNativeLibModule extends ReactContextBaseJavaModule {
    private static final String LOGTAG = "HA.RN";
    private final ReactApplicationContext reactContext;

    public RNHinaReactNativeLibModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNHinaReactNativeLib";
    }

    @ReactMethod
    public void init(ReadableMap config) {
        if (getCurrentActivity() == null) {
            return;
        }
        try {
            JSONObject configJson = RNHinaUtils.convertToJSONObject(config);
            HinaCloudSDK.Builder builder = new HinaCloudSDK.Builder();
            if (configJson != null) {
                String serverUrl = configJson.optString("serverUrl");
                if (!TextUtils.isEmpty(serverUrl)) {
                    builder.setServerUrl(serverUrl);
                }

                boolean enableLog = configJson.optBoolean("enableLog");
                if (enableLog) {
                    builder.enableLog(true);
                }

                int autoTrackTypePolicy = configJson.optInt("autoTrackTypePolicy", 0);
                if (autoTrackTypePolicy > 0) {
                    builder.setAutoTrackEventType(autoTrackTypePolicy);
                }

                boolean enableJSBridge = configJson.optBoolean("enableJSBridge", false);
                if (enableJSBridge) {
                    builder.enableJSBridge(true);
                }

                int flushPendSize = configJson.optInt("flushPendSize", 0);
                if (flushPendSize > 0) {
                    builder.setFlushPendSize(flushPendSize);
                }

                int flushInterval = configJson.optInt("flushInterval", 0);
                if (flushInterval > 0) {
                    builder.setFlushInterval(flushInterval);
                }

                int networkTypePolicy = configJson.optInt("networkTypePolicy", 0);
                if (networkTypePolicy > 0) {
                    builder.setNetworkTypePolicy(networkTypePolicy);
                }

                long maxCacheSize = configJson.optLong("maxCacheSizeForAndroid", 0);
                if (maxCacheSize > 0) {
                    builder.setMaxCacheSize(maxCacheSize);
                }
            }
            builder.build(getCurrentActivity());
            Log.i(LOGTAG, "init success");
        } catch (Exception e) {
            Log.i(LOGTAG, "SDK init failed:" + e.getMessage());
        }
    }

    @ReactMethod
    public void track(String eventName, ReadableMap properties) {
        try {
            JSONObject propertiesJson = RNHinaUtils.convertToJSONObject(properties);
            HinaCloudSDK.getInstance().track(eventName, propertiesJson);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void trackTimerStart(String eventName) {
        try {
            HinaCloudSDK.getInstance().trackTimerStart(eventName);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void trackTimerEnd(String eventName, ReadableMap properties) {
        try {
            JSONObject jsonObject = RNHinaUtils.convertToJSONObject(properties);
            HinaCloudSDK.getInstance().trackTimerEnd(eventName, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void getPresetProperties(Promise promise) {
        if (promise == null) {
            return;
        }
        try {
            JSONObject properties = HinaCloudSDK.getInstance().getPresetProperties();
            WritableMap map = RNHinaUtils.convertToMap(properties);
            if (map != null) {
                promise.resolve(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
            promise.reject("getPresetProperties failed", e);
        }
    }

    @ReactMethod
    public void registerCommonProperties(ReadableMap properties) {
        try {
            HinaCloudSDK.getInstance().registerCommonProperties(new ICommonProperties() {
                @Override
                public JSONObject getCommonProperties() {
                    return RNHinaUtils.convertToJSONObject(properties);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void userSet(ReadableMap properties) {
        try {
            HinaCloudSDK.getInstance().userSet(RNHinaUtils.convertToJSONObject(properties));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void userSetOnce(ReadableMap properties) {
        try {
            HinaCloudSDK.getInstance().userSetOnce(RNHinaUtils.convertToJSONObject(properties));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void userAdd(ReadableMap properties) {
        try {
            JSONObject jsonObject = RNHinaUtils.convertToJSONObject(properties);
            if (jsonObject != null) {
                Iterator<String> keys = jsonObject.keys();
                Map<String, Number> map = new HashMap<>();
                while (keys.hasNext()) {
                    String key = keys.next();
                    Object value = jsonObject.opt(key);
                    map.put(key, (Number) value);
                }
                HinaCloudSDK.getInstance().userAdd(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void userAppend(String property, ReadableArray strList) {
        try {
            HashSet<String> strSet = new HashSet<>();
            for (int i = 0; i < strList.size(); i++) {
                strSet.add(strList.getString(i));
            }
            HinaCloudSDK.getInstance().userAppend(property, strSet);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void userUnset(String property) {
        try {
            HinaCloudSDK.getInstance().userUnset(property);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void userDelete() {
        try {
            HinaCloudSDK.getInstance().userDelete();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void flush() {
        try {
            HinaCloudSDK.getInstance().flush();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void clear() {
        try {
            HinaCloudSDK.getInstance().clear();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void setUserUId(String userUId) {
        try {
            HinaCloudSDK.getInstance().setUserUId(userUId);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void cleanUserUId() {
        try {
            HinaCloudSDK.getInstance().cleanUserUId();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void setDeviceUId(String deviceUId) {
        try {
            HinaCloudSDK.getInstance().setDeviceUId(deviceUId);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void getDeviceUId(Promise promise) {
        if (promise == null) {
            return;
        }
        try {
            promise.resolve(HinaCloudSDK.getInstance().getDeviceUId());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
            promise.reject("getDeviceUId failed", e);
        }
    }

    @ReactMethod
    public void setPushUId(String pushTypeKey, String pushId) {
        try {
            HinaCloudSDK.getInstance().setPushUId(pushTypeKey, pushId);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void setServerUrl(String serverUrl) {
        try {
            HinaCloudSDK.getInstance().setServerUrl(serverUrl);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void setFlushNetworkPolicy(int networkType) {
        try {
            HinaCloudSDK.getInstance().setFlushNetworkPolicy(networkType);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void enableNetworkRequest(boolean isRequest) {
        try {
            HinaCloudSDK.getInstance().enableNetworkRequest(isRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }
}