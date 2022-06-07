package com.arifsutriyono.monitoringlingkungan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.arifsutriyono.monitoringlingkungan.Data
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var mdatabase: DatabaseReference
    private var dataList = ArrayList<Data>()

    private fun getData(){
        mdatabase.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()

                val Data = dataSnapshot.getValue(Data::class.java)
                dataList.add(Data!!)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mdatabase = FirebaseDatabase.getInstance().getReference("/DHT11/data")

        resetData.setOnClickListener{

        }

        simpan.setOnClickListener{

        }
    }

}