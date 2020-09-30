package com.example.homepagefeedchallenge.data.repository

import com.example.homepagefeedchallenge.data.model.HomePageResponse
import io.reactivex.Single

interface HomePageRepository {
    fun fetchHomePage(): Single<HomePageResponse>
}