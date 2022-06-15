package com.example.appformaciones.usuarios

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.appformaciones.basededatos.AppDB
import com.example.appformaciones.basededatos.Modulo
import com.example.appformaciones.basededatos.Usuario
import com.example.appformaciones.databinding.FragmentIniciosesionBinding

import kotlinx.coroutines.launch
import java.lang.StringBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [iniciosesion.newInstance] factory method to
 * create an instance of this fragment.
 */
class iniciosesion : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var enlace:FragmentIniciosesionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        enlace= FragmentIniciosesionBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enlace.button.setOnClickListener {
            val miusuario= AppDB.getInstancia(requireContext()).usuaioDAO
            val mimodulo= AppDB.getInstancia(requireContext()).moduloDAO
            if (enlace.editTextTextPersonName.text.toString()=="admin" && enlace.editTextTextPassword.text.toString()=="admin"){
                findNavController().navigate( iniciosesionDirections.actionIniciosesionToMenuadministrador())
            }else{
                lifecycleScope.launch {
                    val listausuarios=miusuario.recuperaTodo()
                    if(listausuarios!=null){
                        for (Usu: Usuario in listausuarios){
                           if (Usu.nombre==enlace.editTextTextPersonName.text.toString() &&
                               Usu.contraseña==enlace.editTextTextPassword.text.toString()){
                                findNavController().navigate(iniciosesionDirections.actionIniciosesionToSelectormodulos(Usu,listausuarios.indexOf(Usu)))
                                break
                           }else{
                               Toast.makeText(context, "informacion erronea", Toast.LENGTH_SHORT).show()
                           }
                        }

                    }
                }


            }

        }
    }

}

