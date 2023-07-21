package com.mintusharma.innobuzz

import com.mintusharma.innobuzz.models.Post

interface OnClickListner {
    fun onTaskItemClick(post: Post)
}