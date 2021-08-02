package com.example.projectweek8

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.projectweek8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

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
            val alert = AlertDialog.Builder(this).apply {
                setIcon(R.drawable.ic_warning)
                setTitle("Peringatan ! ")
                setMessage("Apakah anda yakin ingin menghapus form ini ? ")
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
             false
        }
    }

}