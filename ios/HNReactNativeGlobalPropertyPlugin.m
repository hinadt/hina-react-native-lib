
#if ! __has_feature(objc_arc)
#error This file must be compiled with ARC. Either turn on ARC for the project or use -fobjc-arc flag on this file.
#endif

#import "HNReactNativeGlobalPropertyPlugin.h"

@interface HNReactNativeGlobalPropertyPlugin()
@property (nonatomic, copy) NSDictionary<NSString *, id> *globleProperties;
@end

@implementation HNReactNativeGlobalPropertyPlugin

- (instancetype)initWithProperties:(NSDictionary *)properties {
    self = [super init];
    if (self) {
        self.globleProperties = properties;
    }
    return self;
}

- (BOOL)isMatchedWithFilter:(id<SAPropertyPluginEventFilter>)filter {
    // 支持 track、Signup、Bind、Unbind
    return filter.type & SAEventTypeDefault;
}

- (SAPropertyPluginPriority)priority {
    return SAPropertyPluginPriorityLow;
}

- (NSDictionary<NSString *,id> *)properties {
    return [self.globleProperties copy];
}

@end
