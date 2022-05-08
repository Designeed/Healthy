package com.example.healthy.domain.use_cases

import android.content.Context
import android.widget.Toast

class NotificationUseCase {

    companion object {
        private var toastMessage: Toast? = null

        fun execute(context: Context, message: String) {
            if (toastMessage != null)
                toastMessage?.cancel()

            toastMessage = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toastMessage?.show()
        }
    }
}