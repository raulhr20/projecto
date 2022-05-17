package com.example.appformaciones.basededatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appformaciones.R
import com.example.appformaciones.administrador.Administrador_de_usuarios
import com.example.appformaciones.administrador.Administrador_de_usuariosDirections
import com.example.appformaciones.administrador.modulosViewModel
import com.example.appformaciones.administrador.usuariosViewModel

class adaptadorusuario(val vm:usuariosViewModel):RecyclerView.Adapter<adaptadorusuario.Holder>() {
    var lista= listOf<Usuario>()
        set(value){
            field=value
            notifyDataSetChanged()
        }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adaptadorusuario.Holder {
        val vista=LayoutInflater.from(parent.context).inflate(R.layout.usuario,parent,false)
        return Holder(vista)
    }

    override fun onBindViewHolder(holder: adaptadorusuario.Holder, position: Int) {
        val usuario=lista[position]
        holder.rellena(usuario)

    }

    override fun getItemCount(): Int =lista.size

    inner class Holder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val nombreusuario=itemView.findViewById<TextView>(R.id.nombreusuario)
        private val contraseñausuario=itemView.findViewById<TextView>(R.id.contraseñausuario)
        private lateinit var usu:Usuario
        init {
            itemView.setOnClickListener {
                itemView.findNavController().navigate(Administrador_de_usuariosDirections.actionAdministradorDeUsuariosToEditorusuarios(usu))
            }
        }

        fun rellena(usuario: Usuario){
            nombreusuario.text=usuario.nombre
            contraseñausuario.text=usuario.contraseña
            usu=usuario
        }
    }

}