package com.example.imdbapp.di

import com.example.imdbapp.network.NetworkService
import com.example.imdbapp.network.model.MovieDtoMapper
import com.example.imdbapp.network.model.person.PersonDtoMapper
import com.example.imdbapp.network.model.reviews.ReviewsDtoMapper
import com.example.imdbapp.network.model.search.SearchResultsDtoMapper
import com.example.imdbapp.presentation.ui.advanced_search.AdvancedSearchResultDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideNetworkService() : NetworkService {
        return Retrofit.Builder()
            .baseUrl("https://imdb-api.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(NetworkService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieDtoMapper() : MovieDtoMapper {
        return MovieDtoMapper()
    }





    @Singleton
    @Provides
    fun provideAdvancedSearchResultDtoMapper() : AdvancedSearchResultDtoMapper {
        return AdvancedSearchResultDtoMapper()
    }


    @Singleton
    @Provides
    fun providePersonDtoMapper() : PersonDtoMapper {
        return PersonDtoMapper()
    }

    @Singleton
    @Provides
    fun provideSearchResultsDtoMapper() : SearchResultsDtoMapper {
        return SearchResultsDtoMapper()
    }


    @Singleton
    @Provides
    fun provideReviewsDtoMapper() : ReviewsDtoMapper {
        return ReviewsDtoMapper()
    }



}