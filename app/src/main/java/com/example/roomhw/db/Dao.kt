package com.example.roomhw.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("Select * from shop_list_item where id = :id")
    fun getById(id: Int): Entity


    @Query("Select * from shop_list_item")
    fun getAll(): List<Entity>

    @Query("Select COUNT(*) from shop_list_item")
    fun getCount(): Integer

    @Query("Select * from shop_list_item")
    fun getAllFlow(): Flow<List<Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(todoEntity: Entity)

    @Query("Delete from shop_list_item")
    fun deleteAll()

    @Query("Delete from shop_list_item where id = :id")
    fun deleteById(id: Int)

    @Query("UPDATE shop_list_item SET done = 1 WHERE id = :id")
    fun putMark(id: Int)
}