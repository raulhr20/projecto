package com.example.appformaciones.administrador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appformaciones.R
import com.example.appformaciones.basededatos.*
import com.example.appformaciones.databinding.FragmentAdministradorpreguntasBinding
import kotlinx.coroutines.launch
import java.util.*


class gestortest : Fragment() {
    lateinit var enlace:FragmentAdministradorpreguntasBinding

    val vm:PreguntasViewModel by lazy{
        ViewModelProvider(this).get(PreguntasViewModel::class.java)
    }
    val vmm:modulosViewModel by lazy{
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
        enlace= FragmentAdministradorpreguntasBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adaptador=adaptadorpreguntas(vm)

        val adaptadormodulo=adaptadormoduloadministracion(vmm)
        val adaptadorsninermodulos= context?.let { ArrayAdapter<Modulo>(it,android.R.layout.simple_spinner_item) }



        enlace.listapreguntas.adapter=adaptador
        enlace.listapreguntas.layoutManager=LinearLayoutManager(context)
        val daopreguntas=AppDB.getInstancia(requireContext()).preguntaDAO
        val daorespuestas=AppDB.getInstancia(requireContext()).respuestaDAO
        enlace.insertar.setOnClickListener {
            lifecycleScope.launch {
                var idpregunta=daopreguntas.inserta(Pregunta(null,enlace.pregunta.text.toString(),(enlace.modulos.selectedItem as Modulo).id))
                Toast.makeText(context, idpregunta.toString(), Toast.LENGTH_SHORT).show()
                daorespuestas.inserta(Respuesta(null,enlace.respuestacorrecta.text.toString(),true,idpregunta))
                daorespuestas.inserta(Respuesta(null,enlace.respuesta2.text.toString(),false,idpregunta))
                daorespuestas.inserta(Respuesta(null,enlace.respuesta3.text.toString(),false,idpregunta))


            }
        }

        enlace.filtro.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                (enlace.filtro.selectedItem as Modulo).id?.let { vm.filtrarlista(it) }
                vm.lista.observe(viewLifecycleOwner){
                    adaptador.refresca()
                    adaptador.lista=it
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

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
                    vm.eliminapregunta(viewHolder.absoluteAdapterPosition)
                }

            })
        manejadorGestos.attachToRecyclerView(enlace.listapreguntas)

        vm.lista.observe(viewLifecycleOwner){
            adaptador.lista=it
        }
        vmm.lista.observe(viewLifecycleOwner){
            adaptadormodulo.lista=it
            if (adaptadorsninermodulos != null) {
                adaptadorsninermodulos.addAll(adaptadormodulo.lista)
            }
            enlace.modulos.adapter=adaptadorsninermodulos
            enlace.filtro.adapter=adaptadorsninermodulos
        }
    }

}