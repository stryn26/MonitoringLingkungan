package com.arifsutriyono.monitoringlingkungan.moduleRead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.arifsutriyono.monitoringlingkungan.Data
import com.arifsutriyono.monitoringlingkungan.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import kotlinx.android.synthetic.main.activity_read.*

class ReadActivity:AppCompatActivity() {
    lateinit var mDatabase:DatabaseReference
    private var dataList = ArrayList<Data>()

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDatabase = FirebaseDatabase.getInstance().getReference("DHT11/data")
        rv_data.layoutManager = LinearLayoutManager(this)
        getData()
    }

    private fun getData(){
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getDataSnapshot in dataSnapshot.getChildren()){
                    val Data= getDataSnapshot.getValue(Data::class.java!!)
                    dataList.add(Data!!)
                }


                rv_data.adapter = Adapter(dataList){
                    val intent =Intent(this@ReadActivity,DetailActivy::class.java).putExtra("data",it)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ReadActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}