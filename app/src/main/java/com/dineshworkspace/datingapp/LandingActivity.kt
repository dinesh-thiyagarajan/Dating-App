package com.dineshworkspace.datingapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.dineshworkspace.datingapp.helpers.AppConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class LandingActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_nav, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (AppConstants.HIDE_BOTTOM_NAV_SCREENS.contains(destination.id)) {
                updateBottomNavVisibility(View.GONE)
                return@addOnDestinationChangedListener
            }
            updateBottomNavVisibility(View.VISIBLE)
        }
        updateBottomNavWithBadges()
    }

    fun showFragment(action: Int, bundle: Bundle?) {
        navController.navigate(action, bundle)
    }

    private fun updateBottomNavVisibility(bottomNavVisibility: Int) {
        bottom_nav?.let {
            it.visibility = bottomNavVisibility
        }
    }

    private fun updateBottomNavWithBadges(){
        val notesBadge = bottom_nav.getOrCreateBadge(R.id.notesFragment)
        notesBadge.isVisible = true
        notesBadge.number = 9

        val matchesBadge = bottom_nav.getOrCreateBadge(R.id.matchesFragment)
        matchesBadge.isVisible = true
        matchesBadge.number = 50
    }

    override fun onBackPressed() {
        if (navController.popBackStack().not()) {
            finish()
        }
    }
}