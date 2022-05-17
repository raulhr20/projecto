package com.example.appformaciones.administrador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appformaciones.R
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.appformaciones.databinding.FragmentMenuadministradorBinding



/**
 * A simple [Fragment] subclass.
 * Use the [menuadministrador.newInstance] factory method to
 * create an instance of this fragment.
 */
class menuadministrador : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var enlace: FragmentMenuadministradorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        enlace= FragmentMenuadministradorBinding.inflate(inflater,container,false)
        return enlace.root
          }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enlace.irusuarios.setOnClickListener {
            view.findNavController().navigate(menuadministradorDirections.actionMenuadministradorToAdministradorDeUsuarios())
        }
        enlace.irmodulos.setOnClickListener {
            view.findNavController().navigate(menuadministradorDirections.actionMenuadministradorToAdministradormodulos())
        }
        enlace.irtest.setOnClickListener {
            view.findNavController().navigate(menuadministradorDirections.actionMenuadministradorToGestortest())
        }
    }

}