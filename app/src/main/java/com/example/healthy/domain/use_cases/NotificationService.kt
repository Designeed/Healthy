package com.example.healthy.domain.use_cases

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationService {
    companion object {
        private var toastMessage: Toast? = null

        suspend fun notifyWithContext(context: Context, message: String) {
            if (toastMessage != null)
                toastMessage?.cancel()

            withContext(Dispatchers.Main) {
                toastMessage = Toast.makeText(context, message, Toast.LENGTH_SHORT)
                toastMessage?.show()
            }
        }

        fun notify(context: Context, message: String) {
            if (toastMessage != null)
                toastMessage?.cancel()

            toastMessage = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toastMessage?.show()
        }
    }
}