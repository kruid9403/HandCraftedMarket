package com.handcraftedmarket.handcraftedmarket.model.shipEngine

data class Package(
    val dimensions: Dimensions,
    val insured_value: InsuredValue,
    val weight: Weight
)