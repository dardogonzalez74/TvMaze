package com.dg.tvmaze.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.ActivityMainBinding

fun log(message: String) = Log.d("TvMaze", message)

class MainActivity : AppCompatActivity() {

    private val listFragment by lazy { ListFragment.newInstance() }
    private val searchFragment by lazy { SearchFragment.newInstance() }
    private val favoritesFragment by lazy { FavoritesFragment.newInstance() }

    private var activeFragment: Fragment = listFragment
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.frameLayout, listFragment).hide(listFragment)
            add(R.id.frameLayout, searchFragment).hide(searchFragment)
            add(R.id.frameLayout, favoritesFragment).hide(favoritesFragment)
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
}