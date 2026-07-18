package com.example.nutrisport.data

import kotlin.uuid.Uuid
import kotlinx.serialization.Serializable



@Serializable
data class CartItem(
    val id:String?= Uuid.random().toHexString(),
    val productId:String,
    val flavour:String?=null,
    val quantity:Int
)
