package com.dg.tvmaze.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var listFragment: BottomNavigationFragment
    private lateinit var searchFragment: BottomNavigationFragment
    private lateinit var favoritesFragment: BottomNavigationFragment

    private lateinit var activeFragment: BottomNavigationFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
        supportActionBar?.setDisplayShowCustomEnabled(true);
        supportActionBar?.setCustomView(R.layout.custom_action_bar);
        setContentView(binding.root)
        setupBottomNavigationView(savedInstanceState != null)
    }

    private fun setupBottomNavigationView(fromConfigChange: Boolean) {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment = when(item.itemId) {
                R.id.action_list -> listFragment
                R.id.action_search -> searchFragment
                else -> favoritesFragment
            }
            supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
            activeFragment = fragment
            true
        }

        if(!fromConfigChange) {
            listFragment =  ListFragment.newInstance()
            searchFragment =  SearchFragment.newInstance()
            favoritesFragment =  FavoritesFragment.newInstance()
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragmentContainerView, listFragment).hide(listFragment)
                add(R.id.fragmentContainerView, searchFragment).hide(searchFragment)
                add(R.id.fragmentContainerView, favoritesFragment).hide(favoritesFragment)
            }.commit()
        } else {
            listFragment = supportFragmentManager.fragments[0] as BottomNavigationFragment
            searchFragment = supportFragmentManager.fragments[1] as BottomNavigationFragment
            favoritesFragment = supportFragmentManager.fragments[2] as BottomNavigationFragment
            supportFragmentManager.beginTransaction().apply {
                show(listFragment)
                hide(searchFragment)
                hide(favoritesFragment)
            }.commit()
        }
        activeFragment = listFragment
        binding.bottomNavigationView.selectedItemId = R.id.action_list
    }

    override fun onBackPressed() {
        when {
            activeFragment.handleUserBackPressed() -> return
            else -> super.onBackPressed()
        }
    }
}