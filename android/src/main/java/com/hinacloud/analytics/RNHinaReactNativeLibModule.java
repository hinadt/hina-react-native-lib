
package com.hinacloud.analytics;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNHinaReactNativeLibModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNHinaReactNativeLibModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNHinaReactNativeLib";
  }
}