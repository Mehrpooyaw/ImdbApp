package com.example.imdbapp.di

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.imdbapp.cache.AppDao
import com.example.imdbapp.cache.AppDatabase
import com.example.imdbapp.cache.models.ApiKey
import com.example.imdbapp.cache.util.ResultEntityMapper
import com.example.imdbapp.cache.util.TopMovieEntityMapper
import com.example.imdbapp.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Provider
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideAppDb(app : BaseApplication,provider : Provider<AppDao>) : AppDatabase{
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(
                object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        MainScope().launch {
                            try {
                                listOf(
                                    ApiKey(id = 6, stringId = "k_uwlksnkc", name = "Default VI"),
                                    ApiKey(id = 5, stringId = "k_iu5o6g6a", name = "Default V"),
                                    ApiKey(id = 4, stringId = "k_zir23t3i", name = "Default IV"),
                                    ApiKey(id = 3, "k_e5ncxzpm", name = "Default III"),
                                    ApiKey(id = 2, "k_ujvwraib", name = "Default II"),
                                    ApiKey(id = 1, "k_7w5zq0a5", name = "Default I"),
                                ).forEach {
                                    provider.get().insertApiKey(it)

                                }
                            }catch (e :Exception){
                                Log.e("appDebug","Error on Cache module : ${e.message}")
                            }
                        }

                    }

                }
            )
            .build()


    }

    @Singleton
    @Provides
    fun provideAppDao(db : AppDatabase) : AppDao {
        return db.appDao()
    }


    @Singleton
    @Provides
    fun provideAdvancedSearchEntityMapper() : ResultEntityMapper {
        return ResultEntityMapper()
    }
}