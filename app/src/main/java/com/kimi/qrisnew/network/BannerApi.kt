package com.kimi.qrisnew.network

import com.kimi.qrisnew.model.Banner
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface BannerApi {
    @GET("banner")
    suspend fun getBanner(): Banner
}