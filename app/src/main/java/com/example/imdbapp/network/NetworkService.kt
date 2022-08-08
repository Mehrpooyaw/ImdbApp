package com.example.imdbapp.network

import com.example.imdbapp.network.model.advanced_search.AdvancedSearchResults
import com.example.imdbapp.network.model.listItemMovies.ListOfItems
import com.example.imdbapp.network.model.movie.MovieModel
import com.example.imdbapp.network.model.search.SearchResultDto
import com.example.propermates.network.model.company.Company
import com.example.imdbapp.network.model.person.PersonModel
import com.example.imdbapp.network.model.reviews.ReviewsModel
import com.example.imdbapp.network.model.season_episodes.SeasonEpisode
import com.example.imdbapp.network.model.youtube.downloader.YoutubeDownloader
import com.example.imdbapp.network.model.youtube.downloader.playlist.YoutubePlaylist
import com.example.imdbapp.network.model.youtube.trailer.YoutubeTrailer
import com.example.imdbapp.network.model.search_keyword.KeywordSearch
import com.example.imdbapp.presentation.ui.search.SearchType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val apiConst = "en/API/"

interface NetworkService {

    @GET("${apiConst}Search{searchType}/{apiKey}/{expression}")
    suspend fun search(
        @Path("searchType") searchType: SearchType,
        @Path("apiKey") apiKey : String,
        @Path("expression") expression : String
    ) : SearchResultDto

    @GET("api/advancedSearch/{apiKey}")
    suspend fun advancedSearch(
        @Path("apiKey") apiKey : String,
        @Query("title",encoded = true) title : String?,
        @Query("user_rating", encoded = true) rating : String?,
        @Query("release_date", encoded = true) release_date: String?,
        @Query("genres", encoded = true) genres: String?,
        @Query("groups", encoded = true) groups : String?,
    ) : AdvancedSearchResults


    @GET("${apiConst}Title/{apiKey}/{id}/FullActor,FullCast,Posters,Images,Trailer,Ratings")
    suspend fun getMovieByImdbId(
        @Path("apiKey") apiKey: String,
        @Path("id") imdbId : String,
    ) : MovieModel

    @GET("${apiConst}Name/{apiKey}/{id}")
    suspend fun getPersonByPersonImdbId(
        @Path("apiKey") apiKey: String,
        @Path("id") movieImdbId : String
    ) : PersonModel

    @GET("${apiConst}Reviews/{apiKey}/{id}")
    suspend fun getMovieReviews(
        @Path("apiKey") apiKey: String,
        @Path("id") movieImdbId : String
    ) : ReviewsModel

    @GET("${apiConst}SeasonEpisodes/{apiKey}/{id}/{seasonNumber}")
    suspend fun getSeasonEpisodes(
        @Path("apiKey") apiKey: String,
        @Path("id") imdbId : String,
        @Path("seasonNumber") seasonNum : Int
    ): SeasonEpisode

    @GET("api/advancedSearch/{apiKey}")
    suspend fun getTops(
        @Path("apiKey") apiKey : String,
        @Query("groups", encoded = true) groups : String?,
        @Query("count", encoded = true) pageCount : Int?,
    ) : AdvancedSearchResults

    @GET("api/advancedSearch/{apiKey}")
    suspend fun getMoviesByGenre(
        @Path("apiKey") apiKey : String,
        @Query("genres", encoded = true) genres : String?,
        @Query("groups", encoded = true) groups : String? = "top_1000",
        @Query("count", encoded = true) pageCount : Int? = 250,

        ) : AdvancedSearchResults


    @GET("${apiConst}Company/{apiKey}/{id}")
    suspend fun getCompany(
        @Path("apiKey") apiKey: String,
        @Path("id") companyImdbId : String
    ): Company

    @GET("${apiConst}Keyword/{apiKey}/{id}")
    suspend fun searchByKeywords(
        @Path("apiKey") apiKey: String,
        @Path("id") keywords : String
        ): KeywordSearch

    @GET("${apiConst}YouTubeTrailer/{apiKey}/{id}")
    suspend fun getYoutubeTrailer(
        @Path("apiKey") apiKey: String,
        @Path("id") imdbId : String
        ): YoutubeTrailer

    @GET("${apiConst}YouTube/{apiKey}/{v}")
    suspend fun getYoutubeVideoDownloadLink(
        @Path("apiKey") apiKey: String,
        @Path("v") validYoutubeIdOrUrl : String
       ): YoutubeDownloader

    @GET("${apiConst}YouTubePlaylist/{apiKey}/{list}")
    suspend fun getYoutubePlayListDownloadLink(
        @Path("apiKey") apiKey: String,
        @Path("list") validYoutubeListIdOrUrl : String,
        ): YoutubePlaylist


    @GET("Images/{size}/{imageId}")
    suspend fun getImageBySize(
        @Path("width") width : Int,
        @Path("height") height : Int,
        @Path("imageId") imageId : String,
    )

}



