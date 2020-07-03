package com.github.grishberg.daggersample.custompanel.stub

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.File
import java.util.concurrent.*


const val CACHED_FILE_NAME = "cache.png"

class CachedBitmapRepository(appContext: Context) {
    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()

    // Sets the amount of time an idle thread waits before terminating
    private val KEEP_ALIVE_TIME = 1000L

    // Sets the Time Unit to Milliseconds
    private val KEEP_ALIVE_TIME_UNIT: TimeUnit = TimeUnit.MILLISECONDS
    private val threadPoolExecutor: ThreadPoolExecutor

    private val future: Future<Drawable?>

    init {
        threadPoolExecutor = ThreadPoolExecutor(
            NUMBER_OF_CORES + 5,  // Initial pool size
            NUMBER_OF_CORES + 8,  // Max pool size
            KEEP_ALIVE_TIME,  // Time idle thread waits before terminating
            KEEP_ALIVE_TIME_UNIT,  // Sets the Time Unit for KEEP_ALIVE_TIME
            LinkedBlockingDeque<Runnable>()
        ) // Work Queue
        future = threadPoolExecutor.submit(LoadCacheTask(appContext))
    }

    fun getCachedStub(): Drawable? = future.get()

    private class LoadCacheTask(private val context: Context) : Callable<Drawable?> {
        override fun call(): Drawable? {
            val bitmap = readBitmap(context) ?: return null
            return BitmapDrawable(context.resources, bitmap)
        }

        private fun readBitmap(context: Context): Bitmap? {
            val image = File(context.cacheDir, CACHED_FILE_NAME) ?: return null

            val bmOptions = BitmapFactory.Options()
            return BitmapFactory.decodeFile(image.absolutePath, bmOptions)
        }
    }
}