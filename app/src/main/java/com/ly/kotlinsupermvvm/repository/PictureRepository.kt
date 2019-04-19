package com.ly.kotlinsupermvvm.repository

import com.ly.kotlinsupermvvm.base.BaseRepository
import com.ly.kotlinsupermvvm.entity.OpenApiPicture
import com.ly.kotlinsupermvvm.entity.OpenApiResponse
import com.ly.kotlinsupermvvm.server.RetrofitService

/**
 * <Pre>
 *     图片数据仓库
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/18 16:37
 */
class PictureRepository : BaseRepository(){
    suspend fun getPictureList(page: Int, count: Int): OpenApiResponse<List<OpenApiPicture>> {
        return apiCall { RetrofitService.service.getPictures(page, count).await() }
    }
}