# NCProcessView-Android
A ProcessView for Android  https://github.com/nanchen2251/NCProcessView-Android
## feature
1. processView
2. support specified position highlight
3. please mention issues

## Screenshots
<img width="270" height="585" src="https://github.com/nanchen2251/NCProcessView-Android/blob/master/screenshot/screenshot.png"/>


## How to use it
#### Step 1. Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}       
```
#### Step 2. Add the dependency
```groovy
dependencies {
    implementation 'com.github.nanchen2251:NCProcessView-Android:1.0.1'
}
```

#### Step 3. Just use it in your project
```xml
<com.nanchen.ncprocessview.NCProcessView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:pv_data="@array/test" />
```
#### Step 3. Support for custom attributes
```xml
<declare-styleable name="NCProcessView">
      <attr name="pv_finished_color" format="color|reference" />
      <attr name="pv_unfinished_color" format="color|reference" />
      <attr name="pv_finished_tv_color" format="color|reference" />
      <attr name="pv_unfinished_tv_color" format="color|reference" />
      <attr name="pv_tv_size" format="dimension" />
      <attr name="pv_data" format="reference"/>
      <attr name="pv_circle_radius" format="dimension"/>
      <attr name="pv_stroke_padding" format="dimension"/>
      <attr name="pv_stroke_width" format="dimension"/>
</declare-styleable>
```

#### Step 5. If you still don't understand, please refer to the demo



### About the author
    nanchen<br>
    Chengdu,China<br>
    [其它开源](https://github.com/nanchen2251/)<br>
    [个人博客](https://nanchen2251.github.io/)<br>
    [简书](http://www.jianshu.com/u/f690947ed5a6)<br>
    [博客园](http://www.cnblogs.com/liushilin/)<br>
    交流群：118116509<br>
    欢迎投稿(关注)我的唯一公众号，公众号搜索 nanchen 或者扫描下方二维码：<br>
    ![](https://github.com/nanchen2251/Blogs/blob/master/images/nanchen12.jpg)
    
## Licenses
```
 Copyright 2019 nanchen(刘世麟)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
```

