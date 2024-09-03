package com.hina.react.lib.property;

import com.hina.react.lib.property.RNPropertyManager.Interceptor;

import org.json.JSONObject;

public class LibMethodInterceptor implements Interceptor {

    public JSONObject proceed(JSONObject properties, boolean isAuto) {
        if (properties == null) {
            properties = new JSONObject();
        }
        try {
            if (!"autoTrack".equals(properties.optString("H_lib_method"))) {
                if (isAuto) {
                    properties.put("H_lib_method", "autoTrack");
                } else {
                    properties.put("H_lib_method", "code");
                }
            }
        } catch (Exception ignored) {

        }
        return properties;
    }
}
