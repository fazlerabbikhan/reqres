package com.fazlerabbikhan.reqres.presentation.ui.user_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fazlerabbikhan.reqres.R
import com.fazlerabbikhan.reqres.common.Constants

class UserDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: UserDetailViewModel

    private lateinit var avatarImageView: ImageView
    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var supportUrlTextView: TextView
    private lateinit var supportTextTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        // Initialize views
        avatarImageView = findViewById(R.id.avatarImageView)
        firstNameTextView = findViewById(R.id.firstNameTextView)
        lastNameTextView = findViewById(R.id.lastNameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        supportUrlTextView = findViewById(R.id.supportUrlTextView)
        supportTextTextView = findViewById(R.id.supportTextTextView)

        // Obtain the user ID from the intent extras
        val userId = intent.getStringExtra(Constants.PARAM_USER_ID)

        if (userId != null) {
            // Create the UserDetailViewModel using the ViewModelProvider
            viewModel = ViewModelProvider(this)[UserDetailViewModel::class.java]

            // Call the getUserDetail method to fetch user details
            viewModel.getUserDetail(userId)

            // Observe the user detail state and update the UI accordingly
            viewModel.state.observe(this) { state ->
                val userDetail = state.user
                if (userDetail != null) {
                    Log.d("UserDetailActivity", "$userDetail")
                    // Set avatar image using Glide
                    Glide.with(this)
                        .load(userDetail.avatar)
                        .placeholder(R.drawable.person_avatar)
                        .into(avatarImageView)

                    // Set first name and last name to TextViews
                    firstNameTextView.text = userDetail.first_name
                    lastNameTextView.text = userDetail.last_name

                    // Set email to TextView
                    emailTextView.text = userDetail.email

                    // Set support URL and text
                    supportUrlTextView.text = userDetail.supportUrl
                    supportTextTextView.text = userDetail.supportText
                }
            }
        }
    }
}