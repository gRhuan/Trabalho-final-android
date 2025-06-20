package com.grhuan.cat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.grhuan.cat.data.local.dao.Catdao
import com.grhuan.cat.data.model.CatEntity

@Database(
    entities = [CatEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatDatabase: RoomDatabase() {
    abstract fun catDao(): Catdao
}