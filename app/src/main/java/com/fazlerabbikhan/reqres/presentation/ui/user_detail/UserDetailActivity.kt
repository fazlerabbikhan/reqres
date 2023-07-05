package com.fazlerabbikhan.reqres.presentation.ui.user_detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fazlerabbikhan.reqres.R
import com.fazlerabbikhan.reqres.common.Constants
import com.fazlerabbikhan.reqres.databinding.ActivityUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var viewModel: UserDetailViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = findViewById(R.id.progressBar)

        val userId = intent.getStringExtra(Constants.PARAM_USER_ID)
        Log.d("DetailActivity", "$userId")
        if (userId != null) {
            viewModel = ViewModelProvider(this)[UserDetailViewModel::class.java]
            viewModel.getUserDetail(userId)

            observeUserDetail()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeUserDetail() {
        viewModel.state.observe(this) { state ->
            Log.d("DetailViewModelObserver", "$state")
            val userDetail = state.user
            progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            if (userDetail != null) {
                // Set avatar image using Glide
                Glide.with(this)
                    .load(userDetail.avatar)
                    .placeholder(R.drawable.person_avatar)
                    .into(binding.avatarImageView)

                // Set first name and last name to TextViews
                binding.firstNameTextView.text = "First Name: ${userDetail.first_name}"
                binding.lastNameTextView.text = "Last Name: ${userDetail.last_name}"

                // Set email to TextView
                binding.emailTextView.text = "Email: ${userDetail.email}"

                // Set support URL and text
                binding.supportUrlTextView.text = "Visit: ${userDetail.supportUrl}"
                binding.supportTextTextView.text = "Description: ${userDetail.supportText}"
            }
        }
    }
}
