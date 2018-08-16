## ItemLayout

一行代码链式调用，实现通用item布局


kotlin常用插件 JsonToKotlinClass同java的GsonFormat

use Gradle:

```
repositories {
  maven { url "https://jitpack.io" }
  mavenCentral()
  google()
}
dependencies {
  implementation 'com.github.czh235285:FastKotlin:1.4.2'
}
```
## 介绍

* 一个简单的Demo，初学kotlin的可以看看,kotlin+rxjava2+retrofit2+mvp
* 沉浸式状态栏，浅色状态栏。
* 大量常用工具类，扩展函数
* 网络请求用的 [xsnow](https://github.com/xiaoyaoyou1212/XSnow)（基于Retrofit2二次封装的框架，缓存方面封装比较好，直接拿来用了）
* 数据库 [dbflow](https://github.com/Raizlabs/DBFlow)(目前最好用的安卓数据库，lib中未添加，具体用法可以看demo)
* 通用adapter [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)
* 下拉刷新/上拉加载库 [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)
* 6.0动态权限申请 [acp](https://github.com/mylhyl/AndroidAcp)

## 一键生成MVP代码
![](mvp.gif)


加载图片:

```

一行代码简单调用

imageView.load(url)  

```

跳转Activity:

```

不带参数
warpActivity<OtherActivity>()  或者
warpActivityForResult<OtherActivity>(500)

传值
warpActivity<OtherActivity>("id" to 1,"name" to "姓名")  或者
warpActivityForResult<OtherActivity>(500,"id" to 1,"name" to "姓名")

OtherActivity获取参数

val id=intent.extras.getInt("id")
val name=intent.extras.getString("name")

```

Dialog:

```

一行代码简单调用

 showDialog("提示语")  或者
 showDialog("提示语",{ //确定按钮操作})

```

网络请求加载动画:

```

仿百度加载动画
 showLoading()
 stopLoading()

```