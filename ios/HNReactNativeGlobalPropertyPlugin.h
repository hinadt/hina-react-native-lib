
#import <Foundation/Foundation.h>
#if __has_include(<HinaCloudSDK/HNPropertyPlugin.h>)
#import <HinaCloudSDK/HNPropertyPlugin.h>
#else
#import "HNPropertyPlugin.h"
#endif

NS_ASSUME_NONNULL_BEGIN

@interface HNReactNativeGlobalPropertyPlugin : HNPropertyPlugin

/// 全局属性插件
///
/// 全局属性，所有事件都会包含
///
/// @param properties 自定义属性
- (instancetype)initWithProperties:(NSDictionary *)properties NS_DESIGNATED_INITIALIZER;

- (instancetype)init NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END
