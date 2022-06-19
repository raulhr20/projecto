package com.example.appformaciones.administrador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.appformaciones.R
import com.example.appformaciones.basededatos.*
import com.example.appformaciones.databinding.FragmentEditortestBinding
import com.example.appformaciones.databinding.FragmentEditorusuariosBinding
import com.example.appformaciones.databinding.FragmentTestBinding
import com.example.appformaciones.usuarios.TestViewModel
import kotlinx.coroutines.launch


class editortest : Fragment() {
    lateinit var lista: List<Respuesta>
    private lateinit var pregunta: Pregunta
    private lateinit var datos: editortestArgs
    lateinit var enlace: FragmentEditortestBinding
    val vm: TestViewModel by lazy{
        ViewModelProvider(this,).get(TestViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let{
            datos=editortestArgs.fromBundle(it)
        }
        pregunta=datos.pregunta
        enlace= FragmentEditortestBinding.inflate(inflater,container,false)
        return enlace.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val daopreguntas= AppDB.getInstancia(requireContext()).preguntaDAO
        val daorespuestas= AppDB.getInstancia(requireContext()).respuestaDAO


        enlace.insertar2.setOnClickListener {
            lifecycleScope.launch {
                pregunta.pregunta=enlace.pregunta2.text.toString()
                daopreguntas.modifica(pregunta)

                lista[0].respuesta=enlace.respuestacorrecta2.text.toString()
                lista[1].respuesta=enlace.respuesta5.text.toString()
                lista[2].respuesta=enlace.respuesta4.text.toString()
                daorespuestas.modifica(lista[0])
                daorespuestas.modifica(lista[1])
                daorespuestas.modifica(lista[2])
                findNavController().popBackStack()
            }
        }


        enlace.pregunta2.setText(pregunta.pregunta)
        vm.listaRes.observe(viewLifecycleOwner){
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }
        pregunta.id?.let { it1 -> vm.obtenerrespuestas(it1) }

           vm.listaRes.observe(viewLifecycleOwner){
               lista=it
               enlace.respuestacorrecta2.setText(it[0].respuesta)
               enlace.respuesta5.setText(it[1].respuesta)
               enlace.respuesta4.setText(it[2].respuesta)
           }








    }

}