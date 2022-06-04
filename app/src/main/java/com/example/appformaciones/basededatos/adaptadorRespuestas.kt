package com.example.appformaciones.basededatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appformaciones.R
import com.example.appformaciones.administrador.RespuestasViewModel

class adaptadorRespuestas(val vm:RespuestasViewModel):RecyclerView.Adapter<adaptadorRespuestas.Holder>() {
    var lista=listOf<Respuesta>()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adaptadorRespuestas.Holder {
        val vista=LayoutInflater.from(parent.context).inflate(R.layout.respuestas,parent,false)
        return Holder(vista)
    }
    override fun onBindViewHolder(holder: adaptadorRespuestas.Holder, position: Int) {
        val respuesta=lista[position]
        holder.rellena(respuesta)

    }
    override fun getItemCount(): Int =lista.size

    inner class Holder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val respuestatext=itemView.findViewById<TextView>(R.id.respuesta)
        private lateinit var res:Respuesta
        init {
            itemView.setOnClickListener {
                 }
        }

        fun rellena(respuesta: Respuesta){
            respuestatext.text=respuesta.respuesta
            res=respuesta
        }
    }
}