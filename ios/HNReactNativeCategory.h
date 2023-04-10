
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

#if __has_include(<React/RCTRootView.h>)
#import <React/RCTRootView.h>
#else
#import "RCTRootView.h"
#endif

NS_ASSUME_NONNULL_BEGIN

#pragma mark - View Property
@interface SAReactNativeViewProperty : NSObject <NSCopying>

/// View 唯一标识符
@property (nonatomic, strong) NSNumber *reactTag;
/// View 可点击状态
@property (nonatomic, assign) BOOL clickable;
/// View 自定义属性
@property (nonatomic, copy) NSDictionary *properties;

@end

@interface UIView (SAReactNative)

/// 用于记录 view 关联的页面信息
@property (nonatomic, copy) NSDictionary *sa_reactnative_screenProperties;

@end

@interface RCTRootView (SAReactNative)

@end

NS_ASSUME_NONNULL_END
