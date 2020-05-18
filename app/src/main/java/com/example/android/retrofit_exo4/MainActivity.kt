package com.example.android.retrofit_exo4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<TacheService>(TacheService::class.java)
        val request= service.get_list()

        request.enqueue(object: Callback<List<Tache>> {
            override fun onFailure(call: Call<List<Tache>>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Toast.makeText(this@MainActivity, "Echec", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Tache>>, response: retrofit2.Response<List<Tache>>?) {
                if ((response != null) && (response.code() == 200)) {
                    var liste_taches = response.body()
                    //la liste des interventions
                    var listView: ListView?
                    listView = findViewById(R.id.listView)
                    var adapter = listAdapter( this@MainActivity, liste_taches as ArrayList<Tache>)
                    listView?.adapter = adapter
                    adapter?.notifyDataSetChanged()

                    Toast.makeText(this@MainActivity, "Succès", Toast.LENGTH_LONG).show()

                }

            }

        })
    }
}