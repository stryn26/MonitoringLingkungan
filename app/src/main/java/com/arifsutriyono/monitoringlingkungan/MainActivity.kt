package com.arifsutriyono.monitoringlingkungan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.arifsutriyono.monitoringlingkungan.R
//import library untuk komunikasi firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
//import library untuk kotlin
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "KotlinActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun basicReadWrite() {
            //menulis data ke firebase
        val database = Firebase.database
        val myRef = database.getReference("message")
//        myRef.setValue("Hello,World!")
            //akhir blok penulisan data ke firebase
            //membaca data dari firebase
        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<String>()
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })


    }
}