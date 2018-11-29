#SolarexSeekbarDemo
-------------------------------

```java
java代码中setxxx即可设置相应属性
android:max="100" //滑动条的最大值
android:progress="60" //滑动条的当前值
android:maxHeight="20"//进度条的最大高度
android:minHeight="5"//进度条的最低高度
android:secondaryProgress="70" //二级滑动条的进度
android:thumb = "@drawable/sb_thumb" //滑块的图样
android:progressDrawable="@drawable/sb_bar" //设置进度条图样
```

滑块thumb四周不透明,看了一下icon,发现icon四周是透明的没错。查一下发现，原来 The Material seek bar has split track enabled by default，所以我们要把它disable掉。

```xml
android:splitTrack="false"
```
