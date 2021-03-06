package com.example.appformaciones.basededatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.appformaciones.R
import com.example.appformaciones.administrador.modulosViewModel
import com.example.appformaciones.usuarios.TestViewModel
import com.example.appformaciones.usuarios.selectormodulosDirections
import kotlin.math.log


class adaptadormodulosusuario(val vm:TestViewModel,val usuario: Usuario):RecyclerView.Adapter<adaptadormodulosusuario.Holder>(){

    private var mostrarboton: Boolean = true
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
        private lateinit var mod: Modulo
        var boton = itemView.findViewById<Button>(R.id.botontest)

        init {



            boton.setOnClickListener {


                itemView.findNavController().navigate(selectormodulosDirections.actionSelectormodulosToTest(mod,usuario))

            }
            itemView.setOnClickListener {
                if(url.text.isNullOrEmpty()){
                    Toast.makeText(itemView.context, "este modulo aun no tiene una lista asociada", Toast.LENGTH_SHORT).show()

                }else{
                    itemView.findNavController().navigate(selectormodulosDirections.actionSelectormodulosToVideoplayer(url.text.toString()))
                }

        }
    }


        fun rellena(modulo:Modulo){
            mostrarboton=true
            nombre.text=modulo.nombre
            nivel.text= modulo.nivel.toString()
            url.text=modulo.url.toString()
            mod=modulo


        }
   
    }

}