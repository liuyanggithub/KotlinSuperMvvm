package com.ly.kotlinsupermvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ly.kotlinsupermvvm.base.BaseViewModel
import com.ly.kotlinsupermvvm.entity.OpenApiPicture
import com.ly.kotlinsupermvvm.repository.PictureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * <Pre>
 *     新闻
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/18 16:08
 */
class PictureViewModel : BaseViewModel(){
    private val repository by lazy { PictureRepository() }
    val mPictureList : MutableLiveData<List<OpenApiPicture>> = MutableLiveData()

    fun getPictureList(page: Int) {
        launch {
            val result = withContext(Dispatchers.IO) {
                repository.getPictureList(page, 10)
            }

            execute(result, {mPictureList.value = result.result}, {})
        }
    }
}