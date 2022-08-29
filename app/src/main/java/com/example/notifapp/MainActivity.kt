package com.example.notifapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.notifapp.ApiServices.APIServiceUser
import com.example.notifapp.ApiServices.APIServiceUsers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

        val testButton: Button = findViewById(R.id.buttonTest) as Button
        testButton.setOnClickListener {
            val intent = Intent( this, dashboard::class.java)
            startActivity(intent)
        }
        val usernameText: EditText = findViewById(R.id.usernameEdit) as EditText


        val logIn: Button = findViewById(R.id.logIn) as Button
        logIn.setOnClickListener {
            getUserData(usernameText.text.toString())
        }

        usernameText.setOnClickListener {
            var str=""
            usernameText.text=str.toEditable() //TODO

        }
        val passwordText: EditText = findViewById(R.id.passwordEdit) as EditText
        passwordText.setOnClickListener {
            var str=""
            passwordText.text=str.toEditable() //TODO

            Log.d("Filip","CLICKED PASS")

        }








    }







    fun getUsersData() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://revu-notif.duckdns.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Create Service
        val service = retrofit.create(APIServiceUsers::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            // Do the GET request and get response
            val response = service.getUsers()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val items = response.body()
                    if (items != null) {
                        for (i in 0 until items.count()) {
                            val id = items[i].id ?: "N/A"
                            Log.d("ID: ", id)

                            val password = items[i].password?: "N/A"
                                Log.d("Password: ", password)


                            val unique_hash = items[i].unique_hash ?: "N/A"
                            Log.d("unique_hash: ", unique_hash)

                            val user_name = items[i].user_name ?: "N/A"
                            Log.d("user_name: ", user_name)

                        }
                    }

                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }




    private fun getUserData(user:String) {
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://revu-notif.duckdns.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(APIServiceUser::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            // Do the GET request and get response
            val response = service.getUser(user)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    var user_hash=""
                    var user_password=""
                    val items = response.body()
                    if (items != null) {

                        val id = items.id ?: "N/A"
                        Log.d("ID: ", id)

                        val password = items.password?: "N/A"
                        Log.d("Password: ", password)
                        user_password=password

                        val unique_hash = items.unique_hash ?: "N/A"
                        Log.d("unique_hash: ", unique_hash)
                        user_hash=unique_hash

                        val user_name = items.user_name ?: "N/A"
                        Log.d("user_name: ", user_name)

                    }
                    val passwordText: EditText = findViewById(R.id.passwordEdit) as EditText

                    if(passwordText.text.toString() == user_password){

                        Log.d("user_name: ", "TOCAN PASS")
                        val intent = Intent(this@MainActivity, dashboard::class.java)

                        intent.putExtra("json_user", user_hash)
                        startActivity(intent)

                    }
                    else{

                        Log.d("user_name: ", "KRIVI PASS")

                    }


                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }


}


