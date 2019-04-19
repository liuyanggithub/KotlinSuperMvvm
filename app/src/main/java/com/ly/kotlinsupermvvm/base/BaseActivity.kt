package com.ly.kotlinsupermvvm.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.title_layout.*

/**
 * <Pre>
 *     带viewmodel的基础Activity
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/17 15:12
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    private val mDialog by lazy {
        MaterialDialog.Builder(this)
            .progress(true, 1)
            .canceledOnTouchOutside(false)
            .build()
    }
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initVM()
        initView()
        setSupportActionBar(mToolbar)
        initData()
        startObserve()
    }

    open fun startObserve() {}
    open fun getVMClass(): Class<VM>? = null

    abstract fun getLayoutId(): Int
    open fun initView() {}
    open fun initData() {}

    private fun initVM() {
        getVMClass()?.let {
            mViewModel = ViewModelProviders.of(this).get(it)
            lifecycle.addObserver(mViewModel)
        }
    }

    protected fun startActivity(z: Class<*>) {
        startActivity(Intent(this, z))
    }

    protected fun showProgress(content: String) {
        mDialog.setContent(content)
        mDialog.show()
    }

    protected fun showProgress(resId: Int) {
        mDialog.setContent(getString(resId))
        mDialog.show()
    }

    protected fun dismissDialg() {
        mDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
    }
}