package org.tensorflow.lite.examples.MemoryBook;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao

public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);
    //adding login DAO method
    @Query("SELECT * from users where userId=(:userId) and password=(:password)")
    UserEntity login(String userId, String password);


}