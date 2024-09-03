package com.hina.react.lib.property;

import com.hina.react.lib.RNHinaReactNativeLibPackage;
import com.hina.react.lib.property.RNPropertyManager.Interceptor;

import org.json.JSONArray;
import org.json.JSONObject;

public class PluginVersionInterceptor implements Interceptor {
    private static boolean isMergePluginVersion = false;

    public JSONObject proceed(JSONObject properties, boolean isAuto){
        if(!isMergePluginVersion){
            if(properties == null){
                properties = new JSONObject();
            }else if(properties.has("H_lib_plugin_version")){
                return properties;
            }
            try{
                JSONArray array = new JSONArray();
                array.put("react_native:" + RNHinaReactNativeLibPackage.VERSION);
                properties.put("H_lib_plugin_version",array);
            }catch (Exception ignored){
                //ignore
            }
            isMergePluginVersion = true;
        }
        return properties;
    }
}