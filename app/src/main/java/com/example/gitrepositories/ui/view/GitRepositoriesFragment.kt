package com.example.gitrepositories.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gitrepositories.R
import com.example.gitrepositories.databinding.FragmentGitrepositoresBinding
import com.example.gitrepositories.ui.view.adapter.GitRepositoriesAdapter
import com.example.gitrepositories.ui.viewmodel.GitRepositoriesViewModel
import com.example.gitrepositories.ui.viewmodel.GitViewStates
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
            when(it) {
                is GitViewStates.Error -> {
                    viewBinding.loading.visibility = View.GONE
                }
                is GitViewStates.Loading -> {
                    viewBinding.loading.visibility = View.VISIBLE
                }
                is GitViewStates.Success -> {
                    viewBinding.loading.visibility = View.GONE
                    viewBinding.recyclerGithub.adapter = GitRepositoriesAdapter(it.data.items)
                }
            }
        })
    }
}
