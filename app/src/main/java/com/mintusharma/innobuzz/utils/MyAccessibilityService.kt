package com.mintusharma.innobuzz.utils

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
//        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event?.packageName?.toString()
//            val className = event.className?.toString()

            if (packageName == "com.whatsapp") {
                Log.d("MintuAccessebility",packageName.toString())
                showToast("WhatsApp launched")
            }
//        }
    }

    override fun onInterrupt() {

    }

    override fun onServiceConnected() {

        val info = AccessibilityServiceInfo()

        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or
                AccessibilityEvent.TYPE_VIEW_FOCUSED

        info.packageNames =
            arrayOf("com.whatsapp")

        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN

        info.notificationTimeout = 100

        this.serviceInfo = info

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}