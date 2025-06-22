package com.grhuan.cat.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grhuan.cat.data.model.CatEntity

@Dao
interface CatDao {
    // Insere um gato
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(cat: CatEntity)

    // Busca todos os gatos salvos
    @Query("SELECT * FROM cats")
    suspend fun getAll(): List<CatEntity>


    @Query("DELETE FROM cats WHERE id = :catId")
    suspend fun deleteById(catId: String)
}