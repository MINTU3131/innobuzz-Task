package com.mintusharma.innobuzz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mintusharma.innobuzz.OnClickListner
import com.mintusharma.innobuzz.databinding.RowPostsItemBinding
import com.mintusharma.innobuzz.models.Post

class PostsListAdapter(private var post: ArrayList<Post>,private val onClickListener: OnClickListner) : RecyclerView.Adapter<PostsListAdapter.PostViewHolder>() {

//    private var posts: List<Post> = emptyList()
    private lateinit var onClickListner : OnClickListner

//    fun setPosts(posts: ArrayList<Post>) {
//        this.posts = posts
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowPostsItemBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(post[position])
    }

    override fun getItemCount(): Int = post.size

    inner class PostViewHolder(private val binding: RowPostsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            itemView.setOnClickListener { onClickListener.onTaskItemClick(post) }
            binding.tittle.text = post.title
        }
    }
}