package com.mintusharma.innobuzz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mintusharma.innobuzz.models.Post

@Database(entities = [Post::class], version = 1)
abstract class PostsDatabase : RoomDatabase(){

    abstract fun postsDao() : PostDao

    companion object{
        @Volatile
        private var INSTANCE: PostsDatabase? = null

        fun getDatabase(context: Context): PostsDatabase {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        PostsDatabase::class.java,
                        "postDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}