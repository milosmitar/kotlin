package com.tarmi.kotlin

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [(Product::class)],version = 1)
abstract class ProductRoomDatabase : RoomDatabase(){

    abstract fun productDao(): ProdustDao

    companion object{

        private var INSTANCE: ProductRoomDatabase? = null

        internal fun getDatabase(context: Context): ProductRoomDatabase?{

            if(INSTANCE == null){

                synchronized(ProductRoomDatabase::class.java){

                    if(INSTANCE == null){

                        INSTANCE = Room.databaseBuilder<ProductRoomDatabase>(
                            context.applicationContext,ProductRoomDatabase::class.java, "product_database").build()
                    }

                }

            }
            return INSTANCE
        }

    }
}