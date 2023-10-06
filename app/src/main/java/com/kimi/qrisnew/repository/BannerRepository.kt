package com.kimi.qrisnew.repository

import com.kimi.qrisnew.model.BannerItem
import com.kimi.qrisnew.model.DataOrException
import com.kimi.qrisnew.network.BannerApi
import javax.inject.Inject

class BannerRepository @Inject constructor(
    private val bannerApi: BannerApi
) {
    val DataBanner = DataOrException<ArrayList<BannerItem>, Boolean, Exception>()

    suspend fun getAllBanner() : DataOrException<ArrayList<BannerItem>, Boolean, Exception> {
        try {
            DataBanner.isLoading = true
            DataBanner.data = bannerApi.getBanner()
            if (DataBanner.data.toString().isNotEmpty()) DataBanner.isLoading = false
        } catch (exception: Exception) {
            DataBanner.error = exception
        }
        return DataBanner
    }
}