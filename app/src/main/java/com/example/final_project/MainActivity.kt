package com.example.final_project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.final_project.data.CitiesItem
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.fragment_overview_details.*


class MainActivity : AppCompatActivity() {
    val cityList: MutableList<CitiesItem?> = mutableListOf()

    private lateinit var navController: NavController

      private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val singIn: Button = findViewById(R.id.button)
      //  FirebaseAuth.getInstance().signOut()
        if (FirebaseAuth.getInstance().currentUser == null) {
            singIn.setOnClickListener {
                // Choose authentication providers
                Toast.makeText(this, "btn clicked", Toast.LENGTH_SHORT).show()
                singInIntent()

            }
        } else {
            Toast.makeText(
                this,
                "alrdy logdin ${FirebaseAuth.getInstance().currentUser?.displayName}",
                Toast.LENGTH_SHORT
            ).show()


            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.overview) as NavHostFragment
            navController = navHostFragment.navController
            setupActionBarWithNavController(navController)
        }
    }

    private fun singInIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )
        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val responce = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            // ...
        } else {
            //  Sign in failed. If response is null the user canceled the
            ///sign-in flow using the back button. Otherwise check
            //response.getError().getErrorCode() and handle the error.
            //...
        }


    }
}

