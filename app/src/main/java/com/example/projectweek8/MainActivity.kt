package com.example.projectweek8

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.projectweek8.databinding.ActivityMainBinding
import com.example.projectweek8.databinding.ViewBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewBinding: ViewBinding

    var fullfrom = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        move()
        getdata()
        longClick()
    }

    private fun getdata(){

        val getname = intent.getStringExtra("nama")
        val getnumberphone = intent.getStringExtra("phone")
        val getdate = intent.getStringExtra("date")
        val getgender = intent.getStringExtra("gender")

        val fulldata = """
            Name : $getname
            Phone Number : $getnumberphone
            Date : $getdate
            Gender : $getgender
        """.trimIndent()

        binding.TVresult.text = fulldata
        fullfrom = binding.TVresult.toString()

    }

    private fun move(){
       binding.FloatingButton.setOnClickListener {
           startActivity(Intent(this,AddActivity::class.java))
           finish()
       }
    }

    @SuppressLint("SetTextI18n")
    private fun longClick(){
        binding.TVresult.setOnLongClickListener {
             val firstalert = AlertDialog.Builder(this)
             viewBinding = ViewBinding.inflate(layoutInflater)
             firstalert.setView(viewBinding.root)
             val showfirstalert = firstalert.show()
             viewBinding.TvCopy.setOnClickListener {
                 showfirstalert.dismiss()
                 val copy = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                 val data = ClipData.newPlainText("text",binding.TVresult.text.toString())
                 copy.setPrimaryClip(data)
                 Toast.makeText(this, "Text Copied", Toast.LENGTH_SHORT).show()

             }
            viewBinding.TvDelete.setOnClickListener {
                showfirstalert.dismiss()
                val alert = AlertDialog.Builder(this).apply {
                    setIcon(R.drawable.ic_warning)
                    setTitle("Warning !")
                    setMessage("Are you sure you want to delete this form ? ")
                    setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                        binding.TVresult.text = """
                     Name : 
                     Phone Number : 
                     Date :
                     Gender :
                   """.trimIndent()
                    }
                    setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
                }
                alert.show()
            }
             false
        }
    }



}