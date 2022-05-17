package com.example.appformaciones.basededatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class,Modulo::class,Pregunta::class,Respuesta::class], version=1, exportSchema = false)
abstract class AppDB: RoomDatabase() {
    abstract val usuaioDAO: UsuaioDAO
    abstract val moduloDAO: ModuloDAO
    abstract val preguntaDAO:PreguntaDAO
    abstract val respuestaDAO:RespuestaDAO

    companion object{
        @Volatile
        private var instancia: AppDB?=null

        fun getInstancia(context: Context): AppDB {
            synchronized(lock=this){
                if (instancia ==null){
                    instancia = Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                        "appdb"
                    ).build()
                }
                return instancia!!
            }
        }
    }
}