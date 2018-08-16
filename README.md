## ItemLayout

一行代码链式调用，实现通用item布局

只支持kotlin，还没开始用的，建议学习下kotlin。无论是否认可，都不可否认谷歌在让kotlin上位，无论官方文档片段还是新出开源demo的源码，清一色kotlin语言。


use Gradle:

```
repositories {
  maven { url "https://jitpack.io" }
  mavenCentral()
  google()
}
dependencies {
  implementation 'com.github.czh235285:ItemLayout:1.0.1'
}


```
## 介绍

* 工作中常见的通用布局，比如QQ设置界面，链式调用。
* 对外提供了方法可以获取指定的item，偶尔遇到根据type会隐藏item的时候可用。
* item点击事件
* 动态修改指定item文本


## xml中
![](1.png)

## 代码中
![](2.png)
