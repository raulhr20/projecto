package com.example.appformaciones.usuarios

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appformaciones.basededatos.AppDB
import com.example.appformaciones.basededatos.Pregunta
import com.example.appformaciones.basededatos.Respuesta

class TestViewModel(aplicacion:Application):AndroidViewModel(aplicacion) {
    var puntos=0
    private var _posicion= MutableLiveData<Int>(0)
    val posicion:LiveData<Int>
    get()=_posicion

    val daopre= AppDB.getInstancia(aplicacion).preguntaDAO
    val daores=AppDB.getInstancia(aplicacion).respuestaDAO

    private var _listapre=daopre.recuperaTodo()
    val listaPre:LiveData<List<Pregunta>>
    get()=_listapre

    private var _listares=daores.list()
    val listaRes:LiveData<List<Respuesta>>
    get()=_listares



    fun obtenerrespuestas(pregunta:Int){
        _listares=daores.getRespuestas(pregunta)
    }
    fun filtrarlista(modulo:Int){
        _listapre=daopre.listafiltro(modulo)

    }
    fun respuesta(valida:Boolean){
        if (valida){
            puntos+=1
        }
        _posicion.value = _posicion.value?.plus(1)

    }
}