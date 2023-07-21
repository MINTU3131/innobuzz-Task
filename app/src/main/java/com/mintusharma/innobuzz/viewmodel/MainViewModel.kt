package com.mintusharma.innobuzz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mintusharma.innobuzz.models.Post
import com.mintusharma.innobuzz.repositries.PostRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PostRepository) : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts


    fun fetchPosts() {
//        viewModelScope.launch {
//            try {
//                val result = repository.getPosts()
//                _posts.value = result
//            } catch (e: Exception) {
//                // Handle error
//            }
//        }

        viewModelScope.launch {
//            val cachedPosts = repository.getPosts()
//            if (cachedPosts.isNotEmpty()) {
//                _posts.value = cachedPosts
//            }
//            val freshPosts = repository.getPosts()
            _posts.value = repository.getPosts()
        }

    }

}