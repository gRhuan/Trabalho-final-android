package com.grhuan.cat.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grhuan.cat.data.model.CatEntity

@Dao
interface Catdao {
    // Insere um gato
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cat: CatEntity)

    // Busca todos os gatos salvos
    @Query("SELECT * FROM cats")
    suspend fun getAll(): List<CatEntity>

    // Deleta um gato
    @Delete
    suspend fun delete(cat: CatEntity)
}