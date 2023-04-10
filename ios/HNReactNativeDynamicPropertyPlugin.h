
#import <Foundation/Foundation.h>
#if __has_include(<HinaCloudSDK/HinaCloudSDK.h>)
#import <HinaCloudSDK/HinaCloudSDK.h>
#else
#import "HinaCloudSDK.h"
#endif

NS_ASSUME_NONNULL_BEGIN

@interface HNReactNativeDynamicPropertyPlugin : SAPropertyPlugin

@property (atomic, copy) NSDictionary *properties;

@end

NS_ASSUME_NONNULL_END
