package com.ly.kotlinsupermvvm.entity

/**
 * <Pre>
 *     open api通用响应数据
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/17 14:48
 */
data class OpenApiResponse<out T>(val code: Int, val message: String, val result: T)