package com.example.android.retrofit_exo4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGet.setOnClickListener({
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create<BlogService>(BlogService::class.java)
            service.getPost("1").enqueue(object: Callback<Post> {
                override fun onResponse(call: Call<Post>, response: retrofit2.Response<Post>?) {
                    if ((response != null) && (response.code() == 200)) {
                        var post = response.body()
                        txtTitle.setText(post!!.title)
                        txtBody.setText(post.body)
                        Toast.makeText(this@MainActivity, "Succès", Toast.LENGTH_LONG).show()

                    }

                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Echec", Toast.LENGTH_LONG).show()
                }
            })
        })
    }
}