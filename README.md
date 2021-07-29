##### `Chain Chart`

[![](https://jitpack.io/v/oky2abbas/chain-chart.svg)](https://jitpack.io/#oky2abbas/chain-chart)
[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)](https://github.com/oky2abbas/chain-chart)
[![API](https://img.shields.io/badge/API-17%2B-blue.svg?style=flat)](https://github.com/oky2abbas/chain-chart)

**Chain Chart** is a library for displaying chain and line charts, the idea of this library came to my mind from Google Analytics.**



[![Donate](https://img.shields.io/badge/Cryptocurrency-Donate-green)](https://idpay.ir/oky2abbas) **BTC**: `1HPZyUP9EJZi2S87QrvCDrE47qRV4i5Fze`

[![Donate](https://img.shields.io/badge/Cryptocurrency-Donate-blue)](https://idpay.ir/oky2abbas) **ETH or USDT**: `0x4a4b0A26Eb31e9152653E4C08bCF10f04a0A02a9`




<img src="screenshots/shot_1.png">




##### Getting Started :

Add to your root build.gradle :

```groovy
allprojects {
  repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
  }
```

Add the dependency :

```groovy
dependencies {
    implementation 'com.github.oky2abbas:chainChart:v0+'
}
```



##### Simple API (default) :

In `XML` :

```xml 
<com.naqdi.chart.ChainChartView
		android:id="@+id/chainChartView"
		android:layout_width="match_parent"
    android:layout_height="300dp"
    android:layout_gravity="center"/>
```

In `Code` :

```java
val intervalList = listOf("Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
val rangeList = listOf("0-1K", "100K", "200K", "500K")
val lineList = arrayListOf<Line>().apply {
	add(Line("Line 1", Color.BLUE, listOf(10f, 280f, 88f, 70f, 23f, 33f)))
	add(Line("Line 2", Color.RED, listOf(300f, 40f, 38f, 180f, 403f, 201f)))
}

chainChartView.setData(lineList, intervalList, rangeList)
```

##### Customazation :

```xml 
app:cc_line_size="3dp"	//size as dp
app:cc_node_size="3dp"	//size as dp
app:cc_text_size="11sp"	//size as sp
app:cc_text_color="#000000"	//color as int or resource id
app:cc_font_family="@font/font_name"	//font as resource id
```

In `Code` :

```java
chainChartView.apply{
	setLineSize(3f)	// size as dp
	setNodeSize(8F)	//size as dp
	setTextSize(11f)	// size as sp
	setTextColor(Color.GRAY)	//color as int
	setFontFamily(font_family)	//font as typeface
}
```