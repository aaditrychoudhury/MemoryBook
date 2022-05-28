package org.tensorflow.lite.examples.MemoryBook;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = User.class,
        version = 1,
        exportSchema = false
)
@TypeConverters(DataConverter.class)
public abstract class UserDB extends RoomDatabase {
    private static UserDB userDB = null;
    public abstract UserD userD();
    public static synchronized UserDB getDBInstance(Context context){
        if(userDB == null){
            userDB = Room.databaseBuilder(
                            context.getApplicationContext(),
                            UserDB.class,
                            "user19b2"
                    ).allowMainThreadQueries()
                    .build();
        }
        return userDB;
    }

}