package com.example.appformaciones.basededatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.appformaciones.R
import com.example.appformaciones.administrador.administradormodulos
import com.example.appformaciones.administrador.administradormodulosDirections
import com.example.appformaciones.administrador.menuadministradorDirections
import com.example.appformaciones.administrador.modulosViewModel


class adaptadormoduloadministracion(val vm: modulosViewModel):RecyclerView.Adapter<adaptadormoduloadministracion.Holder>(){
    var lista= listOf<Modulo>()
        set(value){
            field=value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val vista= LayoutInflater.from(parent.context).inflate(R.layout.moduloadministrador,parent,false)
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
        private lateinit var yo:Modulo

        init {
        itemView.setOnClickListener {

           itemView.findNavController().navigate(administradormodulosDirections.actionAdministradormodulosToEditarModulo(
               yo.id!!,yo.nombre!!,yo.url!!,yo.nivel!!))

        }
    }



        fun rellena(modulo:Modulo){
            nombre.text=modulo.nombre
            nivel.text= modulo.nivel.toString()
            url.text=modulo.url.toString()
            yo=modulo
        }
    }

}