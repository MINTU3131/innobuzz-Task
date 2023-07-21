package com.mintusharma.innobuzz.repositries

import android.content.Context
import com.mintusharma.innobuzz.ApiServices
import com.mintusharma.innobuzz.db.PostsDatabase
import com.mintusharma.innobuzz.models.Post
import com.mintusharma.innobuzz.utils.NetworkUtils

class PostRepository(
    private val apiServices: ApiServices,
    private val postsDatabase: PostsDatabase,
    private val applicationContext: Context
) {
    suspend fun getPosts(): List<Post> {

//        if (NetworkUtils.isNetworkAvailable(applicationContext)) {
//            postsDao.insertPosts(apiServices.getPosts())
//            apiServices.getPosts()
//        } else {
//            postsDao.getPostsList()
//        }

        if(NetworkUtils.isNetworkAvailable(applicationContext)){
            val result = apiServices.getPosts()
            if(result != null){
                postsDatabase.postsDao().insertAll(result)
                return apiServices.getPosts()
            }
        }
        else{
            val post = postsDatabase.postsDao().getAllPosts()
            return post
        }

//        suspend fun getPosts(): List<Post> {
//            val posts = apiServices.getPosts()
//            postsDatabase.postsDao().insertAll(posts)
//            return posts
//        }
//
//        suspend fun getCachedPosts(): List<Post> {
//            return postsDatabase.postsDao().getAllPosts()
//        }

        return emptyList()
    }


}