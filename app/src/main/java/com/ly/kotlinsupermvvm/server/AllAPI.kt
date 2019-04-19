package com.ly.kotlinsupermvvm.server

import com.ly.kotlinsupermvvm.entity.OpenApiPicture
import com.ly.kotlinsupermvvm.entity.OpenApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST


/**
 * <Pre>
 *     网络请求接口
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/18 16:59
 */
interface AllAPI {
    companion object {
        const val BASE_URL = "https://api.apiopen.top"
        const val DOMAIN = "Domain-Name: "
        //易源地址标签
        const val DOMAIN_SHOW_API = "domain_show_api"
        //openapi标签
        const val DOMAIN_OPEN_API = "domain_open_api"

        //图片接口地址
        const val OPEN_API_PICTURES_URL = "/getImages"
    }

    @Headers(DOMAIN + DOMAIN_OPEN_API)
    @FormUrlEncoded
    @POST(OPEN_API_PICTURES_URL)
    fun getPictures(
        @Field("page") page: Int,
        @Field("count") count: Int
    ): Deferred<OpenApiResponse<List<OpenApiPicture>>>

}