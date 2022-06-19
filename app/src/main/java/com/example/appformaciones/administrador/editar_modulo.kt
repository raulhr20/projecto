package com.example.appformaciones.administrador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.appformaciones.R
import com.example.appformaciones.basededatos.AppDB
import com.example.appformaciones.databinding.FragmentAdministradormodulosBinding
import com.example.appformaciones.databinding.FragmentEditarModuloBinding
import kotlinx.coroutines.launch
import java.util.regex.Pattern

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [editar_modulo.newInstance] factory method to
 * create an instance of this fragment.
 */
class editar_modulo : Fragment() {
    lateinit var datos : editar_moduloArgs
    lateinit var enlace: FragmentEditarModuloBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        enlace=FragmentEditarModuloBinding.inflate(inflater,container,false)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val modulo= AppDB.getInstancia(requireContext()).moduloDAO
        arguments?.let {
            datos = editar_moduloArgs.fromBundle(it)
            enlace.nombremoduloeditar.setText(datos.nombre.toString())
            enlace.nivelmoduloeditar.setText(datos.nivel.toString())
            enlace.urlmoduloeditor.setText(datos.url.toString())
        }
        enlace.botonprovar.setOnClickListener {
            findNavController().navigate(editar_moduloDirections.actionEditarModuloToVideoplayer(enlace.urlmoduloeditor.text.toString()))
        }
        enlace.botonactualizar.setOnClickListener {
            if (enlace.nombremoduloeditar.text.isNullOrBlank()||enlace.nivelmoduloeditar.text.isNullOrBlank()){
                Toast.makeText(context, "rellena toda la informacion por favor", Toast.LENGTH_SHORT).show()
            }else {
                var urlvalida = Pattern.compile("^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+").matcher(enlace.urlmoduloeditor.text.toString()).matches()
                if (urlvalida) {
                    lifecycleScope.launch {
                        modulo.acutalizamodulo(
                            datos.id,
                            enlace.nombremoduloeditar.text.toString(),
                            enlace.nivelmoduloeditar.text.toString().toInt(),
                            enlace.urlmoduloeditor.text.toString()
                        )
                    }
                    findNavController().popBackStack()
                }else{
                    Toast.makeText(context, "la url no es valdia", Toast.LENGTH_SHORT).show()
                }
            }
            }

    }
}