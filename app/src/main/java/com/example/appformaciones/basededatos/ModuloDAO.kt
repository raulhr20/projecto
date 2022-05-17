package com.example.appformaciones.basededatos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ModuloDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun inserta(modulo: Modulo)

    @Delete
    suspend fun borra(modulo: Modulo)
    @Query("DELETE FROM Modulo Where id=:id")
    suspend fun borraPorId(id:Int)

    @Update
    suspend fun modifica(modulo: Modulo)
    @Query("UPDATE Modulo SET nombre=:nombre,nivel=:nivel,playlist=:url  WHERE id=:id")
    suspend fun acutalizamodulo(id:Int,nombre:String,nivel:Int,url:String)

    @Query("SELECT * FROM Modulo ORDER BY id ASC")
    fun listaviewmodel(): LiveData<List<Modulo>>

    @Query("SELECT * FROM Modulo Where nivel<=:nivel ORDER BY id ASC")
    fun listapornivel(nivel: Int): LiveData<List<Modulo>>




}