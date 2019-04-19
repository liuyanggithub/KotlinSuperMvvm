package com.ly.kotlinsupermvvm

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * <Pre>
 *     应用上下文
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/18 17:07
 */
class MyApplication : Application() {
    companion object {
        var instance: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = applicationContext
    }
}