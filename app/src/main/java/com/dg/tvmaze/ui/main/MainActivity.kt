package com.dg.tvmaze.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.ActivityMainBinding
import com.dg.tvmaze.ui.favorites.FavoritesFragment
import com.dg.tvmaze.ui.favorites.newInstance
import com.dg.tvmaze.ui.list.ListFragment
import com.dg.tvmaze.ui.list.newInstance
import com.dg.tvmaze.ui.search.SearchFragment
import com.dg.tvmaze.ui.search.newInstance

fun log(message: String) = Log.d("TvMaze", message)

class MainActivity : AppCompatActivity() {

    private val listFragment: BottomNavigationFragment by lazy { ListFragment.newInstance() }
    private val searchFragment: BottomNavigationFragment by lazy { SearchFragment.newInstance() }
    private val favoritesFragment: BottomNavigationFragment by lazy { FavoritesFragment.newInstance() }

    private var activeFragment: BottomNavigationFragment = listFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
    }

    override fun onBackPressed() {
        when {
            activeFragment.handleUserBackPressed() -> return
            activeFragment != listFragment -> binding.bottomNavigationView.selectedItemId = R.id.action_list
            else -> super.onBackPressed()
        }
    }
}