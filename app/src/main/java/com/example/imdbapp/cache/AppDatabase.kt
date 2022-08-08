package com.example.imdbapp.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.imdbapp.cache.models.*

@Database(entities = [ApiKey::class, ResultEntity::class, BoxOfficeMovieAllTimeEntity::class, TopMovieEntity::class,MovieEntity::class,PersonEntity::class], version = 12)
abstract class AppDatabase: RoomDatabase() {

    abstract fun appDao() : AppDao

    companion object{
        val DATABASE_NAME : String = "app_db"
    }

}
