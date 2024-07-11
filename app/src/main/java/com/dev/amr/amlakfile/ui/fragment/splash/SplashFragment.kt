package com.dev.amr.amlakfile.ui.fragment.splash

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.data.hawk.Hawks
import com.dev.amr.amlakfile.databinding.FragmentSplashBinding
import com.orhanobut.hawk.Hawk

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    private lateinit var animation: Animation

    private lateinit var name: String
    private lateinit var family: String
    private lateinit var username: String
    private lateinit var province: String
    private lateinit var city: String
    private lateinit var amlakName: String

    private lateinit var versionApp: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        Hawk.init(requireContext()).build()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            val pInfo: PackageInfo =
                requireActivity().packageManager.getPackageInfo(requireActivity().packageName, 0)
            versionApp = pInfo.versionName
            binding.txtVersion.text = "نسخه : $versionApp"
            Hawks.save(Hawks.KEY_VERSION_APP, versionApp)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        animation = AnimationUtils.loadAnimation(requireContext(), R.anim.splash_anim);
        binding.imgLogo.animation = animation
        binding.txtNameApp.animation = animation
        binding.txtVersion.animation = animation

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {


                name = Hawks.getString(Hawks.KEY_NAME)
                family = Hawks.getString(Hawks.KEY_FAMILY)
                username = Hawks.getString(Hawks.KEY_USERNAME)
                province = Hawks.getString(Hawks.KEY_PROVINCE)
                city = Hawks.getString(Hawks.KEY_CITY)
                amlakName = Hawks.getString(Hawks.KEY_NAME_AMLAK)

                if (name == "" && family == "" && province == "" && city == "") {
                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_splashFragment_to_loginFragment)
                } else {
                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_splashFragment_to_homeFragment)
//                    }
                }


            }
        })

    }
}