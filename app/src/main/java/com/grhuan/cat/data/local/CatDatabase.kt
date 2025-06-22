package com.grhuan.cat.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grhuan.cat.data.model.CatEntity

@Database(
    entities = [CatEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatDatabase: RoomDatabase() {
    abstract val catDao : CatDao

    companion object{
    @Volatile
    private var INSTANCE: CatDatabase? = null
        fun getInstance(context: Context) : CatDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance  = Room.databaseBuilder(
                        context.applicationContext,
                        CatDatabase::class.java,
                        "cat-db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}