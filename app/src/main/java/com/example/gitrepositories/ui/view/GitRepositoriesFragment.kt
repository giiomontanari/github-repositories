package com.example.gitrepositories.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gitrepositories.R
import com.example.gitrepositories.databinding.FragmentGitrepositoresBinding
import com.example.gitrepositories.ui.view.adapter.GitRepositoriesAdapter
import com.example.gitrepositories.ui.viewmodel.GitRepositoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GitRepositoriesFragment : Fragment(R.layout.fragment_gitrepositores) {

    private val viewModel: GitRepositoriesViewModel by viewModel()

    private val viewBinding by viewBinding<FragmentGitrepositoresBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getGithub()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.githubReceived.observe(this, Observer {
            viewBinding.recyclerGithub.layoutManager = LinearLayoutManager(context)
            viewBinding.recyclerGithub.adapter = GitRepositoriesAdapter(it.items)
        })
    }
}
