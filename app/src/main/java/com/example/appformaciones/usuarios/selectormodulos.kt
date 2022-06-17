package com.example.appformaciones.usuarios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appformaciones.administrador.modulosViewModel
import com.example.appformaciones.administrador.usuariosViewModel
import com.example.appformaciones.basededatos.Usuario
import com.example.appformaciones.basededatos.adaptadormodulosusuario
import com.example.appformaciones.databinding.FragmentSelectormodulosBinding


class selectormodulos : Fragment() {
    private lateinit var usu: Usuario
    private lateinit var datos: selectormodulosArgs
    lateinit var enlace:FragmentSelectormodulosBinding

    val vm: modulosViewModel by lazy{
        ViewModelProvider(this,).get(modulosViewModel::class.java)
    }
    val vmt: TestViewModel by lazy{
        ViewModelProvider(this,).get(TestViewModel::class.java)
    }
    val vmu: usuariosViewModel by lazy{
        ViewModelProvider(this,).get(usuariosViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        arguments?.let{
            datos=selectormodulosArgs.fromBundle(it)
        }
        enlace= FragmentSelectormodulosBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        usu=datos.usuario
        super.onViewCreated(view, savedInstanceState)
        var adaptador= adaptadormodulosusuario(vmt,usu)
        var ubicacion = datos.id
        vm.cargalistapornivel(usu.nivel!!)
        enlace.listamodulos.adapter=adaptador
        enlace.listamodulos.layoutManager=LinearLayoutManager(context)
        enlace.minivel.text=usu.nivel.toString()


        vmu.lista.observe(viewLifecycleOwner){
            usu=it[ubicacion]
            adaptador= adaptadormodulosusuario(vmt,usu)
            vm.cargalistapornivel(usu.nivel!!)
            vm.lista.observe(viewLifecycleOwner){
                adaptador.lista=it
                enlace.listamodulos.adapter=adaptador
                enlace.listamodulos.layoutManager=LinearLayoutManager(context)
            }
            enlace.minivel.text=usu.nivel.toString()


        }

    }


}