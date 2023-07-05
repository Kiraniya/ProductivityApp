package com.example.productivity

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun providetodorepo(
        noteDao: TodoDao
    ):Todorepo{
        return Todorepo(todoDao = noteDao)
    }
    @Singleton
    @Provides
    fun provideAppdb(app:Application):Tododatabase{
        return Tododatabase.getInstance(app)
    }
    @Singleton
    @Provides
    fun providetododao(appDatabase: Tododatabase):TodoDao{
        return appDatabase.todoDao()
    }
}