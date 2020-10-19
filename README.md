##### `Chain Chart`

[![](https://jitpack.io/v/oky2abbas/chain-chart.svg)](https://jitpack.io/#oky2abbas/chain-chart)
[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)](https://github.com/oky2abbas/chain-chart)
[![API](https://img.shields.io/badge/API-17%2B-blue.svg?style=flat)](https://github.com/oky2abbas/chain-chart)

**Chain Chart is a library for displaying chain and line charts, the idea of this library came to my mind from Google Analytics.**

**Bitcoin (BTC) [![Donate](https://img.shields.io/badge/Donate-green)](https://idpay.ir/oky2abbas): `1HPZyUP9EJZi2S87QrvCDrE47qRV4i5Fze`**

**Ethereum (ETH) [![Donate](https://img.shields.io/badge/Donate-green)](https://idpay.ir/oky2abbas): `0x4a4b0A26Eb31e9152653E4C08bCF10f04a0A02a9`**

**Tron (TRX) [![Donate](https://img.shields.io/badge/Donate-green)](https://idpay.ir/oky2abbas): `TAewZVAD4eKjPo9uJ5TesxJUrXiBtVATsK`**



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
    implementation 'com.github.oky2abbas:chain-chart:0.9.1'
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

##### Licenses

```
MIT License

Copyright (c) 2021  amir abbas naqdi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE
```
