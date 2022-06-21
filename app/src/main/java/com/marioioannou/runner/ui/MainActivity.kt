package com.marioioannou.runner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.marioioannou.runner.R
import com.marioioannou.runner.databinding.ActivityMainBinding
import com.marioioannou.runner.db.RunDAO
import com.marioioannou.runner.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Log.d("runDao","RUNDAO: ${runDAO.hashCode()}")
        setSupportActionBar(binding.myToolbar)
        fragmentNavigation()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        navController.addOnDestinationChangedListener {_,destination,_ ->
                when (destination.id) {
                    R.id.settingsFragment,
                    R.id.runFragment,
                    R.id.statisticsFragment ->
                        binding.bottomNavigationView.isVisible = true
                    else -> {
                        binding.bottomNavigationView.isVisible = false
                        binding.myToolbar.isVisible = false
                    }
                }
            }

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        binding.navHostFragment.findNavController().addOnDestinationChangedListener { navController, destination, _ ->
//            when (destination.id) {
//                R.id.settingsFragment,
//                R.id.runFragment,
//                R.id.statisticsFragment ->
//                    binding.bottomNavigationView.isVisible = true
//                else -> binding.bottomNavigationView.isVisible = false
//            }
//        }
    }

    private fun fragmentNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.runFragment,
                R.id.trackingFragment,
                R.id.settingsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}