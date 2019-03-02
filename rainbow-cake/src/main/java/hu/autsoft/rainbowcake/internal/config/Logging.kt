package hu.autsoft.rainbowcake.internal.config

import android.util.Log
import hu.autsoft.rainbowcake.config.Logger
import java.io.PrintWriter
import java.io.StringWriter

internal inline fun <reified T> T.log(message: String) {
    if (RainbowCakeConfiguration.isDebug.not()) {
        return
    }
    RainbowCakeConfiguration.logger.logger.log(T::class.java.simpleName, message)
}

internal inline fun <reified T> T.log(e: Throwable) {
    if (RainbowCakeConfiguration.isDebug.not()) {
        return
    }

    log(getStacktraceString(e))
}

private fun getStacktraceString(e: Throwable): String {
    // Implementation taken from Timber
    val sw = StringWriter(256)
    val pw = PrintWriter(sw, false)
    e.printStackTrace(pw)
    pw.flush()
    return sw.toString()
}

internal object BlankLogger : Logger {
    override fun log(tag: String, message: String) {
    }
}

internal object AndroidLogger : Logger {
    override fun log(tag: String, message: String) {
        Log.d(tag, message)
    }
}
