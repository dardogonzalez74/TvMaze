package com.dg.tvmaze.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.lifecycleScope
import com.dg.tvmaze.BuildConfig
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

fun log(message: String) = Log.d(BuildConfig.APPLICATION_ID, message)

class MainActivity : AppCompatActivity() {

    private val listFragment: BottomNavigationFragment by lazy { ListFragment.newInstance() }
    private val searchFragment: BottomNavigationFragment by lazy { SearchFragment.newInstance() }
    private val favoritesFragment: BottomNavigationFragment by lazy { FavoritesFragment.newInstance() }
    private val viewModel by viewModel<MainViewModel>()

    private var activeFragment: BottomNavigationFragment = listFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
        supportActionBar?.setDisplayShowCustomEnabled(true);
        supportActionBar?.setCustomView(R.layout.custom_action_bar);
        setContentView(binding.root)
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainerView, listFragment).hide(listFragment)
            add(R.id.fragmentContainerView, searchFragment).hide(searchFragment)
            add(R.id.fragmentContainerView, favoritesFragment).hide(favoritesFragment)
        }.commit()

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
        binding.bottomNavigationView.selectedItemId = R.id.action_list

        lifecycleScope.launchWhenCreated {
            viewModel.shows.collectLatest { log("receiving page") }
        }
    }

    override fun onBackPressed() {
        when {
            activeFragment.handleUserBackPressed() -> return
            activeFragment != listFragment -> binding.bottomNavigationView.selectedItemId = R.id.action_list
            else -> super.onBackPressed()
        }
    }
}