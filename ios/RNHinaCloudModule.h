#import <Foundation/Foundation.h>

#if __has_include(<React/RCTBridgeModule.h>)
#import <React/RCTBridgeModule.h>
#else
#import "RCTBridgeModule.h"
#endif

NS_ASSUME_NONNULL_BEGIN

@interface RNHinaCloudModule : NSObject <RCTBridgeModule>

@end

NS_ASSUME_NONNULL_END
