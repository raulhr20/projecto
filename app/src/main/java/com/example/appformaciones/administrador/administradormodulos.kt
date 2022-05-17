package com.example.appformaciones.administrador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appformaciones.basededatos.AppDB
import com.example.appformaciones.basededatos.Modulo
import com.example.appformaciones.basededatos.adaptadormoduloadministracion
import com.example.appformaciones.basededatos.adaptadormodulosusuario
import com.example.appformaciones.databinding.FragmentAdministradormodulosBinding
import kotlinx.coroutines.launch

class administradormodulos : Fragment() {
    lateinit var enlace: FragmentAdministradormodulosBinding

    val vm: modulosViewModel by lazy{
        ViewModelProvider(this).get(modulosViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        enlace= FragmentAdministradormodulosBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adaptador=adaptadormoduloadministracion(vm)
        enlace.listamodulos.adapter=adaptador
        enlace.listamodulos.layoutManager=LinearLayoutManager(context)
        val mimodulo= AppDB.getInstancia(requireContext()).moduloDAO
        enlace.aAdir.setOnClickListener{
            lifecycleScope.launch {
                mimodulo.inserta(Modulo(null,enlace.modulo.text.toString(),enlace.nivel.text.toString().toInt(),enlace.url.text.toString()))
            }
        }
        val manejadorGestos= ItemTouchHelper(
            object: ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }


                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    vm.eliminamodulo(viewHolder.absoluteAdapterPosition)
                }

            })
        manejadorGestos.attachToRecyclerView(enlace.listamodulos)

        vm.lista.observe(viewLifecycleOwner){
            adaptador.lista=it
        }

    }

}