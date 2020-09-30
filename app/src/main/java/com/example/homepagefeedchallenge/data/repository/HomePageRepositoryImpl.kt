package com.example.homepagefeedchallenge.data.repository

import com.example.homepagefeedchallenge.data.model.HomePageResponse
import com.example.homepagefeedchallenge.data.network.WebServices
import io.reactivex.Single

class HomePageRepositoryImpl(private val webServices: WebServices) : HomePageRepository {
    override fun fetchHomePage(): Single<HomePageResponse> {
        return webServices.fetchHomePage()
    }
}