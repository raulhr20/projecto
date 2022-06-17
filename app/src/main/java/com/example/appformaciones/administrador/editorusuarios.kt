package com.example.appformaciones.administrador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.appformaciones.R
import com.example.appformaciones.basededatos.AppDB
import com.example.appformaciones.basededatos.Usuario
import com.example.appformaciones.databinding.FragmentEditorusuariosBinding
import com.example.appformaciones.databinding.FragmentSelectormodulosBinding
import com.example.appformaciones.usuarios.selectormodulosArgs
import kotlinx.coroutines.launch


class editorusuarios : Fragment() {
    private lateinit var usu: Usuario
    private lateinit var datos: editorusuariosArgs
    lateinit var enlace: FragmentEditorusuariosBinding

    val vm: usuariosViewModel by lazy{
        ViewModelProvider(this).get(usuariosViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let{
            datos=editorusuariosArgs.fromBundle(it)
        }
        usu=datos.usuarioaeditar
        enlace= FragmentEditorusuariosBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao= AppDB.getInstancia(requireContext()).usuaioDAO
        enlace.nombre.setText(usu.nombre)
        enlace.contraseAeditor.setText(usu.contraseña)
        enlace.actualizar.setOnClickListener {
            lifecycleScope.launch {

                if (dao.isRowIsExist(enlace.nombre.text.toString())){
                    Toast.makeText(context, "ese usuario ya existe", Toast.LENGTH_SHORT).show()
                }else{
                    usu.nombre=enlace.nombre.text.toString()
                    usu.contraseña=enlace.contraseAeditor.text.toString()
                    dao.modifica(usu)
                }



            }
            findNavController()
            }
    }
}