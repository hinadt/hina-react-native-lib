
#import "HNReactNativeRootViewManager.h"
#import <React/RCTUIManager.h>

@interface HNReactNativeRootViewManager ()

@property (nonatomic, strong) NSPointerArray *rootViews;
@property (nonatomic, strong) NSMutableDictionary<NSNumber *, NSMutableSet *> *viewProperties;

@end

@implementation HNReactNativeRootViewManager

+ (instancetype)sharedInstance {
    static HNReactNativeRootViewManager *manager;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        manager = [[HNReactNativeRootViewManager alloc] init];
    });
    return manager;
}

- (instancetype)init {
    self = [super init];
    if (self) {
        _rootViews = [NSPointerArray weakObjectsPointerArray];
        _viewProperties = [NSMutableDictionary dictionary];
    }
    return self;
}

#pragma mark - rootView

- (void)addRootView:(RCTRootView *)rootView {
    [self compact];
    [self.rootViews addPointer:(__bridge void * _Nullable)(rootView)];
}

- (RCTRootView *)currentRootView {
    NSArray<RCTRootView *> *rootViews = self.rootViews.allObjects;
    // 倒序遍历, 获取最上层正在显示的 RCTRootView
    for (RCTRootView *rootView in rootViews.reverseObjectEnumerator) {
        BOOL isVisible = rootView.alpha > 0.01 && !rootView.isHidden;
        if (isVisible && rootView.window) {
            return rootView;
        }
    }
    // RCTRootView 初始化后, RN 插件就会触发页面浏览事件, 但此时 RCTRootView 可能还未显示到页面上
    // 所以此处取最后一个 RCTRootView 作为兼容逻辑
    return rootViews.lastObject;
}

#pragma mark - viewProperties

- (void)addViewProperty:(SAReactNativeViewProperty *)property withRootTag:(NSNumber *)rootTag {
    if (!property || !rootTag) {
        return;
    }

    NSMutableSet *viewProperties = self.viewProperties[rootTag];
    if (!viewProperties) {
        viewProperties = [NSMutableSet set];
        self.viewProperties[rootTag] = viewProperties;
    }
    [viewProperties addObject:property];
}

- (NSSet<SAReactNativeViewProperty *> *)viewPropertiesWithRootTag:(NSNumber *)rootTag {
    return [self.viewProperties[rootTag] copy];
}

#pragma mark - utils

- (void)compact {
    // NSPointerArray 内部有个标记 needsCompaction, 当 needsCompaction 为 true 时, compact 才会生效
    // 向 NSPointerArray 添加一个正常元素, 该元素在后面被释放时, needsCompaction 不会变化
    // 向 NSPointerArray 主动添加 NULL 时, needsCompaction 标记会被设置为 true
    // 所以调用 compact 前需要先添加一个 NULL
    // https://stackoverflow.com/questions/31322290/nspointerarray-weird-compaction
    [self.rootViews addPointer:NULL];
    [self.rootViews compact];

    // 清除没有 RCTRootView 的 viewProperty
    NSMutableSet *rootTags = [NSMutableSet set];
    for (RCTRootView *rootView in self.rootViews) {
        [rootTags addObject:rootView.reactTag];
    }

    NSMutableSet *removeTags = [NSMutableSet setWithArray:[self.viewProperties allKeys]];
    [removeTags minusSet:rootTags];
    [self.viewProperties removeObjectsForKeys:removeTags.allObjects];
}

@end
