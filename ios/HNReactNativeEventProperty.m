
#if ! __has_feature(objc_arc)
#error This file must be compiled with ARC. Either turn on ARC for the project or use -fobjc-arc flag on this file.
#endif

#import "HNReactNativeEventProperty.h"
#import "RNHinaReactNativeLib.h"

NSString *const kSALibPluginVersionKey = @"$lib_plugin_version";

@implementation HNReactNativeEventProperty

+ (NSDictionary *)eventProperties:(NSDictionary *)properties {
    return [self eventProperties:properties isAuto:NO];
}

+ (NSDictionary *)eventProperties:(NSDictionary *)properties isAuto:(BOOL)isAuto {
    NSMutableDictionary *result = [NSMutableDictionary dictionary];
    [result addEntriesFromDictionary:properties];
    NSString *libMethod = @"code";
    if (isAuto || [result[@"$lib_method"] isEqualToString:@"autoTrack"]) {
        // 当自定义属性中设置的 $lib_method 为 autoTrack 时有效，其他内容时 $lib_method 为 code
        // isAuto：当触发事件为全埋点时，$lib_method 为 autoTrack 且不可修改
        libMethod = @"autoTrack";
    }
    result[@"$lib_method"] = libMethod;
    if (result[kSALibPluginVersionKey]) {
        return result;
    }
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        result[kSALibPluginVersionKey] = @[kSAReactNativePluginVersion];
    });
    return result;
}

@end
