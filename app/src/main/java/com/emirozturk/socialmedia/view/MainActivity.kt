package com.emirozturk.socialmedia.view
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emirozturk.socialmedia.widget.showMessage
import com.emirozturk.socialmedia.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            //Eğer kullanıcı daha önceden giriş yaptıysa diğer ekrana geç.
            gotoFeedActivity()
        }
    }

    fun register(view: View) {
        email = binding.editMail.text.toString()
        password = binding.editPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            gotoFeedActivity()
        }.addOnFailureListener {
            showMessage(this, it.localizedMessage!!)
        }
    }

    fun login(view: View) {
        email = binding.editMail.text.toString()
        password = binding.editPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            gotoFeedActivity()
        }.addOnFailureListener {
            showMessage(this, it.localizedMessage!!)
        }
    }

    private fun gotoFeedActivity() {
        val intent = Intent(this, FeedActivity::class.java)
        startActivity(intent)
        finish()
    }
}