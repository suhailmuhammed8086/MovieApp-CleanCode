package com.app.moviesapp.utils

import com.app.moviesapp.BuildConfig

object ImageLoader {
    enum class ImageSize(val value: String){
        W200("w200"),
        W300("w300"),
        W400("w400"),
        W500("w500")
    }

    operator fun invoke(
        endPath: String, size: ImageSize = ImageSize.W400
    ) : String {
        return BuildConfig.FileUrl.plus(size.value).plus(endPath)
    }

    fun getUrl(endPath: String, size: ImageSize = ImageSize.W500): String {
        return BuildConfig.FileUrl.plus(size.value).plus(endPath)
    }
}