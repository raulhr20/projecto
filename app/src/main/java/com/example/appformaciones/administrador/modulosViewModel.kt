package com.example.appformaciones.administrador

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.*
import com.example.appformaciones.basededatos.AppDB
import com.example.appformaciones.basededatos.Modulo
import kotlinx.coroutines.launch



class modulosViewModel(aplicacion:Application):AndroidViewModel(aplicacion) {
val dao= AppDB.getInstancia(aplicacion).moduloDAO
    val daopreguntas=AppDB.getInstancia(aplicacion).preguntaDAO

    private var _lista=dao.listaviewmodel()
    val lista: LiveData<List<Modulo>>
        get()=_lista


    fun eliminamodulo(pos:Int){
        viewModelScope.launch {
            lista.value?.let {
                dao.borra(it[pos])
            }
        }
    }
    fun haytest(modid:Int){
        viewModelScope.launch {
            lista.value?.let {
             var x = daopreguntas.listafiltro(modid)
                Log.d("TAG",x.toString())
            }
        }

    }
    fun cargalistapornivel(nivel:Int){
        _lista=dao.listapornivel(nivel)
    }



}


