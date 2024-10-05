package com.dev.amr.amlakfile.ui.fragment.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.FragmentProfileBinding
import com.dev.amr.amlakfile.ui.fragment.categories.ReportsFragment
import com.dev.amr.amlakfile.ui.fragment.setting.SettingFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int {
            return 3 // تعداد تب‌ها
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ProfileFragment()
                1 -> ReportsFragment()
                2 -> SettingFragment()
                else -> ProfileFragment()
            }
        }
    }
}