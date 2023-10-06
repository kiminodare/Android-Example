package com.kimi.qrisnew.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

object GeneralUtils {
    fun showToastMessage(context: Context?, message: String?) {
        context?.let {
            val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            val group = toast.view as ViewGroup?
            val messageTextView = group!!.getChildAt(0) as TextView

            messageTextView.textSize = 30f
            toast.show()
        }
    }
}