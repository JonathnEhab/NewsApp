package com.example.newsapp.domain.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE email= :email AND password = :password")
    suspend fun login(email: String, password: String): User?

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getUser(): User?

    @Update
    suspend fun updateUserInfo(user: User)


}