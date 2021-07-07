package com.evgeniykim.poststest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evgeniykim.poststest.adapter.Adapter
import com.evgeniykim.poststest.common.Common
import com.evgeniykim.poststest.retrofit.RetrofitServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.evgeniykim.poststest.dialog.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: Adapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        recyclerView = findViewById(R.id.recyclerList)
        mService = Common.retrofitServices
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            showDialog()
        }

        getPostsList()
    }


    private fun getPostsList() {
        mService.getNewsList().enqueue(object : Callback<MutableList<Model>> {
            override fun onResponse(
                call: Call<MutableList<Model>>,
                response: Response<MutableList<Model>>
            ) {
                adapter = Adapter(baseContext, response.body() as MutableList<Model>)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<MutableList<Model>>, t: Throwable) {

            }

        })

    }

    private fun showDialog() {
        val alertDialog = AlertDialog()
        val manager = supportFragmentManager
        alertDialog.show(manager, "AlertDialog")

    }

}



