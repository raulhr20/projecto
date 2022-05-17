package com.example.appformaciones.basededatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.appformaciones.R
import com.example.appformaciones.administrador.modulosViewModel
import com.example.appformaciones.usuarios.selectormodulosDirections


class adaptadormodulosusuario(val vm: modulosViewModel):RecyclerView.Adapter<adaptadormodulosusuario.Holder>(){
    var lista= listOf<Modulo>()
        set(value){
            field=value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val vista= LayoutInflater.from(parent.context).inflate(R.layout.modulousuario,parent,false)
        return Holder(vista)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val modulo= lista[position]
        holder.rellena(modulo)

    }

    override fun getItemCount(): Int =lista.size

    /////////////////////////////////   HOLDER  ////////////////////////////////////////////////////
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val nombre=itemView.findViewById<TextView>(R.id.nombreusuario)
        private val nivel=itemView.findViewById<TextView>(R.id.contraseñausuario)
        private val url=itemView.findViewById<TextView>(R.id.urlmodulo)

        init {
        itemView.setOnClickListener {

           itemView.findNavController().navigate(selectormodulosDirections.actionSelectormodulosToVideoplayer(url.text.toString()))

        }
    }


        fun rellena(modulo:Modulo){
            nombre.text=modulo.nombre
            nivel.text= modulo.nivel.toString()
            url.text=modulo.url.toString()
        }
    }
    fun refresca(){
        notifyDataSetChanged()
    }
}