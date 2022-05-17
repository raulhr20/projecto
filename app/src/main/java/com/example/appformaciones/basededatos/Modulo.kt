package com.example.appformaciones.basededatos



import android.R
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Modulo(@PrimaryKey(autoGenerate = true)
                    val id:Int?=null,
                  @ColumnInfo(name="nombre")val nombre: String?,
                  @ColumnInfo(name="nivel")val nivel:Int?,
                  @ColumnInfo(name="playlist")val url:String?) {


}
