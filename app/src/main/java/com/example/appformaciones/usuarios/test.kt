package com.example.appformaciones.usuarios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appformaciones.R
import com.example.appformaciones.administrador.PreguntasViewModel
import com.example.appformaciones.administrador.RespuestasViewModel
import com.example.appformaciones.administrador.modulosViewModel
import com.example.appformaciones.basededatos.*
import com.example.appformaciones.databinding.FragmentAdministradorDeUsuariosBinding
import com.example.appformaciones.databinding.FragmentTestBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class test : Fragment() {
    lateinit var enlace: FragmentTestBinding
    val vmr: RespuestasViewModel by lazy{
        ViewModelProvider(this,).get(RespuestasViewModel::class.java)
    }
    val vm: PreguntasViewModel by lazy{
        ViewModelProvider(this,).get(PreguntasViewModel::class.java)
    }
    private lateinit var datos: testArgs
    private lateinit var preguntas:List<Pregunta>
    private lateinit var modulo: Modulo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            datos=testArgs.fromBundle(it)
        }
        modulo=datos.modelo

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        enlace= FragmentTestBinding.inflate(inflater,container,false)
        return enlace.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val adaptador= adaptadorRespuestas(vmr)
        enlace.respuestas.adapter=adaptador
        enlace.respuestas.layoutManager=LinearLayoutManager(context)
        vm.filtrarlista(modulo.id!!)

        vm.lista.observe(viewLifecycleOwner){
            var pregunta  =it.get(0)
            enlace.preguntas.text=pregunta.pregunta
            pregunta.id?.let { it1 -> vmr.obtenerrespuestas(it1) }
            vmr.lista.observe(viewLifecycleOwner){
                val semilla=System.currentTimeMillis()
            adaptador.lista=it.shuffled(Random(semilla))

                
            }
        }

    }


}