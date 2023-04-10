
#import <UIKit/UIKit.h>
#import "HNReactNativeCategory.h"

NS_ASSUME_NONNULL_BEGIN

@interface HNReactNativeRootViewManager : NSObject

#pragma mark - rootView

/// 防止在缓存 RCTRootView 时, 命中 SA 未初始化的断言
/// SA SDK 初始化前就创建 RCTRootView --> 加载 HNReactNativeManager --> 调用 - [HinaCloudSDK ignoreViewType:]
/// 所以此处使用单例, 不通过 HNReactNativeManager 去缓存 RCTRootView, 避免提前加载 HNReactNativeManager
+ (instancetype)sharedInstance;

/// 缓存 RCTRootView
/// @param rootView rootView
- (void)addRootView:(RCTRootView *)rootView;

/// 获取当前正在显示的 RCTRootView
- (RCTRootView *)currentRootView;

#pragma mark - viewProperties

/// 缓存 RN 页面中的元素信息
/// @param property 元素信息
/// @param rootTag 当前 RN 页面 RCTRootView 对应的 reactTag
- (void)addViewProperty:(SAReactNativeViewProperty *)property withRootTag:(NSNumber *)rootTag;

/// 获取 RN 页面中的元素信息
/// @param rootTag 当前 RN 页面 RCTRootView 对应的 reactTag
- (NSSet<SAReactNativeViewProperty *> *)viewPropertiesWithRootTag:(NSNumber *)rootTag;

@end

NS_ASSUME_NONNULL_END
