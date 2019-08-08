# FMenu
Libreria morphing del Floating Action Button Android para obtener 2 opciones

[![](https://jitpack.io/v/LouisP79/FABMenu.svg)](https://jitpack.io/#LouisP79/FABMenu)

![](logo.png)

### Implementación

#### build.gradle (Project)
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
#### build.gradle (Module:app)
```gradle
  implementation 'com.github.LouisP79:FABMenu:v1.0'
```

#### View
```xml
<com.fmenu.FMenu
            android:id="@+id/fMenu"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_below="@id/app_bar"
            app:fSrc="@drawable/ic_adjust_black_24dp"
            app:fSrcTint="@color/colorAccent"
            app:fSrcBackground="@color/colorPrimary"
            app:fRadius="0dp"
            app:fBt1Text="Prueba 1"
            app:fBt2Text="Prueba 2"
            app:fBt1TextColor="@android:color/white"
            app:fBt2TextColor="@color/colorPrimary"
            app:fBt1Background="@color/colorPrimary"
            app:fBt2Background="@android:color/white"
            app:fBt1Drawable="@drawable/ic_adjust_black_24dp"
            app:fBt2Drawable="@drawable/ic_adjust_black_24dp"
            app:fBt1DrawableTint="@android:color/white"
            app:fBt2DrawableTint="@color/colorAccent"
            app:fBt1DrawablePadding="50dp"
            app:fBt2DrawablePadding="50dp"
            tools:fShowMenu="true"/>
```

#### Listener
```kotlin
fMenu.setOnFMenuClickListener(object: FMenu.OnFMenuClick{
            override fun onFBT1Click() {
                Toast.makeText(this@MainActivity, "fBt1 Click", Toast.LENGTH_SHORT).show()
            }

            override fun onFBT2Click() {
                Toast.makeText(this@MainActivity, "fBt2 Click", Toast.LENGTH_SHORT).show()
            }
        })
```


#### onBackPressed()
```kotlin
override fun onBackPressed() {
        if(fMenu.isFMenuOpen()) {
            fMenu.hideFMenu()
            return
        }
        super.onBackPressed()
    }
```
