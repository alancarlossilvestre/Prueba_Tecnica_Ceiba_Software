package com.example.myapplication.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.PostAdapter
import com.example.myapplication.R
import com.example.myapplication.model.APIRest
import com.example.myapplication.model.Post
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_post.*
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PostActivity : AppCompatActivity()  {

    val URLAPI  = "https://jsonplaceholder.typicode.com/"
    lateinit var service: APIRest
    val TAG_LOGS = "Respuesta"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val objectintent : Intent=intent
        var Name = objectintent.getStringExtra("nombre")
        var Phone = objectintent.getStringExtra("phone")
        var Email = objectintent.getStringExtra("email")

        name.text = Name
        phone.text = Phone
        email.text = Email


        recyclerViewPostsResults .layoutManager = LinearLayoutManager(this)
        recyclerViewPostsResults.adapter =  null

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URLAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create<APIRest>(APIRest::class.java)

         getAllPosts()
    }

    fun getAllPosts(){

        service.getAllPosts().enqueue(object: Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                val posts = response?.body()
                Log.i(TAG_LOGS, Gson().toJson(posts))
                recyclerViewPostsResults.adapter = PostAdapter(response!!.body())
            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                t?.printStackTrace()
            }

        })
    }
}

