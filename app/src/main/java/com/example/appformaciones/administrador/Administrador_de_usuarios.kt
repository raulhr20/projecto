package com.example.appformaciones.administrador

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appformaciones.R
import com.example.appformaciones.basededatos.AppDB
import com.example.appformaciones.basededatos.Usuario
import com.example.appformaciones.basededatos.adaptadorusuario
import com.example.appformaciones.databinding.FragmentAdministradorDeUsuariosBinding
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class Administrador_de_usuarios : Fragment() {
    lateinit var enlace:FragmentAdministradorDeUsuariosBinding
    val vm: usuariosViewModel by lazy{
        ViewModelProvider(this).get(usuariosViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        enlace= FragmentAdministradorDeUsuariosBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val adaptador=adaptadorusuario(vm)
        enlace.listausuarios.adapter=adaptador
        enlace.listausuarios.layoutManager=LinearLayoutManager(context)
        val miusuariodao= AppDB.getInstancia(requireContext()).usuaioDAO
        enlace.botonInsertar.setOnClickListener{
            lifecycleScope.launch {
                if (enlace.usuario.text.isNullOrBlank()||enlace.contraseA.text.isNullOrBlank()){
                    Toast.makeText(context, "rellena toda la informacion por favor", Toast.LENGTH_SHORT).show()
                }else{
                try {

                    if (miusuariodao.isRowIsExist(enlace.usuario.text.toString())){
                        Toast.makeText(context, "ese nombre de usuario ya esta en uso", Toast.LENGTH_SHORT).show()
                    }else{
                        miusuariodao.inserta(Usuario(null,1,0,enlace.usuario.text.toString(),enlace.contraseA.text.toString()))

                    }

                    } catch (exception: SQLiteConstraintException) {
                    Toast.makeText(context, "a ocurrido un error", Toast.LENGTH_SHORT).show()
                }
        }


                }
        }
        val manejadorGestos=ItemTouchHelper(
            object :ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }


                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    vm.eliminausuario(viewHolder.absoluteAdapterPosition)
                }

            })
        manejadorGestos.attachToRecyclerView(enlace.listausuarios)

        vm.lista.observe(viewLifecycleOwner){
            adaptador.lista=it
        }




    }

}