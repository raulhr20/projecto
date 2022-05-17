package com.example.appformaciones.basededatos

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(indices = [Index(value = ["nombreinicio"], unique = true)])
data class Usuario(@PrimaryKey(autoGenerate = true)
                    val id:Int?=null,
                   @ColumnInfo(name="nivel")var nivel:Int?,
                   @ColumnInfo(name="administrador")var administrador:Int?,
                   @ColumnInfo(name="nombreinicio") var nombre:String?,
                   @ColumnInfo(name="contraseña")var contraseña:String?) : Parcelable {

}