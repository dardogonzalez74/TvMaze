package com.dg.tvmaze.ui.main

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BottomNavigationFragment(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId) {

    fun handleUserBackPressed(): Boolean {
        if(childFragmentManager.fragments.isNotEmpty()) {
            childFragmentManager.popBackStack()
            return true
        }
        return false
    }
}