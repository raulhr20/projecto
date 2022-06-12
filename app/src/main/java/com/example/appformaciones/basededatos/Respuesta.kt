package com.example.appformaciones.basededatos

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
class Respuesta(@PrimaryKey(autoGenerate = true)
                val id:Int?=null,
                @ColumnInfo(name="respuesta") var respuesta:String,
                @ColumnInfo(name = "correcta") val correcta:Boolean,
                @ColumnInfo(name="id pregunta") var idpregunta: Long
) : Parcelable {

}