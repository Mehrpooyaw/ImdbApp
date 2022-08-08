package com.example.imdbapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.example.imdbapp.datastore.SettingsDataStore
import com.example.imdbapp.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }
    
    
    @Singleton
    @Provides
    fun provideDataStore(app : BaseApplication) : SettingsDataStore {
        return SettingsDataStore(app)
    }
}
