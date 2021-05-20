package dev48n02m41.socialmediamoodtracker.ui

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import dev48n02m41.socialmediamoodtracker.R
import kotlinx.android.synthetic.main.activity_main.*


private lateinit var textViewHeader: TextView
private lateinit var btnAsk: Button
private lateinit var btnList: Button
private lateinit var account: Auth0
private lateinit var client: String
private lateinit var domain: String
private lateinit var encryptedSharedPreferences: SharedPreferences

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        handleUI()
        prepareEncryptedSharedPreferences()
        animate()

        client = getString(R.string.com_auth0_client_id)
        domain = getString(R.string.com_auth0_domain)

        // Auth0 account object
        account = Auth0(
            client, domain
        )

        showUserProfile(encryptedSharedPreferences.getString("ACCESS_TOKEN", "No access token found").toString())
    }

    private fun prepareEncryptedSharedPreferences() {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        encryptedSharedPreferences = EncryptedSharedPreferences.create(
            "encrypted_shared_prefs",
            masterKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun handleUI() {
        textViewHeader = findViewById(R.id.header1)
        btnAsk = findViewById(R.id.btn_ask)
        btnList = findViewById(R.id.btn_list)
    }

    private fun animate() {
        ObjectAnimator.ofFloat(textViewHeader, "scaleX", 1.015f).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(textViewHeader, "scaleY", 1.01f).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }

        ObjectAnimator.ofFloat(btnAsk, "scaleX", 1.01f).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(btnAsk, "scaleY", 1.01f).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(btnList, "scaleX", 1.01f).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(btnList, "scaleY", 1.01f).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
    }

    private fun loginWithBrowser() {
        // Setup the WebAuthProvider, using the custom scheme and scope.

        WebAuthProvider.login(account)
            .withScheme("app")
            .withScope("openid profile email")
            // Launch the authentication passing the callback where the results will be received
            .start(this, object : Callback<Credentials, AuthenticationException> {
                // Called when there is an authentication failure
                override fun onFailure(exception: AuthenticationException) {
                    // Something went wrong!
                    Log.d(MainActivity.TAG, "Bad login attempt.")
                    showUserProfile(encryptedSharedPreferences.getString("ACCESS_TOKEN", "No access token found").toString()) // refresh
                }

                // Called when authentication completed successfully
                override fun onSuccess(credentials: Credentials) {
                    // Get the access token from the credentials object.
                    // This can be used to call APIs
                    val token = credentials.idToken
                    val accessToken = credentials.accessToken

                    with(encryptedSharedPreferences.edit()) {
                        putString("TOKEN", token)
                        putString("ACCESS_TOKEN", accessToken)
                        apply()
                    }

                    showUserProfile(encryptedSharedPreferences.getString("ACCESS_TOKEN", "No access token found").toString()) // refresh
                }
            })
    }

    private fun logout() {
        WebAuthProvider.logout(account)
            .withScheme("app")
            .start(this, object : Callback<Void?, AuthenticationException> {
                override fun onSuccess(payload: Void?) {
                    // The user has been logged out!
                }

                override fun onFailure(error: AuthenticationException) {
                    // Something went wrong!
                }
            })
    }

    private fun showUserProfile(accessToken: String) {
        var client = AuthenticationAPIClient(account)

        // With the access token, call `userInfo` and get the profile from Auth0.
        client.userInfo(accessToken)
            .start(object : Callback<UserProfile, AuthenticationException> {
                override fun onFailure(exception: AuthenticationException) {
                    // Something went wrong!
                    Log.d(TAG, "Failed to show user profile details.")
                    Log.d(TAG, exception.getCode())
                    textViewLoggedInAs.text = "Not logged in."
                }

                override fun onSuccess(profile: UserProfile) {
                    // We have the user's profile!
                    val email = profile.email
                    val name = profile.name

                    Log.d(TAG, "User name: $name")
                    textViewLoggedInAs.text = "Logged in as $name"
                }
            })
    }

    fun login(view: View) {
        loginWithBrowser()
    }

    fun openAskActivity(view: View) {
        val x = Intent(this, AskActivity::class.java)
        startActivity(x)
    }

    fun openMoodLogListActivity(view: View) {
        val x = Intent(this, MoodLogListActivity::class.java)
        startActivity(x)
    }

    fun openAPITestActivity(view: View) {
        val x = Intent(this, APITestActivity::class.java)
        startActivity(x)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}