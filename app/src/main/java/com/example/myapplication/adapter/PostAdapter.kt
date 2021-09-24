package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Post
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostAdapter (private val Post: List<Post>,)
    :RecyclerView.Adapter<PostAdapter.PostViewHolder>(){
    class PostViewHolder(itemView: View):RecyclerView.ViewHolder(itemView ){
        fun bindPost(post: Post){

            itemView.title.text = post.title
            itemView.body.text = post.body

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)

        return PostViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var itemPost = Post[position]
        holder.bindPost(itemPost)
    }

    override fun getItemCount(): Int {
       return Post.size
    }
}

