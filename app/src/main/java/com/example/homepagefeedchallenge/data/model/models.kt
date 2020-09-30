package com.example.homepagefeedchallenge.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class HomePageResponse(
    @SerializedName("page")
    val page: Page
)

@Parcelize
data class Page(
    @SerializedName("cards")
    val cards: List<HomePageFeed>
) : Parcelable

@Parcelize
data class HomePageFeed(
    @SerializedName("card_type")
    val cardType: String,
    @SerializedName("card")
    val card: Card
) : Parcelable

@Parcelize
data class Card(
    @SerializedName("value")
    val value: String,
    @SerializedName("attributes")
    val attributes: Attributes,
    @SerializedName("title")
    val title: Title,
    @SerializedName("description")
    val description: Description,
    @SerializedName("image")
    val image: Image
) : Parcelable

@Parcelize
data class Attributes(
    @SerializedName("text_color")
    val textColor: String,
    @SerializedName("font")
    val font: Font
) : Parcelable

@Parcelize
data class Font(
    @SerializedName("size")
    val size:String
) : Parcelable

@Parcelize
data class Title(
    @SerializedName("value")
    val value: String,
    @SerializedName("attributes")
    val attributes: Attributes
) : Parcelable

@Parcelize
data class Description(
    @SerializedName("value")
    val value:String,
    @SerializedName("attributes")
    val attributes: Attributes
) : Parcelable

@Parcelize
data class Image(
    @SerializedName("url")
    val url:String,
    @SerializedName("size")
    val size: Size
) : Parcelable

@Parcelize
data class Size(
    @SerializedName("width")
    val width:Int,
    @SerializedName("height")
    val height: Int
) : Parcelable