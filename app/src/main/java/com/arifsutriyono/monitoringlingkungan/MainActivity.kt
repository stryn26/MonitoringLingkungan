package com.arifsutriyono.monitoringlingkungan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import com.arifsutriyono.monitoringlingkungan.databinding.ActivityMainBinding

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    private lateinit var database :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding.refresh.setOnClickListener{
            readData()
        }
    }

    private fun readData(){
        database = FirebaseDatabase.getInstance().getReference("DHT11")
        database.child("data").get().addOnSuccessListener {
            if (it.exists()){
                val temperature = it.child("temperature").value
                val humidity = it.child("kelembapan").value

                binding.tvSuhu.text = temperature.toString()
                binding.tvKelembapan.text = humidity.toString()

            }else {
                Toast.makeText(this,"Gagal Membaca Data",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }

}

