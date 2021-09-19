package com.mns.banzosapp.utils

import android.view.View
import android.view.ViewGroup

import com.google.android.material.tabs.TabLayout


class TabLayoutUtils {

    companion object {
        fun enableTabs(tabLayout: TabLayout?, enable: Boolean?) {
            val viewGroup = getTabViewGroup(tabLayout)
            if (viewGroup != null) for (childIndex in 0 until viewGroup.childCount) {
                val tabView: View? = viewGroup.getChildAt(childIndex)
                if (tabView != null) tabView.setEnabled(enable!!)
            }
        }

        fun getTabView(tabLayout: TabLayout?, position: Int): View? {
            var tabView: View? = null
            val viewGroup = getTabViewGroup(tabLayout)
            if (viewGroup != null && viewGroup.childCount > position) tabView =
                viewGroup.getChildAt(position)
            return tabView
        }

        private fun getTabViewGroup(tabLayout: TabLayout?): ViewGroup? {
            var viewGroup: ViewGroup? = null
            if (tabLayout != null && tabLayout.childCount > 0) {
                val view: View? = tabLayout.getChildAt(0)
                if (view != null && view is ViewGroup) viewGroup = view
            }
            return viewGroup
        }
    }
}