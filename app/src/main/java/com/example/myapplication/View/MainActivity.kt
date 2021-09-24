package com.example.myapplication.View

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.UserAdapter
import retrofit2.Retrofit
import com.example.myapplication.model.APIRest
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    val URLAPI = "https://jsonplaceholder.typicode.com/"
    private lateinit var TextSearch: EditText
    private lateinit var itemList: List<User>
    private lateinit var mAdapter: UserAdapter
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        recyclerViewSearchResults.layoutManager = LinearLayoutManager(this)
        recyclerViewSearchResults.adapter = null
        if (recyclerViewSearchResults.adapter == null) {
            LoadMessage()
        }


        var retrofitUser = Retrofit.Builder()
            .baseUrl(URLAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var apiUser = retrofitUser.create(APIRest::class.java)
        var callUser = apiUser.getUser()

        callUser.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
                for (res in response?.body()!!) {
                    Log.d("TAG respuesta", res.id)
                    Log.d("TAG respuesta", res.name)
                    Log.d("TAG respuesta", res.phone)
                    Log.d("TAG respuesta", res.email)
                    Log.d("TAG respuesta", "------------------------------------------")

                }
                recyclerViewSearchResults.adapter = UserAdapter(response.body())

            }

            override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                Log.e("TAG Fallo : ", t.toString())
            }
        })


    }

    fun LoadMessage() {

        val progresD = ProgressDialog(this)
        progresD.setMessage("Cargando")
        progresD.setCancelable(true)
        progresD.show()
        Handler().postDelayed({ progresD.dismiss() }, 2500)

    }




}