

#import "HNReactNativeSwizzler.h"
#import <objc/runtime.h>

@implementation NSObject (HNReactNativeSwizzler)

+ (BOOL)sa_reactnative_swizzle:(SEL)originalSelector withSelector:(SEL)destinationSelector {
    Method origMethod = class_getInstanceMethod(self, originalSelector);
    if (!origMethod) {
        return NO;
    }

    Method altMethod = class_getInstanceMethod(self, destinationSelector);
    if (!altMethod) {
        return NO;
    }

    class_addMethod(self,
                    originalSelector,
                    class_getMethodImplementation(self, originalSelector),
                    method_getTypeEncoding(origMethod));
    class_addMethod(self,
                    destinationSelector,
                    class_getMethodImplementation(self, destinationSelector),
                    method_getTypeEncoding(altMethod));

    method_exchangeImplementations(class_getInstanceMethod(self, originalSelector), class_getInstanceMethod(self, destinationSelector));
    return YES;
}

@end
