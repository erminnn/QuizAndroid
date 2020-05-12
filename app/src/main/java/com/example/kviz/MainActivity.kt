package com.example.kviz

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
/*
Created by eomeragić 9/5/2020
 */
class MainActivity : AppCompatActivity() {
    lateinit var currentFragment : Fragment
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        if(savedInstanceState != null){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder,currentFragment)
                commit()
            }
        }else {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val homeFragment = HomeFragment();
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder, homeFragment)
                commit()
            }
            currentFragment = homeFragment
        }
        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState() //its ready to be used

        //displays icon that opens navbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> {
                    val aboutFragment = AboutFragment()
                    supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragmentHolder,aboutFragment)
                    commit()}
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


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        val fragment : Fragment? = supportFragmentManager.findFragmentById(R.id.fragmentHolder)
        currentFragment = fragment as Fragment
    }

}
