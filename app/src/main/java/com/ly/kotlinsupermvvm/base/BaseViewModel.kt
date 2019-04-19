package com.ly.kotlinsupermvvm.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ly.kotlinsupermvvm.entity.OpenApiResponse
import kotlinx.coroutines.*

/**
 * <Pre>
 *     基础VM
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/17 10:49
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    val mException: MutableLiveData<Exception> = MutableLiveData()


    private fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    private suspend fun tryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
        finallyBlock: suspend CoroutineScope.() -> Unit,
        handleCancellableExceptionManually: Boolean = false
    ) {
        coroutineScope {
            try {
                tryBlock()
            } catch (e: Exception) {
                if (e !is CancellationException || handleCancellableExceptionManually) {
                    mException.value = e;
                    catchBlock(e)
                } else {
                    throw e
                }
            } finally {
                finallyBlock()
            }
        }
    }

    suspend fun <T> launchIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }

    fun launch(tryBlock: suspend CoroutineScope.() -> Unit) {
        launchOnUI {
            tryCatch(tryBlock, {}, {}, true)
        }
    }

    fun launchOnUITryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
        finallyBlock: suspend CoroutineScope.() -> Unit,
        handleCancellableExceptionManually: Boolean
    ) {
        launchOnUI {
            tryCatch(tryBlock, catchBlock, finallyBlock, handleCancellableExceptionManually)
        }
    }

    fun launchOnUITryCatch(tryBlock: suspend CoroutineScope.() -> Unit,
                           handleCancellableExceptionManually: Boolean) {
        launchOnUI {
            tryCatch(tryBlock, {}, {}, handleCancellableExceptionManually)
        }
    }

    suspend fun execute(response: OpenApiResponse<Any>,
                        successBlock: CoroutineScope.() -> Unit,
                        failBlock: suspend CoroutineScope.() -> Unit) {
        coroutineScope{
            if (response.code != 200) failBlock()
            else successBlock()
        }
    }
}