package com.bitcode.a10_04_24_notificationdemo

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bitcode.a10_04_24_notificationdemo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var notificationManager : NotificationManagerCompat
    private lateinit var notificationManager1: NotificationManager
    private var channelId = "bitcode_channel"
    private val SIMPLE_NOTIFICATION = 1
    private val BIG_PICTURE_NOTIFICATION = 2
    private val ACTION_TEXT_STYLE_NOTIFICATION = 3
    private val INBOX_STYLE_NOTIFICATION = 4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        notificationManager = NotificationManagerCompat.from(this)
        setContentView(activityMainBinding.root)
        createNotificationChannel()
//        simpleNotification()
//        bigPictureNotification()
//        actionTextStyleNotification()
//        snackBarNotification()
        inboxStyleNotification()
    }

    @SuppressLint("MissingPermission")
    private fun simpleNotification(){
        activityMainBinding.btnSimpleNotification.setOnClickListener {
            var notificationBuilder = NotificationCompat.Builder(this, channelId)
            notificationBuilder.setContentTitle("Simple Notification")
            notificationBuilder.setLights(Color.BLUE,200,200)
            notificationBuilder.setContentText("This is simple notification demo!")
            notificationBuilder.setBadgeIconType(R.drawable.ic_launcher_background)
            notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background)
            notificationBuilder.setColor(Color.MAGENTA)
            notificationBuilder.setVisibility(NotificationCompat.VISIBILITY_SECRET)
            notificationBuilder.setOngoing(true)


            var actionIntent = Intent(this,SecondActivity::class.java)
            var actionPendingIntent = PendingIntent.getActivity(this,
                1,
                actionIntent,
                PendingIntent.FLAG_MUTABLE)

            notificationBuilder.setContentIntent(actionPendingIntent)
            var simpleNotification = notificationBuilder.build()
            notificationManager.notify(SIMPLE_NOTIFICATION,simpleNotification)
        }
    }

    @SuppressLint("MissingPermission")
    private fun inboxStyleNotification(){
        var builder = NotificationCompat.Builder(this,channelId)
        builder.setContentText("Inbox Style Notification")
        builder.setSmallIcon(R.drawable.ic_launcher_background)
        builder.setContentTitle("This is Android April 2024 Batch")
        builder.setLights(Color.RED,100,100)


        var inboxStyle = NotificationCompat.InboxStyle(builder)
        inboxStyle.addLine("ios 2024")
        inboxStyle.addLine("Android 2024")
        inboxStyle.addLine("Web 2024")
        inboxStyle.addLine("java 2024")

        builder.setStyle(inboxStyle)

        notificationManager.notify(INBOX_STYLE_NOTIFICATION,builder.build())
    }

    @SuppressLint("MissingPermission")
    private fun actionTextStyleNotification(){
        var builder = NotificationCompat.Builder(this,channelId)
        builder.setContentTitle("Action Text Style")
        builder.setContentText("Action Text Notification")
        builder.setSmallIcon(R.drawable.ic_launcher_background)

        var actionIntent = Intent(this,SecondActivity::class.java)

        var pendingIntent = PendingIntent.getActivity(this,
            1,
            actionIntent,
            PendingIntent.FLAG_IMMUTABLE)

        var actionTextStyle = NotificationCompat.Action(R.drawable.ic_launcher_background,
            "Ok",
            pendingIntent)

        builder.addAction(actionTextStyle)
        builder.addAction(R.drawable.ic_launcher_background,"Cancel", pendingIntent)

        notificationManager.notify(ACTION_TEXT_STYLE_NOTIFICATION, builder.build())
    }

    @SuppressLint("MissingPermission")
    private fun bigPictureNotification(){
        var builder = NotificationCompat.Builder(this, channelId)
        builder.setContentTitle("big picture style notification")
        builder.setContentText("This is bitcode big picture notification")
        builder.setColor(Color.GREEN)
        builder.setLights(Color.BLUE, 100, 100)
        builder.setSmallIcon(R.drawable.ic_launcher_background)

        var bigPictureStyle = NotificationCompat.BigPictureStyle(builder)
        bigPictureStyle.setBigContentTitle("Android April 2024")

        var bitmapImage = BitmapFactory.decodeResource(resources,R.drawable.heliconia_image)
        bigPictureStyle.bigPicture(bitmapImage)
        builder.setStyle(bigPictureStyle)
        notificationManager.notify(BIG_PICTURE_NOTIFICATION,builder.build())
    }

    private fun snackBarNotification(){
        var snackBar = Snackbar.make(activityMainBinding.root,
            "SnackBar",
            Snackbar.LENGTH_LONG)

        snackBar.setTextColor(Color.GREEN)
        snackBar.setText("This is sncak bar notitification")
        snackBar.show()
    }

    private fun createNotificationChannel(){
        var channelBitcodeBuilder = NotificationChannelCompat.Builder(channelId,NotificationManager.IMPORTANCE_MAX)
        channelBitcodeBuilder.setName("Bitcode Channel")
        channelBitcodeBuilder.setDescription("This is Bitcode Channel")
        channelBitcodeBuilder.setShowBadge(true)
        notificationManager.createNotificationChannel(channelBitcodeBuilder.build())
    }
}