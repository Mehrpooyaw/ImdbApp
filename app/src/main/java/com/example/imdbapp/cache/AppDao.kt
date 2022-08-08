package com.example.imdbapp.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imdbapp.cache.models.*
import com.example.imdbapp.domain.model.advanced_search.TopMovieType
import com.example.imdbapp.presentation.ui.categories.components.Genres

@Dao
interface AppDao {
    // Settings - API_KEY
    @Insert
    suspend fun insertApiKey(apiKey : ApiKey) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertApiKeys(apiKey : List<ApiKey>) : LongArray

    @Query("DELETE FROM apiKeys WHERE string_id = :stringId")
    suspend fun deleteApiKey(stringId : String)


    @Query("SELECT * FROM apiKeys")
    suspend fun getApiKeys() : List<ApiKey>


    @Query("SELECT COUNT() FROM apiKeys")
    suspend fun apiKeysCount(): Int




    // TopMovie - Result - BoxOfficeAllTime

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAdvancedResults(movies : List<ResultEntity>) : LongArray


    //Favorites
    @Insert
    suspend fun insertFavorites(movie : MovieEntity) :Long

    @Query("SELECT * FROM favorite_movies")
    suspend fun getFavoriteMovies() : List<MovieEntity>


    @Query("DELETE FROM favorite_movies WHERE id = :id")
    suspend fun removeMovieFromFavorites(id : String)


    @Query("SELECT EXISTS(SELECT * FROM favorite_movies WHERE id = :id)")
    suspend fun isMovieExistInFavorites(id : String): Boolean

    @Insert
    suspend fun insertFavoritePerson(person : PersonEntity) :Long


    @Query("SELECT * FROM favorite_person")
    suspend fun getFavoritePerson() : List<PersonEntity>


    @Query("DELETE FROM favorite_person WHERE id = :id")
    suspend fun removePersonFromFavorites(id : String)


    @Query("SELECT EXISTS(SELECT * FROM favorite_person WHERE id = :id)")
    suspend fun isPersonExistInFavorites(id : String): Boolean



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertResults(movies : List<ResultEntity?>?) : LongArray

    @Query("SELECT * FROM search_results where genreType = :genre")
    suspend fun getResultsByGenres(genre : Genres) : List<ResultEntity>?


    @Query("SELECT * FROM search_results where topMovieType = :topMovieType")
    suspend fun getTopResults(topMovieType: TopMovieType) : List<ResultEntity?>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBoxOffice(movies : List<BoxOfficeMovieAllTimeEntity>) : LongArray

    @Query("SELECT * FROM box_office_movie_all_time")
    suspend fun getBoxOfficeAllTime() : List<BoxOfficeMovieAllTimeEntity?>?









}