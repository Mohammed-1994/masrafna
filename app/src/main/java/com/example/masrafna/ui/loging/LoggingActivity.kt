package com.example.masrafna.ui.loging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.masrafna.R

class LoggingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging)

        val navController = findNavController(R.id.nav_host_fragment_activity_home)

        runOnUiThread {
            navController.setGraph(R.navigation.main_activity_nav)
        }
    }
}