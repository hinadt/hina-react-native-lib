
#import "HNReactNativeDynamicPropertyPlugin.h"

@implementation HNReactNativeDynamicPropertyPlugin

- (BOOL)isMatchedWithFilter:(id<HNPropertyPluginEventFilter>)filter {
    
    return filter.type & HNEventTypeDefault;
}

- (HNPropertyPluginPriority)priority {
    return HNPropertyPluginPriorityLow;
}

@end
