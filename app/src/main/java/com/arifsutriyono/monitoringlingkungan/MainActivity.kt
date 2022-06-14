package com.arifsutriyono.monitoringlingkungan

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arifsutriyono.monitoringlingkungan.databinding.ActivityMainBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding  //digunakan untuk melakukan binding ke layout xml
    private lateinit var database :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding.refresh.setOnClickListener{
            readData()//untuk melakukan respon ketika di klik kemudian akan melakukan read data
        }

        setupLineChartData()

    }

    private fun readData(){
            database.get().addOnSuccessListener { //dalam kode ini akan membaca data , ketika sukses dalam pembacaan maka akan di eksekusi kode selanjutnya
            if (it.exists()) {
                val temperature = it.child("temperature").value //mengambil data temperatur dari firebase
                val humidity = it.child("kelembapan").value //mengambil data kelembapan dari firebase

                binding.tvSuhu.text = temperature.toString() //melakukan binding ke layout kemudian merubah string yang di tampilkan menjadi nilai temperatur
                binding.tvKelembapan.text = humidity.toString() //melakukan binding ke layout kemudian merubah string yang di tampilkan menjadi nilai kelembapan

                Toast.makeText(this,"berhasil mendapatkan data", Toast.LENGTH_SHORT).show() //ketika berhasil akan menampilkan pesan di bawah ini

            } else {
                Toast.makeText(this, "Gagal Membaca Data", Toast.LENGTH_SHORT).show() //ketika data tidak ada maka ini akan menampilkan pesan
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show() //ketika fungsi gagal maka ini akan di eksekusi
        }
    }

    private fun setupLineChartData(){ //menampilkan data ke bentuk chart
        val yVals = ArrayList<Entry>()

        yVals.add(Entry(0f, 30f, "0")) //data dummy yang digunakan untuk proses pengembangan , akan tetapi data dari firebase belum dapat digunakan karena pembacaan dan penyimpanan ke dalam csv tidak dapat dilakukan
        yVals.add(Entry(1f, 2f, "1"))
        yVals.add(Entry(2f, 4f, "2"))
        yVals.add(Entry(3f, 6f, "3"))
        yVals.add(Entry(4f, 8f, "4"))
        yVals.add(Entry(5f, 10f, "5"))
        yVals.add(Entry(6f, 22f, "6"))
        yVals.add(Entry(7f, 12.5f, "7"))
        yVals.add(Entry(8f, 22f, "8"))
        yVals.add(Entry(9f, 32f, "9"))
        yVals.add(Entry(10f, 54f, "10"))
        yVals.add(Entry(11f, 28f, "11"))

        val set1: LineDataSet
        set1 = LineDataSet(yVals, "DataSet 1")

        set1.color = Color.BLUE //melakukan setting terhadap chart yang ditampilkan
        set1.setCircleColor(Color.BLUE)
        set1.lineWidth = 1f
        set1.circleRadius = 3f
        set1.setDrawCircleHole(false)
        set1.valueTextSize = 0f
        set1.setDrawFilled(false)

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data = LineData(dataSets)

        // set data
        lineChart.setData(data)
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.setPinchZoom(true)
        lineChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
        lineChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
        lineChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        //lineChart.setDrawGridBackground()
        lineChart.xAxis.labelCount = 11
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    }

}

