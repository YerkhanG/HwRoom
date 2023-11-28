package com.example.roomhw.module

import com.example.roomhw.db.Dao
import com.example.roomhw.repo.Repository
import com.example.roomhw.repo.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {


    @Singleton
    @Provides
    fun provideTodoRepository(dao: Dao): Repository = RepositoryImpl(dao)
}