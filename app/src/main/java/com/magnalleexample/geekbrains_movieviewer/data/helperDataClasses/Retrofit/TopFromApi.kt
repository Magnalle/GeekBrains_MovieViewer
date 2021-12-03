package com.magnalleexample.geekbrains_movieviewer.data.helperDataClasses.Retrofit

import com.magnalleexample.geekbrains_movieviewer.data.helperDataClasses.Retrofit.MovieDataFromApi

data class TopFromApi(val page: Long,
                      val results : List<MovieDataFromApi>)
