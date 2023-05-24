package com.example.advanceapp.base

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseActivity<VB : ViewBinding>() : AppCompatActivity() {
    lateinit var binding : VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBind()
        setContentView(binding.root)
        initFun()
        getToolbar()
    }

    abstract fun initFun()

    abstract fun getViewBind(): VB

    abstract fun getToolbar()

   protected fun customToast(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showSnackBar(view: View,message: String) {
        val snack = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snack.setAction("Close") {
            snack.dismiss()
        }
        snack.show()
    }

    protected fun View.showVisibility(){
       visibility = View.VISIBLE
    }

    protected fun View.hideVisibility(){
        visibility = View.GONE
    }

}