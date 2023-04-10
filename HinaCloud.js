import { NativeModules} from 'react-native';

const { RNHinaReactNativeLib } = NativeModules;

const AutoTrackType = {
  START : 1,
  END : 2,
  CLICK : 4,
  VIEW_SCREEN : 8
}

/**
 * 初始化
 *
 * @param config 初始化配置，类型 String
 */
function init(config){
    RNHinaReactNativeLib &&
      RNHinaReactNativeLib.init &&
      RNHinaReactNativeLib.init(config);
  }

/**
 * 追踪事件
 *
 * @param event 事件名称，类型 String
 * @param properties 事件属性，类型 {}
 */
function track (event, properties) {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.track &&
    RNHinaReactNativeLib.track(event, properties);
}

/**
 * 事件开始
 *
 * @param event 事件名称，类型 String
 */
function trackTimerStart (event) {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.trackTimerStart &&
    RNHinaReactNativeLib.trackTimerStart(event);
}

/**
 * 事件结束
 *
 * @param event 事件名称，类型 String
 * @param properties 事件属性，类型 {}
 */
function trackTimerEnd (event, properties) {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.trackTimerEnd &&
    RNHinaReactNativeLib.trackTimerEnd(event, properties);
}

/**
 * 登录
 *
 * @param loginId
 */
function setUserUId (loginId) {
    RNHinaReactNativeLib &&
      RNHinaReactNativeLib.setUserUId &&
      RNHinaReactNativeLib.setUserUId(loginId);
  }
  
  /**
   * 退出登录
   */
  function cleanUserUId () {
    RNHinaReactNativeLib &&
      RNHinaReactNativeLib.cleanUserUId &&
      RNHinaReactNativeLib.cleanUserUId();
  }
  
  /**
   * 设置用户属性
   *
   * @param profile 类型 {}
   * Sex
   * Age
   */
  function userSet (profile) {
    RNHinaReactNativeLib &&
      RNHinaReactNativeLib.userSet &&
      RNHinaReactNativeLib.userSet(profile);
  }
  
  /**
   * 记录初次设定的属性
   *
   * @param profile 类型 {}
   */
  function userSetOnce (profile) {
    RNHinaReactNativeLib &&
      RNHinaReactNativeLib.userSetOnce &&
      RNHinaReactNativeLib.userSetOnce(profile);
  }

/**
 * 给一个数值类型的 Profile 增加一个数值. 只能对数值型属性进行操作，若该属性
 * 未设置，则添加属性并设置默认值为 0.
 *
 * @param property 属性名称，类型 String
 * @param value 属性值，类型 Number
 */
function userAdd (property, value) {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.userAdd &&
    RNHinaReactNativeLib.userAdd(property, value);
}

/**
 * 给一个列表类型的 Profile 增加一个元素.
 *
 * @param property 属性名称，类型 String
 * @param strList 属性值，类型 []
 */
function userAppend (property, strList) {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.userAppend &&
    RNHinaReactNativeLib.userAppend(property, strList);
}

/**
 * 删除用户的一个 Profile.
 *
 * @param property 属性名称，类型 String
 */
function userUnset (property) {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.userUnset &&
    RNHinaReactNativeLib.userUnset(property);
}

/**
 * 删除用户所有 Profile.
 */
function userDelete () {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.userDelete &&
    RNHinaReactNativeLib.userDelete();
}

/**
 * 强制发送数据到服务端
 */
function flush () {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.flush &&
    RNHinaReactNativeLib.flush();
}

/**
 * 删除本地数据库的所有数据！！！请谨慎使用
 */
function clear () {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.clear &&
    RNHinaReactNativeLib.clear();
}

/**
 * 替换“匿名 ID”
 *
 * @param anonymousId 传入的的匿名 ID，仅接受数字、下划线和大小写字母，类型 String
 */
function setDeviceUId (anonymousId) {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.setDeviceUId &&
    RNHinaReactNativeLib.setDeviceUId(anonymousId);
}

/**
 * Promise 方式 getAnonymousId 获取匿名 ID.
 */
async function getDeviceUIdPromise () {
    if(RNHinaReactNativeLib && RNHinaReactNativeLib.getDeviceUIdPromise){
      try{
        return await RNHinaReactNativeLib.getDeviceUIdPromise();
      }catch(e){
           console.log(e);
         }
    }
  }

/**
 * 保存用户推送 ID 到用户表
 *
 * @param pushTypeKey 属性名称（例如 jgId）
 * @param pushId 推送 ID
 *     <p>使用 profilePushId("jgId", pushId) 例如极光 pushId
 *     获取方式：JPushModule.getRegistrationID(callback)
 */
function setPushUId(pushTypeKey, pushId) {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.setPushUId &&
    RNHinaReactNativeLib.setPushUId(pushTypeKey, pushId);
}

/**
 * 设置当前 serverUrl
 *
 * @param serverUrl 当前 serverUrl
 */
function setServerUrl(serverUrl) {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.setServerUrl &&
    RNHinaReactNativeLib.setServerUrl(serverUrl);
}

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

function setFlushNetworkPolicy(networkType) {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.setFlushNetworkPolicy &&
    RNHinaReactNativeLib.setFlushNetworkPolicy(networkType);
}

/**
 * 设置是否允许请求网络，默认是 true
 *
 * @param isRequest boolean
 */
function enableNetworkRequest(isRequest) {
  RNHinaReactNativeLib &&
    RNHinaReactNativeLib.enableNetworkRequest &&
    RNHinaReactNativeLib.enableNetworkRequest(isRequest);
}


/************** Android only end *****************/
export {AutoTrackType}

export default {
  init,
  track,
  trackTimerStart,
  trackTimerEnd,
  userSet,
  userSetOnce,
  userAdd,
  userAppend,
  userUnset,
  userDelete,
  setUserUId,
  cleanUserUId,
  setPushUId,
  setDeviceUId,
  getDeviceUIdPromise,
  flush,
  clear,
  setServerUrl,
  setFlushNetworkPolicy,
  enableNetworkRequest,
  hn: RNHinaReactNativeLib,
};
