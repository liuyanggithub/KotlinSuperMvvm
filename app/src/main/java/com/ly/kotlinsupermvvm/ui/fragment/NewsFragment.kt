package com.ly.kotlinsupermvvm.ui.fragment

import com.ly.kotlinsupermvvm.R
import com.ly.kotlinsupermvvm.base.BaseFragment
import com.ly.kotlinsupermvvm.viewmodel.NewsViewModel

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
class NewsFragment : BaseFragment<NewsViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }

    override fun initView() {
    }

    override fun initData() {
    }

}