package com.example.roomhw.repo

import androidx.lifecycle.LiveData
import com.example.roomhw.R
import com.example.roomhw.db.Dao
import com.example.roomhw.db.Entity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface Repository {
    suspend fun saveTodo(todo: String)
    suspend fun deleteAll()
    suspend fun deleteById(id: Int)
    suspend fun getAll(): List<Entity?>
    suspend fun getById(id: Int): Entity?
    suspend fun putMark(id : Int)
    suspend fun getCount() : Integer
    var itemFlow: Flow<List<Entity?>>
}

class RepositoryImpl @Inject constructor(
    private val dao: Dao
): Repository {
    override suspend fun saveTodo(item: String) {
        dao.save(
            Entity(
                0,
                item,
                false
            )
        )
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun getAll(): List<Entity?> {
        return dao.getAll()
    }


    override suspend fun getById(id: Int): Entity? {
        return dao.getById(id)
    }

    override suspend fun putMark(id: Int) {
        return dao.putMark(id)
    }

    override suspend fun getCount(): Integer {
        return dao.getCount()
    }

    override var itemFlow: Flow<List<Entity?>> = dao.getAllFlow()
}