package com.emirozturk.socialmedia.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emirozturk.socialmedia.databinding.RecyclerRowBinding
import com.emirozturk.socialmedia.model.Post
import com.emirozturk.socialmedia.R
import com.squareup.picasso.Picasso

class FeedAdapter(val posts: ArrayList<Post>) : RecyclerView.Adapter<FeedAdapter.PostHolder>() {

    class PostHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.email.text = posts.get(position).email
        holder.binding.comment.text = posts.get(position).comment
        Picasso.get().load(posts.get(position).url).into(holder.binding.post)
    }
}