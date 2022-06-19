package com.example.appformaciones.basededatos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PreguntaDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun inserta(pregunta: Pregunta):Long

    @Delete
    suspend fun borra(pregunta: Pregunta)

    @Update
    suspend fun modifica(pregunta: Pregunta)

    @Query("DELETE FROM Pregunta Where `id modulo`=:id")
    suspend fun borradopreguntas(id:Int)

    @Query("SELECT * FROM Pregunta ORDER BY `id modulo` ASC")
    fun recuperaTodo(): LiveData<List<Pregunta>>

    @Query("SELECT * FROM Pregunta WHERE `id modulo`=:modulo")
    fun listafiltro(modulo: Int): LiveData<List<Pregunta>>

    @Query("SELECT * FROM Pregunta WHERE `id modulo`=:modulo")
    suspend fun crearlistapreguntas(modulo: Int): List<Pregunta>

    @Query("SELECT EXISTS(SELECT * FROM Pregunta WHERE pregunta = :pregunta)")
    suspend fun existelapregunta(pregunta : String) : Boolean


}
