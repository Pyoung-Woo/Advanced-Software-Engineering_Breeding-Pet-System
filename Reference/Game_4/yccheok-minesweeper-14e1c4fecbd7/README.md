Free and open source minesweeper. For learning and education purpose.

I want to make a few confessions

    :::xml
        android:screenOrientation="portrait"
        android:configChanges="orientation|keyboardHidden"
        
* I'm just too lazy to handle configuration changes. Hence, I add the following in `AndroidManifest.xml`. To be honest, I usually don't take this kind of shortcut :)

* Having flip animation when tapping on tile would be cool. Too lazy at this moment.

* I'm facing problem [Is it possible to position drawable of TextView to center](http://stackoverflow.com/questions/19724290/is-it-possible-to-position-drawable-of-textview-to-center). Simply using a quick hack android:padding="5dp" to make it looks reasonable good.

* There is only `setOnTouchListener`, but not `setItemOnTouchListener` for `GridView`. Using `setOnTouchListener` is good enough to create sense "Oh no! This must a a bomb!" emoji effect. But, not 100% accurate.

* Not optimized for tablet.

* Need Android 4.1 and above.

* At last by not least, I'm using Eastern emoticons.

The app can be downloaded from [https://play.google.com/store/apps/details?id=org.yccheok.minesweeper](https://play.google.com/store/apps/details?id=org.yccheok.minesweeper)


Have fun! :)


![Alt text](http://i.imgur.com/OS7xWdN.png)