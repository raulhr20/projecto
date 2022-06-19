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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appformaciones.R
import com.example.appformaciones.administrador.PreguntasViewModel
import com.example.appformaciones.administrador.RespuestasViewModel
import com.example.appformaciones.administrador.modulosViewModel
import com.example.appformaciones.administrador.usuariosViewModel
import com.example.appformaciones.basededatos.*
import com.example.appformaciones.databinding.FragmentAdministradorDeUsuariosBinding
import com.example.appformaciones.databinding.FragmentTestBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IndexOutOfBoundsException
import java.util.*

class test : Fragment() {
    lateinit var enlace: FragmentTestBinding
    val vm: TestViewModel by lazy{
        ViewModelProvider(this,).get(TestViewModel::class.java)
    }
    val vmu: usuariosViewModel by lazy{
        ViewModelProvider(this,).get(usuariosViewModel::class.java)
    }

    private lateinit var datos: testArgs
    private lateinit var modulo: Modulo
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            datos=testArgs.fromBundle(it)
        }
        modulo=datos.modelo
        usuario=datos.usuario
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

        val adaptador= adaptadorRespuestas(vm)
        enlace.respuestas.adapter=adaptador
        enlace.respuestas.layoutManager=LinearLayoutManager(context)
        vm.filtrarlista(modulo.id!!)
        vm.posicion.observe(viewLifecycleOwner){
            vm.listaPre.observe(viewLifecycleOwner){





                try {
                    var pregunta: Pregunta
                    pregunta=it.get(vm.posicion.value!!)
                    if (pregunta != null) {
                        enlace.preguntas.text=pregunta.pregunta
                    }
                    if (pregunta != null) {
                        pregunta.id?.let { it1 -> vm.obtenerrespuestas(it1) }
                    }
                    var respuestas =vm.puntos
                    var preguntas = it.size
                    var division:Float = respuestas.toFloat() / preguntas.toFloat()
                    var nota=division * 10
                    if (nota>=5){
                        if (modulo.nivel===usuario.nivel) {
                            vmu.actualizanivel(usuario)
                            findNavController().popBackStack()
                            Toast.makeText(context, "AS APROBADO", Toast.LENGTH_SHORT).show()
                        }else{
                            findNavController().popBackStack()
                            Toast.makeText(context, "AS APROBADO DE NUEVO", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        findNavController().popBackStack()
                        Toast.makeText(context, "AS SUSPENDIDO", Toast.LENGTH_SHORT).show()
                    }
                }catch (e:IndexOutOfBoundsException){
                    findNavController().popBackStack()
                    Toast.makeText(context, "NO HAY TEST", Toast.LENGTH_SHORT).show()

                }


                vm.listaRes.observe(viewLifecycleOwner){
                    val semilla=System.currentTimeMillis()
                    adaptador.lista=it.shuffled(Random(semilla))
                }
            }
        }




    }


}