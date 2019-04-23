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
    private var mCurrentPage = 1//当前加载页数

    private var mIsRefresh = true//是否是刷新

    private val mAdapter by lazy { PictureAdapter() }

    override fun getVMClass(): Class<PictureViewModel>? = PictureViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.fragment_picture
    }

    override fun initView() {
        rv_picture.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_picture.adapter = mAdapter
        mAdapter.openLoadAnimation()
        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, rv_picture)
        mAdapter.disableLoadMoreIfNotFullPage()
        swipe_refresh_layout.setColorSchemeResources(
            android.R.color.holo_green_light,
            android.R.color.holo_blue_light,
            android.R.color.holo_green_light,
            android.R.color.holo_blue_light
        )
        swipe_refresh_layout.setOnRefreshListener {
            refreshList()
        }
        swipe_refresh_layout.isRefreshing = true
    }

    override fun initData() {
        refreshList()
    }

    private fun addPicture(list: List<OpenApiPicture>) {
        mAdapter.apply {
            swipe_refresh_layout.isRefreshing = false
            if (mIsRefresh) {
                setNewData(list)
            } else {
                if (list.size < 10) {
                    loadMoreEnd()
                } else {
                    loadMoreComplete()
                }
                addData(list)
            }
        }
    }

    override fun startObserve() {
        mViewModel.apply {
            mPictureList.observe(this@PictureFragment, Observer {
                it?.run {
                    if (!it.isEmpty()) {
                        addPicture(it)
                    }
                }
            })
        }
    }

    private fun refreshList() {
        mCurrentPage = 1
        mIsRefresh = true
        mViewModel.getPictureList(mCurrentPage)
    }

    private fun loadMore() {
        mCurrentPage++
        mIsRefresh = false
        mViewModel.getPictureList(mCurrentPage)
    }
}