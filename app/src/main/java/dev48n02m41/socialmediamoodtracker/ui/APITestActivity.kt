package dev48n02m41.socialmediamoodtracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dev48n02m41.socialmediamoodtracker.R
import dev48n02m41.socialmediamoodtracker.data.api.APIInterface
import dev48n02m41.socialmediamoodtracker.data.entities.APIDiaryEntryEntity
import dev48n02m41.socialmediamoodtracker.ui.viewmodels.APITestViewModel
import retrofit2.Call
import retrofit2.Response

private lateinit var apiTestViewModel: APITestViewModel
private lateinit var token: String


class APITestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_p_i_test)

        // ViewModel
        apiTestViewModel = ViewModelProvider(this).get(APITestViewModel::class.java)

        getAccessToken()
    }

    private fun getAccessToken() {
        // Retrieve encrypted access token, if it exists.
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        val sharedPreferences = EncryptedSharedPreferences.create(
            "encrypted_shared_prefs",
            masterKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        token =
            sharedPreferences.getString("TOKEN", "Token not found.").toString()

        Log.d(APITestActivity.TAG, "Token: $token")
    }

    fun grabAPIData(view: View) {
        apiGetData()
    }

    private fun apiGetData() {
        val apiGETAll = APIInterface.create().getAll("Bearer $token")

        apiGETAll.enqueue(object : retrofit2.Callback<List<APIDiaryEntryEntity>> {
            override fun onResponse(
                call: Call<List<APIDiaryEntryEntity>>?,
                response: Response<List<APIDiaryEntryEntity>>?
            ) {

                if(response == null) {
                    Log.d(TAG, "Response is null.")
                }
                else {
                    Log.d(TAG, "Response not null.")
                    apiTestViewModel.insertAPIDiaryEntries(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<APIDiaryEntryEntity>>?, t: Throwable?) {
                Log.d(TAG, "API GET onFailure...")
                Log.d(TAG, "" + t?.message)
            }
        })
    }

    companion object {
        private const val TAG = "APITestActivity"
    }
}