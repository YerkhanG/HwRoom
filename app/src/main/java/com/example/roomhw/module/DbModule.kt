package com.example.roomhw.module

import android.content.Context
import androidx.room.Room
import com.example.roomhw.db.Dao
import com.example.roomhw.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): Database {
        return Room
            .databaseBuilder(context, Database::class.java, "To do database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun getDao(db: Database): Dao = db.Dao()
}