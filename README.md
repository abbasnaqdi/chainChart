##### `Node Line Chart`

[![](https://jitpack.io/v/oky2abbas/chain-chart.svg)](https://jitpack.io/#oky2abbas/chain-chart)
[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)](https://github.com/oky2abbas/chain-chart)
[![API](https://img.shields.io/badge/API-17%2B-blue.svg?style=flat)](https://github.com/oky2abbas/chain-chart)

**Chain Chart View** is a 

<img src="screenshots/shot_1.png">

[![Donate](https://img.shields.io/badge/Donate-green)](https://idpay.ir/naqdi)

**Bitcoin (BTC) Donate: `bc1qhgvnx2nfzr0qep5fnsevyyn59k32wpe7q0c7nh`**

**Ethereum (ETH) Donate: `0x0dA44bbcc2d7BBF11eb070A81CB24c4eE7Bf1AD9`**




##### Getting Started :

Add to your root build.gradle :

```java
allprojects {
  repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
  }
```

Add the dependency :

```java
dependencies {
    implementation 'com.github.oky2abbas:chain-chart:0.9.0'
}
```

##### Simple API (default) :

In `XML` :

```XML 

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

  setData(lineList, intervalList, rangeList)
                

```

##### Customazation :

```XML 

    app:cc_line_size="3dp"
    app:cc_node_size="3sp"
    app:cc_text_size="11sp" 
    app:cc_font_family="@font/font_name"

```

In `Code` :

```java


  chainChartView.setLineSize(3f)
  chainChartView.setTextSize(13f)
  chainChartView.setTextColor(Color.GRAY)
  chainChartView.setNodeSize(8F)
  chainChartView.setFontFamily(Typeface.DEFAULT_BOLD)
           

```

##### FAQ :

###### Need more help?

- [Check out the classes in this folder](sample/src/main/java/com/naqdi/sample)

##### License

```
MIT License

Copyright (c) 2020  amir abbas naqdi

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
