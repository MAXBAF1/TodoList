package com.around_team.todolist.di

import android.content.Context
import androidx.room.Room
import com.around_team.todolist.data.db.TodoListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides dependencies related to the local database.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TodoListDatabase {
        return Room
            .databaseBuilder(context, TodoListDatabase::class.java, "todo_list_database")
            .addMigrations(TodoListDatabase.MIGRATION_1_2)
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db: TodoListDatabase) = db.dao()
}