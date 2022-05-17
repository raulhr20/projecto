package com.example.appformaciones.administrador

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.appformaciones.basededatos.AppDB
import com.example.appformaciones.basededatos.Usuario
import kotlinx.coroutines.launch

class usuariosViewModel(aplication:Application):AndroidViewModel(aplication) {
val dao= AppDB.getInstancia(aplication).usuaioDAO
    private var _lista=dao.listaviewmodel()
    val lista:LiveData<List<Usuario>>
    get()=_lista



    fun eliminausuario(pos:Int){
        viewModelScope.launch {
            lista.value?.let {
                dao.borra(it[pos])
            }
        }
    }
     fun actualizanivel(usuario: Usuario){
         viewModelScope.launch {
             usuario.id?.let { dao.incrementanivelPorId(it) }

         }





    }


}