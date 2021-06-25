package com.example.myapiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapiapp.model.PhotoItem
import com.example.myapiapp.networking.ApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: PhotoAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        getMyPhotos()
    }

    private fun getMyPhotos(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getPhotos()

        retrofitData.enqueue(object : Callback<List<PhotoItem>?> {
            override fun onResponse(
                call: Call<List<PhotoItem>?>,
                response: Response<List<PhotoItem>?>
            ) {
                val responseBody = response.body()

                myAdapter = responseBody?.let { PhotoAdapter(baseContext, it) }!!
                myAdapter.notifyDataSetChanged()
                recyclerView.adapter = myAdapter

            }

            override fun onFailure(call: Call<List<PhotoItem>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}


