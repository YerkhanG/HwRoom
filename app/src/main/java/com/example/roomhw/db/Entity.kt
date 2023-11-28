package com.example.roomhw.db

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "shop_list_item")
data class Entity (
    @PrimaryKey(autoGenerate = true) val id : Int,
    val item : String ,
    var done : Boolean
)