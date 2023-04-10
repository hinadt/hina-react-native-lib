
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSObject (HNReactNativeSwizzler)

+ (BOOL)sa_reactnative_swizzle:(SEL)originalSelector withSelector:(SEL)destinationSelector;

@end


NS_ASSUME_NONNULL_END
