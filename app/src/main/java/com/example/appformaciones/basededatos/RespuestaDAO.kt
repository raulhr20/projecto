package com.example.appformaciones.basededatos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RespuestaDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun inserta(respuesta: Respuesta)

    @Query("DELETE FROM Respuesta Where `id pregunta`=:id")
    suspend fun borradoporpregunta(id:Int)

    @Update
    suspend fun modifica(respuesta: Respuesta)

    @Query("SELECT * FROM Respuesta ORDER BY id ASC")
    fun list(): LiveData<List<Respuesta>>

    @Query("SELECT * FROM Respuesta Where `id pregunta`=:id")
    fun getRespuestas(id:Int): LiveData<List<Respuesta>>


}