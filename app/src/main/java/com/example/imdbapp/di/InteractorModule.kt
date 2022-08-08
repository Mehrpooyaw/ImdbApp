package com.example.imdbapp.di

import com.example.imdbapp.cache.AppDao
import com.example.imdbapp.cache.util.ResultEntityMapper
import com.example.imdbapp.datastore.SettingsDataStore
import com.example.imdbapp.interactors.advanced_search.GetAdvancedSearchResults
import com.example.imdbapp.interactors.categories.GetMoviesByGenre
import com.example.imdbapp.interactors.favorites.GetFavoritePerson
import com.example.imdbapp.interactors.favorites.GetFavoritesMovies
import com.example.imdbapp.interactors.top_movies.GetTopMovies
import com.example.imdbapp.interactors.person.GetPerson
import com.example.imdbapp.interactors.movie.GetMovie
import com.example.imdbapp.interactors.movie.GetReviews
import com.example.imdbapp.interactors.search.GetSearchResults
import com.example.imdbapp.interactors.settings.SettingsInteractor
import com.example.imdbapp.network.NetworkService
import com.example.imdbapp.network.model.MovieDtoMapper
import com.example.imdbapp.network.model.person.PersonDtoMapper
import com.example.imdbapp.network.model.reviews.ReviewsDtoMapper
import com.example.imdbapp.network.model.search.SearchResultsDtoMapper
import com.example.imdbapp.presentation.ui.advanced_search.AdvancedSearchResultDtoMapper
import com.example.imdbapp.presentation.ui.genres.GenresEvent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractionModule {

    @ViewModelScoped
    @Provides
    fun provideGetMovie(
        dtoMapper: MovieDtoMapper,
        networkService: NetworkService,
    ): GetMovie {
        return GetMovie(dtoMapper = dtoMapper, networkService = networkService)
    }


    @ViewModelScoped
    @Provides
    fun provideGetAdvancedSearchResults(settingsDataStore: SettingsDataStore,networkService: NetworkService) : GetAdvancedSearchResults {
        return GetAdvancedSearchResults(
            settings = settingsDataStore,
            networkService = networkService
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetMoviesByGenre(
        networkService: NetworkService,
        settingsDataStore: SettingsDataStore,
        entityMapper : ResultEntityMapper,
        dtoMapper: AdvancedSearchResultDtoMapper,
        appDao: AppDao
    ) : GetMoviesByGenre {
        return GetMoviesByGenre(
            networkService = networkService,
            settings = settingsDataStore,
            entityMapper = entityMapper,
            dtoMapper = dtoMapper, appDao = appDao
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetTopResults(
        networkService: NetworkService,
        settingsDataStore: SettingsDataStore,
        entityMapper : ResultEntityMapper,
        dtoMapper: AdvancedSearchResultDtoMapper,
        appDao: AppDao
    ) : GetTopMovies {
        return GetTopMovies(
            networkService = networkService,
            settings = settingsDataStore,
            entityMapper = entityMapper,
            dtoMapper = dtoMapper,
            appDao = appDao
        )
    }


    @ViewModelScoped
    @Provides
    fun provideGetFavoriteMovies(appDao: AppDao): GetFavoritesMovies{
        return GetFavoritesMovies(
            appDao = appDao
        )
    }
    @ViewModelScoped
    @Provides
    fun provideGetFavoritePerson(appDao: AppDao): GetFavoritePerson{
        return GetFavoritePerson(
            appDao = appDao
        )
    }

    @ViewModelScoped
    @Provides
    fun provideSettingsInteractor(appDao : AppDao,settingsDataStore: SettingsDataStore): SettingsInteractor {
        return SettingsInteractor(
            appDao = appDao,
            settings = settingsDataStore
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetPerson(
        dtoMapper: PersonDtoMapper,
        networkService: NetworkService
    ) : GetPerson {
        return GetPerson(dtoMapper = dtoMapper, networkService = networkService)
    }


    @ViewModelScoped
    @Provides
    fun provideGetReviews(
        networkService: NetworkService,
        dtoMapper: ReviewsDtoMapper
    ) : GetReviews {
        return GetReviews(networkService = networkService, reviewsDtoMapper = dtoMapper)
    }

    @ViewModelScoped
    @Provides
    fun provideGetSearchResults(
        networkService: NetworkService,
        dtoMapper : SearchResultsDtoMapper

    ) : GetSearchResults{
        return GetSearchResults(
            networkService = networkService,
            dtoMapper = dtoMapper
        )

    }
}