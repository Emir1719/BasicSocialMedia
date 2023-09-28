package com.emirozturk.socialmedia
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emirozturk.socialmedia.databinding.ActivityMainBinding
import com.emirozturk.socialmedia.databinding.ActivityUploadBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun register(view: View) {

    }

    fun login(view: View) {

    }
}