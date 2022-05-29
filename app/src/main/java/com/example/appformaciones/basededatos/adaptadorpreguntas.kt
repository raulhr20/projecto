package com.example.appformaciones.basededatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appformaciones.R
import com.example.appformaciones.administrador.PreguntasViewModel
import com.example.appformaciones.usuarios.selectormodulosDirections
import kotlin.coroutines.coroutineContext

class adaptadorpreguntas(val vm:PreguntasViewModel):RecyclerView.Adapter<adaptadorpreguntas.Holder>() {
    var lista= listOf<Pregunta>()
        set(value){
            field=value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val vista=LayoutInflater.from(parent.context).inflate(R.layout.fragment_itempregunta,parent,false)
        return Holder(vista)
    }

    override fun onBindViewHolder(holder: adaptadorpreguntas.Holder, position: Int) {
        val pregunta= lista[position]
        holder.rellena(pregunta)

    }

    override fun getItemCount(): Int =lista.size
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val preguntatext=itemView.findViewById<TextView>(R.id.pregunta)
    private val modulotext=itemView.findViewById<TextView>(R.id.modulopregunta)
        lateinit var pre: Pregunta
        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, pre.idmodulo.toString(), Toast.LENGTH_SHORT).show()
               // itemView.findNavController().navigate(selectormodulosDirections.actionSelectormodulosToVideoplayer(url.text.toString()))

            }
        }
        fun rellena(pregunta: Pregunta){
            preguntatext.text=pregunta.pregunta
            modulotext.text=pregunta.idmodulo.toString()
            pre=pregunta

        }

    }

    fun refresca(){
        notifyDataSetChanged()
    }


}