package com.ly.kotlinsupermvvm.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ly.kotlinsupermvvm.R
import com.ly.kotlinsupermvvm.adapter.PictureAdapter
import com.ly.kotlinsupermvvm.base.BaseFragment
import com.ly.kotlinsupermvvm.entity.OpenApiPicture
import com.ly.kotlinsupermvvm.viewmodel.PictureViewModel
import kotlinx.android.synthetic.main.fragment_picture.*

/**
 * <Pre>
 *     新闻
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/18 16:06
 */
class PictureFragment : BaseFragment<PictureViewModel>() {
    private val mAdapter by lazy { PictureAdapter() }

    override fun getVMClass(): Class<PictureViewModel>? = PictureViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.fragment_picture
    }

    override fun initView() {
        rv_picture.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_picture.adapter = mAdapter
    }

    override fun initData() {
        mViewModel.getPictureList(1)
    }

    private fun addPicture(list: List<OpenApiPicture>) {
        mAdapter.apply {
            if(!list.isEmpty()) {
                setNewData(list)
            }
        }
    }

    override fun startObserve() {
        mViewModel.apply {
            mPictureList.observe(this@PictureFragment, Observer {
                it?.run { addPicture(it) }
            })
        }
    }
}