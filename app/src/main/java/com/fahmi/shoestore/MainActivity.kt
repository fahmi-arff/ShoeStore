package com.fahmi.shoestore

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.fahmi.shoestore.databinding.ActivityMainBinding
import com.fahmi.shoestore.screen.login.LoginFragmentDirections
import com.fahmi.shoestore.screen.shoelist.ShoesListFragment


class MainActivity : AppCompatActivity() {
    private lateinit var pref : SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup for Login save state
        pref = SharedPreference(this)
        var notOnApp : Boolean = true

        val emailId = pref.getValueString("USER")
        val passId = pref.getValueString("PASS")

        // Setup Data binding and navcontroller
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.myNavHostFragment)

        if(savedInstanceState != null) {
            // to prevent crashing when configuration changes when app is open
            notOnApp = savedInstanceState.getBoolean("NOT_ON_APP")
        }

        // Check the sharedPreferences save the key login if yes then skip the onboard
        if(emailId != "" && passId != "" && notOnApp){
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToShoesListFragment())
        }

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return  navController.navigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putBoolean("NOT_ON_APP", false )
    }
}