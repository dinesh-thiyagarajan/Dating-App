package com.dineshworkspace.datingapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.dineshworkspace.datingapp.helpers.AppConstants
import com.dineshworkspace.datingapp.helpers.SharedPrefHelper
import kotlinx.android.synthetic.main.activity_main.*

class LandingActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_nav, navController)
        showHideBottomNav()
    }

    private fun showHideBottomNav() {
        if (SharedPrefHelper.getBoolean(AppConstants.IS_PHONE_VALIDATED, false)) {
            bottom_nav.visibility = View.VISIBLE
        } else {
            bottom_nav.visibility = View.GONE
        }
    }

    fun showFragment(action: Int, bundle: Bundle?) {
        navController.navigate(action, bundle)
    }
}