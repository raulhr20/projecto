package com.example.appformaciones.basededatos



import android.R
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Modulo(@PrimaryKey(autoGenerate = true)
                    val id:Int?=null,
                  @ColumnInfo(name="nombre")val nombre: String?,
                  @ColumnInfo(name="nivel")val nivel:Int?,
                  @ColumnInfo(name="playlist")val url:String?) : Parcelable {


}
