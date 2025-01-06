package com.example.core.data.networking

private const val baseUrl= "https://www.themealdb.com/api/json/v1/"
fun constructUrl(url: String): String{
    return when{
        url.contains(baseUrl)-> url
        url.startsWith("/")-> baseUrl+ url.drop(1)
        else-> baseUrl+ url
    }
}