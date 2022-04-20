package com.handcraftedmarket.handcraftedmarket.repos

data class RepoResource<T>(
    val data: T? = null
) {
    val isSuccessful = data != null
}