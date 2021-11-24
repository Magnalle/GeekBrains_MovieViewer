package com.magnalleexample.geekbrains_movieviewer.data

data class TopFromApi(val page: Long,
                      val results : List<MovieDataFromApi>)
