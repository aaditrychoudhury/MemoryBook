package org.tensorflow.lite.examples.MemoryBook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class DataConverter {

    @TypeConverter
    public static Date toDate(Long dateLong){
        return dateLong == null? null : new Date(dateLong);
    }
    @TypeConverter
    public static Long fromDate(Date date){
        return date == null? null : date.getTime();
    }

    public static byte[] convertImage2ByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0 , stream);
        return stream.toByteArray();
    }

    public static Bitmap convertByteArray2Image(byte[] array){
        return BitmapFactory.decodeByteArray(array,0,array.length);
    }
}