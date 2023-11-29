package com.jason.singularappbykotlin

import android.content.Context
import android.widget.Toast

class Utils {

    companion object {
        @JvmStatic fun isNullOrEmpty(string: String): String {
            return string.trim().equals("").toString()
        }

        @JvmStatic fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}