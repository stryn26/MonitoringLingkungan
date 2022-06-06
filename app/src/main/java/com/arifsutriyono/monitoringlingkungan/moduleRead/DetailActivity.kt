package com.arifsutriyono.monitoringlingkungan.moduleRead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.arifsutriyono.monitoringlingkungan.Data
import com.arifsutriyono.monitoringlingkungan.MainActivity
import com.arifsutriyono.monitoringlingkungan.R


class DetailActivity :AppCompatActivity() {

    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    lateinit var sTemperature : String
    lateinit var sKelembapan : String

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()



    }
}