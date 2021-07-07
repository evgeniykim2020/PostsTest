package com.evgeniykim.poststest.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.evgeniykim.poststest.Model
import com.evgeniykim.poststest.R
import com.evgeniykim.poststest.adapter.Adapter
import com.evgeniykim.poststest.common.Common
import com.evgeniykim.poststest.retrofit.RetrofitServices
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AlertDialog : DialogFragment() {

    lateinit var mService: RetrofitServices


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var view: View? = null

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            view = inflater.inflate(R.layout.dialog_post, null)

            builder.setView(view)
                .setPositiveButton("SUBMIT", DialogInterface.OnClickListener {
                        dialogInterface, i -> newPost(); dialog?.dismiss() })

            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")



    }

    private fun newPost() {

        var titleNew: String = ""
        var bodyNew: String = ""
        val titleInput: EditText? = dialog?.findViewById(R.id.title_input)
        val textInput: EditText? = dialog?.findViewById(R.id.post_input)

        titleNew = titleInput?.text.toString()
        bodyNew = textInput?.text.toString()

        mService = Common.retrofitServices
        mService.postDataFields(titleNew, bodyNew, 1).enqueue(object : Callback<Model> {
            override fun onResponse(
                    call: Call<Model>,
                    response: Response<Model>
            ) {

                if (response.isSuccessful) {
                    Log.i("MyRetro", "post submitted to API." + response.body().toString() + response.code());
                }

            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                Log.e("MyRetro", "Unable to submit post to API.");
            }

        })

        var bundle: Bundle? = null
        bundle?.putString(titleNew, bodyNew)




    }

}