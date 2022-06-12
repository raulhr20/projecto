package com.example.appformaciones.administrador

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.*
import com.example.appformaciones.basededatos.AppDB
import com.example.appformaciones.basededatos.Pregunta
import kotlinx.coroutines.launch

class PreguntasViewModel(aplicacion:Application):AndroidViewModel(aplicacion) {
val dao= AppDB.getInstancia(aplicacion).preguntaDAO
    val daorespuestas= AppDB.getInstancia(aplicacion).respuestaDAO
    private var _lista=dao.recuperaTodo()
    val lista:LiveData<List<Pregunta>>
    get()=_lista


    fun eliminapregunta(pos:Int){
        viewModelScope.launch {
            lista.value?.let{
                it[pos].id?.let { it1 -> daorespuestas.borradoporpregunta(it1) }
                dao.borra(it[pos])
            }
        }
    }
    fun filtrarlista(modulo:Int){
        _lista=dao.listafiltro(modulo)

    }
    fun refresca(){
        _lista=dao.recuperaTodo()
    }


}