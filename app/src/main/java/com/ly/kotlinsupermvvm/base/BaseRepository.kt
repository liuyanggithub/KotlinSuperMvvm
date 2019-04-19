package com.ly.kotlinsupermvvm.base

import com.ly.kotlinsupermvvm.entity.OpenApiResponse

/**
 * <Pre>
 *     基础数据仓库
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/18 15:35
 */
open class BaseRepository {
    suspend fun <T : Any> apiCall(call: suspend () -> OpenApiResponse<T>): OpenApiResponse<T> {
        return call.invoke()
    }
}