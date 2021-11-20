package com.example.projectweek8

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.example.projectweek8.Model.Data
import com.example.projectweek8.databinding.ActivityAddBinding
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    private lateinit var binding :ActivityAddBinding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapterSpinner()
        putdata()
        back()
        datepicker()

    }

    private fun adapterSpinner(){
        val listitem = arrayOf("Male","Female","waria","Ngondek")
        val adapteritem = ArrayAdapter(this,R.layout.simple_list_item_1,listitem)
        binding.TVgender.setAdapter(adapteritem)

    }

    private fun putdata(){
      binding.ButtonSave.setOnClickListener {
          val name = binding.ETname.text.toString()
          val phone = binding.ETnumberphone.text.toString()
          val date = binding.ETdate.text.toString()
          val gender = binding.TVgender.text.toString()

          startActivity(Intent(this,MainActivity::class.java).apply {
              putExtra("nama",name)
              putExtra("phone",phone)
              putExtra("date",date)
              putExtra("gender",gender)
          })
          finish()
      }
      }
    private fun back(){
        binding.ButtonBack.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    @SuppressLint("WeekBasedYear")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun datepicker(){

        val formatedate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val now = Calendar.getInstance()
        val year = now.get(Calendar.YEAR)
        val month = now.get(Calendar.MONTH)
        val day = now.get(Calendar.DAY_OF_MONTH)

       binding.ETdate.setOnClickListener {
           val datePicker = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view, myear, mMount, mDay ->
//               binding.ETdate.setText("$mDay-${mMount+1}-$myear")
               now.set(Calendar.YEAR,myear)
               now.set(Calendar.MONTH,mMount)
               now.set(Calendar.DAY_OF_MONTH,mDay)
               val result = formatedate.format(now.time)
               binding.ETdate.setText(result)
           },year,month,day
           )
           datePicker.show()
       }
    }



}