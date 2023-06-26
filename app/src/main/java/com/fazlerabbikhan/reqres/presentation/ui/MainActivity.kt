package com.fazlerabbikhan.reqres.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fazlerabbikhan.reqres.R
import com.fazlerabbikhan.reqres.presentation.ui.user_list.UserListAdapter
import com.fazlerabbikhan.reqres.presentation.ui.user_list.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userRecyclerView = findViewById(R.id.userRecyclerView)
        progressBar = findViewById(R.id.progressBar)

        // Set up RecyclerView and adapter
        val userListAdapter = UserListAdapter()
        userRecyclerView.adapter = userListAdapter

        userRecyclerView.layoutManager = LinearLayoutManager(this)

        // Set up ViewModel and observe state changes
        val viewModel: UserListViewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        viewModel.state.observe(this) { state ->
            Log.d("ViewModelObserver", "$state")
            progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            userListAdapter.submitList(state.users)
        }
    }
}

