package com.example.masrafna.ui.navigation

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import com.example.masrafna.R
import com.example.masrafna.databinding.ActivityNavigationDrawerBinding


private const val TAG = "NavigationDrarAct myTag"

class NavigationDrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)

        setSupportActionBar(binding.appBarNavigation.toolbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        binding.bottomNav.setupWithNavController(navController)


        val mainParms = binding.appBarNavigation.root
            .layoutParams as ViewGroup.MarginLayoutParams

        val tempMargin = mainParms.bottomMargin
        navController.addOnDestinationChangedListener { controller, destination, arguments ->

            if (destination.id != R.id.nav_contact &&
                destination.id != R.id.nav_notification &&
                destination.id != R.id.nav_home
            ) {
                binding.bottomNav.visibility = GONE

                val param = binding.appBarNavigation.root
                    .layoutParams as ViewGroup.MarginLayoutParams
                param.bottomMargin = 0

            } else {
                binding.bottomNav.visibility = VISIBLE

                val param = binding.appBarNavigation.root
                    .layoutParams as ViewGroup.MarginLayoutParams
                param.bottomMargin = tempMargin
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isOpen)
            binding.drawerLayout.close()
        else
            super.onBackPressed()
    }
}