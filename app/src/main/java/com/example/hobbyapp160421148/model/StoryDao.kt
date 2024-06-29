package com.example.hobbyapp160421148.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories")
    fun getAllStories(): Flow<List<Story>>

    @Query("SELECT * FROM stories WHERE id = :id")
    suspend fun getStoryById(id: Int): Story?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: List<Story>)

    @Update
    suspend fun updateStory(story: Story)

    @Delete
    suspend fun deleteStory(story: Story)
}