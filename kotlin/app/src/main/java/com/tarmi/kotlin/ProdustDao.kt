package com.tarmi.kotlin

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface ProdustDao {
    @Insert
    fun insertProduct(product: Product)

    @Query("SELECT * FROM products WHERE productName = :name")
    fun findProduct(name: String): List<Product>

    @Query("DELETE FROM products WHERE productName = :name")
    fun deleteProduct(name: String)

    @Query("SELECT * FROM products")
    fun getAllProduct(): LiveData<List<Product>>

}