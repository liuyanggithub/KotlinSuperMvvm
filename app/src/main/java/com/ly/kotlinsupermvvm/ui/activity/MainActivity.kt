package com.ly.kotlinsupermvvm.ui.activity

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ly.kotlinsupermvvm.R
import com.ly.kotlinsupermvvm.base.BaseNoVMActivity
import com.ly.kotlinsupermvvm.ui.fragment.NewsFragment
import com.ly.kotlinsupermvvm.ui.fragment.PictureFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseNoVMActivity() {
    private var mCurrentFragment: Fragment? = null
    private val mNewsFragemnt by lazy { NewsFragment() }
    private val mPictureFragemnt by lazy { PictureFragment() }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchFragment(mNewsFragemnt)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchFragment(mPictureFragemnt)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        switchFragment(mNewsFragemnt)
    }

    override fun initData() {
    }

    private fun switchFragment(fragment: Fragment) {
        val transition = supportFragmentManager.beginTransaction()

        if (!fragment.isAdded) {
            mCurrentFragment?.let { transition.hide(it) }
            transition.add(R.id.main_content_layout, fragment, fragment.javaClass.name)
        } else {
            transition.hide(mCurrentFragment!!).show(fragment)
        }
        transition.commit()
        mCurrentFragment = fragment
    }
}
