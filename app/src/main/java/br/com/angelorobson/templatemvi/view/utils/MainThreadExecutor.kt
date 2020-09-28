package br.com.angelorobson.templatemvi.view.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.NonNull
import java.util.concurrent.Executor

// Executes Runnable tasks on the UI thread
class MainThreadExecutor : Executor {
    private val mHandler = Handler(Looper.getMainLooper())
    override fun execute(@NonNull command: Runnable) {
        mHandler.post(command)
    }
}