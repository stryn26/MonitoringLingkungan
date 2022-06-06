package com.arifsutriyono.monitoringlingkungan.moduleRead

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
// import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arifsutriyono.monitoringlingkungan.Data
import com.arifsutriyono.monitoringlingkungan.R

class Adapter (
    private var data : List<Data>,
    private val listener : (Data) -> Unit
):RecyclerView.Adapter<Adapter.LeagueViewHolder>(){

    lateinit var ContextAdapter : Context

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ContextAdapter = parent.context
        val inflatedView:View = layoutInflater.inflate(R.layout.row_data,parent,false)

        return LeagueViewHolder(inflatedView)
    }


    override fun onBindViewHolder(holder: Adapter.LeagueViewHolder, position: Int) {
         holder.bindItem(data[position],listener,ContextAdapter,position)
    }

    override fun getItemCount():Int = data.size

    class LeagueViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val temperature:TextView = view.findViewById(R.id.nilaiTemperature)
        private val kelembapan:TextView = view.findViewById(R.id.nilaiHumidity)

        fun bindItem(
            data: Data,
            listener: (Data) -> Unit,
            ContextAdaptr: Context,
            position: Int )
        {
            temperature.text = data.temperature
            kelembapan.text = data.kelembapan

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}