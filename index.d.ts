/**
 * 初始化 SDK
 *
 * @param config 初始化配置，支持参数可参考{link https://manual.sensorsdata.cn/sa/latest/react-native-1574001.html#id-.ReactNativev1.13-%E5%88%9D%E5%A7%8B%E5%8C%96SDK}
 */
export function init(config: Partial<Config>): void
/**
 * 追踪事件
 *
 * @param event 事件名称
 * @param properties 事件属性
 */
export function track(event: string, properties?: PropertiesObjectType): void;

/**
 * 事件开始
 *
 * @param event 事件名称
 */
export function trackTimerStart(event: string): void;

/**
 * 事件结束
 *
 * @param event 事件名称
 * @param properties 事件属性
 */
export function trackTimerEnd(event: string, properties?: PropertiesObjectType): void;

/**
 * 登录
 *
 * @param userId 业务用户id，类型 String
 */
export function setUserUId(userId: string): void;

/**
 * 退出登录
 */
export function cleanUserUId(): void;

/**
 * 设置用户属性
 *
 * @param profile 用户属性
 */
export function userSet(profile: PropertiesObjectType): void;

/**
 * 固定初始值类型的属性
 *
 * @param profile 用户属性
 */
export function userSetOnce(profile: PropertiesObjectType): void;

/**
 * 数值类型的属性
 *
 * @param profile 用户属性
 */
export function userAdd(profile: PropertiesObjectType): void;

/**
 * 集合类型的属性
 *
 * @param property 属性名称
 * @param strList 属性值
 */
export function userAppend(property: string, strList: Array<string>): void;

/**
 * 删除用户的一个 Profile.
 *
 * @param property 属性名称
 */
export function userUnset(property: string): void;

/**
 * 删除用户所有 Profile.
 */
export function userDelete(): void;

/**
 * 强制发送数据到服务端
 */
export function flush(): void;

/**
 * 删除本地数据库的所有数据！！！请谨慎使用
 */
export function clear(): void;

/**
/**
 * 替换“匿名 ID”
 *
 * @param deviceUId 传入的的匿名 ID，仅接受数字、下划线和大小写字母
 */
export function setDeviceUId(deviceUId: string): void;

/**
 * Promise 方式 getDeviceUId 获取匿名 ID.
 */
export function getDeviceUId(): Promise<string>;

/**
 * 保存用户推送 ID 到用户表
 *
 * @param pushTypeKey 属性名称（例如 jgId）
 * @param pushId 推送 ID
 *     <p>使用 profilePushId("jgId", pushId) 例如极光 pushId
 *     获取方式：JPushModule.getRegistrationID(callback)
 */
export function setPushUId(pushTypeKey: string, pushId: string): void;

/**
 * 设置当前 serverUrl
 *
 * @param serverUrl 当前 serverUrl
 */
export function setServerUrl(serverUrl: string): void;

/**
 * 设置 flush 时网络发送策略，默认 3G、4G、WI-FI 环境下都会尝试 flush
 * TYPE_NONE = 0;//NULL
 * TYPE_2G = 1;//2G
 * TYPE_3G = 1 << 1;//3G 2
 * TYPE_4G = 1 << 2;//4G 4
 * TYPE_WIFI = 1 << 3;//WIFI 8
 * TYPE_5G = 1 << 4;//5G 16
 * TYPE_ALL = 0xFF;//ALL 255
 * 例：若需要开启 4G 5G 发送数据，则需要设置 4 + 16 = 20
 */

export function setFlushNetworkPolicy(networkType: number): void;

/**
 * 设置是否允许请求网络，默认是 true
 *
 * @platform Android
 *
 * @param isRequest boolean
 */
export function enableNetworkRequest(isRequest: boolean): void;

/**
 * 返回预置属性
 */
export function getPresetProperties(): Promise<PropertiesObjectType>;

/**
   * 设置公共属性
   *
   * @param properties 类型 {}
   */
export function registerCommonProperties(properties?: PropertiesObjectType): void;