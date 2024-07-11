package com.dev.amr.amlakfile.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragmentActivity : FragmentActivity,list : List<Fragment>) : FragmentStateAdapter(fragmentActivity) {

    private val listFrag = list
    override fun getItemCount(): Int {
        return listFrag.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFrag[position]
    }
}