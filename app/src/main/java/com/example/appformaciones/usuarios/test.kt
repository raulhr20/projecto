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
import com.example.appformaciones.R
import com.example.appformaciones.administrador.PreguntasViewModel
import com.example.appformaciones.administrador.modulosViewModel
import com.example.appformaciones.basededatos.Modulo
import com.example.appformaciones.basededatos.Pregunta
import com.example.appformaciones.basededatos.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class test : Fragment() {
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
        return inflater.inflate(R.layout.fragment_test, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.filtrarlista(modulo.id!!)
        vm.lista.observe(viewLifecycleOwner){
            preguntas=it
            Toast.makeText(context, preguntas[0].toString(), Toast.LENGTH_SHORT).show()
        }

    }


}