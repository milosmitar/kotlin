package com.tarmi.kotlin

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProductRepository(application: Application) {

    val searchResult: MutableLiveData<List<Product>>? = null
    private var productDao: ProdustDao? = null
    var allProducts: LiveData<List<Product>>?

    init {
        val db: ProductRoomDatabase? = ProductRoomDatabase.getDatabase(application)
        productDao = db?.productDao()
        allProducts = productDao?.getAllProduct()
    }


    fun asycFinished(results: List<Product>) {
        searchResult?.value = results
    }

    fun insertProduct(product: Product) {
        val task = InsertAsyncTask(productDao)
        task.execute(product)
    }

    fun deleteProduct(name: String) {
        val task = DeleteAsyncTask(productDao)
        task.execute(name)
    }

    fun findProduct(name: String) {
        val task = QueryAsyncTask(productDao)
        task.delegate = this
        task.execute(name)
    }

    private class QueryAsyncTask constructor(val asyncTaskDao: ProdustDao?) :
        AsyncTask<String, Void, List<Product>>() {

        var delegate: ProductRepository? = null

        override fun doInBackground(vararg params: String?): List<Product>? {
            return params[0]?.let { asyncTaskDao?.findProduct(it) }
        }

        override fun onPostExecute(result: List<Product>?) {
            result?.let { delegate?.asycFinished(it) }
        }

    }

    private class InsertAsyncTask constructor(val asyncTaskDao: ProdustDao?) :
        AsyncTask<Product, Void, Void>() {

        var delegate: ProductRepository? = null

        override fun doInBackground(vararg params: Product?): Void? {
            params[0]?.let { asyncTaskDao?.insertProduct(it) }
            return null
        }
    }

    private class DeleteAsyncTask constructor(val asyncTaskDao: ProdustDao?) :
        AsyncTask<String, Void, Void>() {
        override fun doInBackground(vararg params: String?): Void? {
            params[0]?.let { asyncTaskDao?.deleteProduct(it) }
            return null
        }

    }
}