package com.emirozturk.socialmedia.view
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirozturk.socialmedia.R
import com.emirozturk.socialmedia.adapter.FeedAdapter
import com.emirozturk.socialmedia.widget.showMessage
import com.emirozturk.socialmedia.databinding.ActivityFeedBinding
import com.emirozturk.socialmedia.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var posts: ArrayList<Post>
    private lateinit var feedAdapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        firestore = Firebase.firestore
        posts = ArrayList()
        getData()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        feedAdapter = FeedAdapter(posts)
        binding.recyclerView.adapter = feedAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.signOut -> signOut()
            R.id.addPost -> addPost()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun addPost() {
        val intent = Intent(this, UploadActivity::class.java)
        startActivity(intent)
    }

    private fun getData() {
        firestore.collection("Posts").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if (error != null) {
                showMessage(this, error.localizedMessage!!)
            }
            if (value != null) {
                val docs = value.documents
                posts.clear()
                for (doc in docs) {
                    doc?.let { doc ->
                        val comment = doc.get("comment") as String
                        val email = doc.get("email") as String
                        val url = doc.get("url") as String

                        val post = Post(email, comment, url)
                        posts.add(post)
                    }
                }
                feedAdapter.notifyDataSetChanged()
            }
        }
    }
}