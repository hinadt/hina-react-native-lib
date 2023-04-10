import React, { useState } from "react";
import { Button, Text, ScrollView, View, ToastAndroid } from "react-native";
import HinaCloud from './HinaCloud';

const Cafe = () => {
  const [uid, setUid] = useState("");
  var param = {serverUrl:"https://loanetc.mandao.com/hn?token=BHRfsTQS",flushInterval:1000,flushPendSize:1,enableLog:true,autoTrackTypePolicy:3};
  HinaCloud.init(param)
  return (
    <ScrollView>
    <Text>--</Text>
      <Button
        title="track"
        onPress={() => {
          HinaCloud.track("click_one", {who:"william"})
        }}
      />
            <Text>--</Text>
      <Button
        title="trackTimerStart"
        onPress={() => {
          HinaCloud.trackTimerStart("ViewProduct");
        }}
      />
      <Text>--</Text>
      <Button
        title="trackTimerEnd"
        onPress={() => {
          HinaCloud.trackTimerEnd("ViewProduct", {product:"商品1"});
        }}
      />
      <Text>--</Text>
      <Button
        title="userSet"
        onPress={() => {
          HinaCloud.userSet({gender:"男"});
        }}
      />
      <Text>--</Text>
      <Button
        title="userSetOnce"
        onPress={() => {
          HinaCloud.userSetOnce({register_date:"2022-01-01"});
        }}
      />
      <Text>--</Text>
      <Button
        title="userAdd"
        onPress={() => {
          HinaCloud.userAdd({score:80});
        }}
      />
      <Text>--</Text>
      <Button
        title="userAppend"
        onPress={() => {
          HinaCloud.userAppend("click-lala", null);
        }}
      />
      <Text>--</Text>
      <Button
        title="userUnset"
        onPress={() => {
          HinaCloud.userUnset("gender");
        }}
      />
      <Text>--</Text>
      <Button
        title="userDelete"
        onPress={() => {
          HinaCloud.userDelete();
        }}
      />
      <Text>--</Text>
      <Button
        title="setUserUId"
        onPress={() => {
          HinaCloud.setUserUId("1234567890");
        }}
      />
      <Text>--</Text>
      <Button
        title="cleanUserUId"
        onPress={() => {
          HinaCloud.cleanUserUId();
        }}
      />
      <Text>--</Text>
      <Button
        title="setDeviceUId"
        onPress={() => {
          HinaCloud.setDeviceUId("dededededededede");
        }}
      />
      <Text>--</Text>
      <Button
        title={uid}
        onPress={() => {
          HinaCloud.getDeviceUIdPromise().then(result => {
            title=result
          })
        }}
      />
      <Text>--</Text>
      <Button
        title="setFlushNetworkPolicy"
        onPress={() => {
          HinaCloud.setFlushNetworkPolicy(14);
        }}
      />
      <Text>--</Text>
      <Button
        title="flush"
        onPress={() => {
          HinaCloud.flush();
        }}
      />
      <Text>--</Text>
      <Button
        title="clear"
        onPress={() => {
          HinaCloud.clear();
        }}
      />
    </ScrollView>
  );
}

export default Cafe;