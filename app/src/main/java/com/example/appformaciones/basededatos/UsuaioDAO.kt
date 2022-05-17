package com.example.appformaciones.basededatos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsuaioDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun inserta(usuario: Usuario)

    @Delete
    suspend fun borra(usuario: Usuario)
    @Query("DELETE FROM Usuario Where id=:id")
    suspend fun borraPorId(id:Int)

    @Update
    suspend fun modifica(usuario: Usuario)
    @Query("UPDATE Usuario SET nivel=nivel+1 WHERE id=:id")
    suspend fun incrementanivelPorId(id:Int)

    @Query("SELECT * FROM Usuario ORDER BY id ASC")//No tenemos garantía de que se hayan guardado en el mismo orden
    suspend fun recuperaTodo(): List<Usuario>

    @Query("SELECT * FROM Usuario ORDER BY id ASC")
    fun listaviewmodel(): LiveData<List<Usuario>>



    @Query("SELECT EXISTS(SELECT * FROM Usuario WHERE nombreinicio = :nombre)")
    suspend fun isRowIsExist(nombre : String) : Boolean

}