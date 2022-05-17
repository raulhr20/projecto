package com.example.appformaciones.basededatos

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
class Pregunta(@PrimaryKey(autoGenerate = true)
           val id:Int?=null,
           @ColumnInfo(name="pregunta")var pregunta:String?,
           @ColumnInfo(name="id modulo")var idmodulo:Int?) : Parcelable {

}