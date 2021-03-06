package com.example.kviz

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class MainActivity : AppCompatActivity() {
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
                    replace(R.id.fragmentHolder,aboutFragment)
                        addToBackStack(null)
                    commit()
                        drawerLayout.closeDrawers()
                    }
                }
                R.id.item_books -> {
                    val booksFragment = BooksFragment()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentHolder,booksFragment)
                        addToBackStack(null)
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
