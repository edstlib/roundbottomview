# RoundBottomView

![RoundBottomView](https://i.ibb.co/bQy45JY/2021-09-06-07-27-15.jpg)
## Setup
### Gradle

Add this to your project level `build.gradle`:
```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
Add this to your app `build.gradle`:
```groovy
dependencies {
    implementation 'com.github.edstlib:roundbottomview:latest'
}
```

### Usage

To show bottom view, just call static mehotd RoundBottomView.show

```kotlin
    /*
        title: title of bottom view, if no title set with empty string
        contentLayout: resource id of content layout which show on bottom view
        process: process layout content, view: object view from content layout
    */
    fun show(context: Context, title: String, contentLayout: Int, process: (contentLayout: View?) -> Unit)
    fun show(context: Context, title: String?, contentLayout: Int, headerLayout: Int,
         process: (contentLayout: View?, headerLayout: View?) -> Unit) 
```
Here is example code

```kotlin
          findViewById<View>(R.id.textView).setOnClickListener {
            RoundBottomView.show(this, "Title", R.layout.view_dialog_content) {
                it?.findViewById<View>(R.id.textView)?.setOnClickListener {
                    Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show()
                }
            }
        }
```
view_dialog_content

```xml
    <?xml version="1.0" encoding="utf-8"?>
    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:paddingBottom="100dp"
        android:layout_gravity="center">
        <TextView
            android:id="@+id/textView"
            android:layout_gravity="center"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Dialog content exapmle. Click this text" />
    </FrameLayout>
```

And you can set text style of title with method static
```kotlin
    fun setTitleStyle(style: Int)
``` 

