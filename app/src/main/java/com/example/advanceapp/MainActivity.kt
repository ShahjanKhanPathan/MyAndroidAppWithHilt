package com.example.advanceapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.example.advanceapp.base.BaseActivity
import com.example.advanceapp.databinding.ActivityMainBinding
import com.example.advanceapp.dataclass.NetworkClass
import com.example.advanceapp.vm.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    val viewModal : AuthViewModel by viewModels()

    override fun initFun() {
        calViewModal()
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            val jsonObject = JSONObject()
            try {
                jsonObject.put("email",email)
                jsonObject.put("password",password)
            }catch (e : JSONException){
                Log.d("JsonError",e.message.toString())
            }
            var body: RequestBody =
                jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

            viewModal.getLogin(body)
        }
    }

    override fun getViewBind() = ActivityMainBinding.inflate(layoutInflater)

    private fun calViewModal(){
        viewModal.isAuthDone.observe(this, Observer {
            binding.progressBar.hideVisibility()
            when(it){
                is NetworkClass.onLoading -> {
                    binding.progressBar.showVisibility()
                }

                is NetworkClass.onSuccess ->{
                    if (it.data?.code == 1){
                        customToast(it.data.message!!)
                        startActivity(Intent(this, MainActivity::class.java))

                    } else if(it.data?.code == 0){
                    showSnackBar(binding.mainActivity,it.data.message!!)
                    }
                }

                is NetworkClass.onFailure ->{
                    Toast.makeText(applicationContext, "Not Working...", Toast.LENGTH_SHORT).show()
                }

            }

        })
    }

    @SuppressLint("ResourceType")
    override fun getToolbar() {
        val toolbar = findViewById<Toolbar>(R.layout.custom_toolbar)

        val title = findViewById<TextView>(R.id.txtTitle_toolbar)
        title.text = getString(R.string.login_page)

        setSupportActionBar(toolbar)

        val image = findViewById<ImageView>(R.id.imgLogOut_toolbar)
        image.showVisibility()
        image.setBackgroundResource(R.drawable.ic_logout);

        image.setOnClickListener {
            showSnackBar(binding.mainActivity,"Image Clicked...")
        }
    }



}