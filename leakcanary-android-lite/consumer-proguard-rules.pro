-dontwarn com.squareup.leakcanarylite.**
-keep class com.squareup.leakcanarylite.** { *; }

# Marshmallow removed Notification.setLatestEventInfo()
-dontwarn android.app.Notification
