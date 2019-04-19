package com.ly.kotlinsupermvvm.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.title_layout.*

/**
 * <Pre>
 *     没有viewmodel的基础Activity
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/18 15:29
 */
abstract class BaseNoVMActivity : AppCompatActivity() {
    private val mDialog by lazy {
        MaterialDialog.Builder(this)
            .progress(true, 1)
            .canceledOnTouchOutside(false)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        setSupportActionBar(mToolbar)
        initData()
    }

    abstract fun getLayoutResId(): Int
    open fun initView() {}
    open fun initData() {}

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
}