
# hina-react-native-lib

## Getting started

通过github `$ npm install --save https://github.com/hinadt/hina-react-native-lib.git`

通过npm `$ npm install hina-react-native-lib --save`

### Mostly automatic installation

`$ react-native link hina-react-native-lib`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-hina-react-native-lib` and add `RNHinaReactNativeLib.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNHinaReactNativeLib.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.hina.react.lib.RNHinaReactNativeLibPackage;` to the imports at the top of the file
  - Add `new RNHinaReactNativeLibPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':hina-react-native-lib'
  	project(':hina-react-native-lib').projectDir = new File(rootProject.projectDir, 	'../node_modules/hina-react-native-lib/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':hina-react-native-lib')
  	```


## Usage
```javascript
import RNHinaReactNativeLibModule from 'hina-react-native-lib';

// TODO: What to do with the module?
RNHinaReactNativeLibModule;
```
  