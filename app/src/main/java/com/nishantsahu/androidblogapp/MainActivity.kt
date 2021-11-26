package com.nishantsahu.androidblogapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.nishantsahu.androidblogapp.databinding.ActivityMainBinding
import com.nishantsahu.api.BlogApiClient
import com.nishantsahu.api.models.entities.User

class MainActivity : AppCompatActivity() {

    companion object{

        const val PREFS_AUTH = "prefs_auth"
        const val PREFS_AUTH_TOKEN = "prefs_auth_token"
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var authViewModel: AuthViewModel
    private var binding: ActivityMainBinding?=null
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(PREFS_AUTH, Context.MODE_PRIVATE)
        binding = ActivityMainBinding.inflate(layoutInflater)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        setContentView(binding?.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_feed,
                R.id.nav_auth
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        sharedPreferences.getString(PREFS_AUTH_TOKEN, null)?.let {
            authViewModel.getCurrentUser(it)
        }

        authViewModel.user.observe({lifecycle}){

            updateMenu(it)
            it?.token?.let {token->
                sharedPreferences.edit{
                    putString(PREFS_AUTH_TOKEN, token)
                }
            } ?:run{
                Toast.makeText(this, "Logged out",Toast.LENGTH_LONG).show()
                sharedPreferences.edit{
                    remove(PREFS_AUTH_TOKEN)
                }
            }
            navController.navigateUp()
        }

    }

    private fun updateMenu(user: User?){
        when(user){
            is User ->{
                binding?.navView?.menu?.clear()
                binding?.navView?.inflateMenu(R.menu.menu_main_user)
            }
            else ->{
                binding?.navView?.menu?.clear()
                binding?.navView?.inflateMenu(R.menu.menu_main_guest)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_logout->{
                authViewModel.logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}