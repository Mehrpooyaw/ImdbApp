package com.example.imdbapp.interactors.advanced_search




enum class AdvancedGenres(
    val str : String,
    val title : String,
){
    Action(str = "action", title = "Action"),
    Adventure(str = "adventure", title = "Adventure"),
    Animation(str = "animation", title = "Animation"),
    Biography(str = "biography", title = "Biography"),
    Comedy(str = "comedy", title = "Comedy"),
    Crime(str = "crime", title = "Crime"),
    Documentary(str = "documentary", title = "Documentary"),
    Drama(str = "drama", title = "Drama"),
    Family(str = "family", title = "Family"),
    Fantasy(str = "fantasy", title = "Fantasy"),
    FilmNoir(str = "noir", title = "Film_Noir"),
    GameShow(str = "show", title = "Game_Show"),
    History(str = "history", title = "History"),
    Horror(str = "horror", title = "Horror"),
    Music(str = "music", title = "Music"),
    Musical(str = "musical", title = "Musical"),
    Mystery(str = "mystery", title = "Mystery"),
    News(str = "news", title = "News"),
    RealityTV(str = "tv", title = "Reality"),
    Romance(str = "romance", title = "Romance"),
    SciFi(str = "fi", title = "Sci_Fi"),
    Sport(str = "sport", title = "Sport"),
    TalkShow(str = "show", title = "Talk_Show"),
    Thriller(str = "thriller", title = "Thriller"),
    War(str = "war", title = "War" ),
    Western(str = "wester", title = "Western")
}

val imdbRates = listOf(
    "1.0",
    "2.0",
    "3.0",
    "4.0",
    "5.0",
    "6.0",
    "7.0",
    "8.0",
    "9.0",
    "10.0",
)

val month = listOf(
    "01",
    "02",
    "03",
    "04",
    "05",
    "06",
    "07",
    "08",
    "09",
    "10",
    "11",
    "12",
)

val day = listOf(
    "01",
    "02",
    "03",
    "04",
    "05",
    "06",
    "07",
    "08",
    "09",
    "10",
    "11",
    "12",
    "13",
    "14",
    "15",
    "16",
    "17",
    "18",
    "19",
    "20",
    "21",
    "22",
    "23",
    "24",
    "25",
    "26",
    "27",
    "28",
    "29",
    "30",
    "31",
)

val year = listOf(
    "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900"
)


enum class ContentRating(str : String, title : String){
    G(str = "G", title = "G"),
    PG_13(str = "PG_13", title = "PG-13"),
    PG(str = "PG", title = "PG"),
    R(str = "R", title = "R"),
    NC_17(str = "NC_17", title = "NC-17")
}



