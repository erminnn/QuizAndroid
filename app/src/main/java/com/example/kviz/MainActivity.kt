package com.example.kviz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

/*
Created by eomeragić 9/5/2020
 */
class MainActivity : AppCompatActivity() {
    lateinit var currentFragment : Fragment
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val homeFragment = HomeFragment();
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder, homeFragment)
                commit()
            }


        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState() //its ready to be used

        //displays icon that opens navbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item_about -> {
                    val aboutFragment = AboutFragment()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentHolder, aboutFragment)
                        addToBackStack(null)
                        commit()
                        drawerLayout.closeDrawers()
                    }
                }
                R.id.item_home -> {
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentHolder, homeFragment)
                        commit()
                        drawerLayout.closeDrawers()
                    }
                }
            }
            true
        }
    }

    //this fun returns t or f if the icon is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
