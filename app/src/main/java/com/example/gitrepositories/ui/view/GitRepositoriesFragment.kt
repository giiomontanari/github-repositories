package com.example.gitrepositories.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitrepositories.R
import com.example.gitrepositories.ui.view.adapter.GitRepositoriesAdapter
import com.example.gitrepositories.ui.viewmodel.GitRepositoriesViewModel
import kotlinx.android.synthetic.main.fragment_gitrepositores.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GitRepositoriesFragment : Fragment(R.layout.fragment_gitrepositores) {

    private val viewModel: GitRepositoriesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getGithub()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.githubReceived.observe(this, Observer {
            recycler_github.layoutManager = LinearLayoutManager(context)
            recycler_github.adapter = GitRepositoriesAdapter(it.items)
        })
    }
}
