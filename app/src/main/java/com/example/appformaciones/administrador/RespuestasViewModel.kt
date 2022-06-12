package com.example.appformaciones.administrador

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.appformaciones.basededatos.AppDB
import com.example.appformaciones.basededatos.Respuesta

class RespuestasViewModel(aplicacion: Application): AndroidViewModel(aplicacion) {
    val dao= AppDB.getInstancia(aplicacion).respuestaDAO
    var puntos=0
    var posicion=0
    private var _lista=dao.list()
    val lista:LiveData<List<Respuesta>>
    get()=_lista

    fun obtenerrespuestas(pregunta:Int){
        _lista=dao.getRespuestas(pregunta)
    }



}