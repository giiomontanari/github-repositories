package com.example.gitrepositories.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gitrepositories.R
import com.example.gitrepositories.databinding.FragmentGititemrepositoriesBinding
import com.example.gitrepositories.ui.view.adapter.GitItemRepositoriesAdapter
import com.example.gitrepositories.ui.viewmodel.GitItemRepositoriesViewModel
import com.example.gitrepositories.ui.viewmodel.GitViewStates
import org.koin.androidx.viewmodel.ext.android.viewModel

class GitItemRepositoriesFragment : Fragment(R.layout.fragment_gititemrepositories) {

    private val viewModel: GitItemRepositoriesViewModel by viewModel()

    private val viewBinding by viewBinding<FragmentGititemrepositoriesBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    companion object {

        private const val NAME = "NAME"
        private const val AUTHOR_NAME = "AUTHOR_NAME"

        fun buildBundle(
            name: String?,
            authorName: String?
        ): Bundle = Bundle().apply {
            putString(NAME, name)
            putString(AUTHOR_NAME, authorName)
        }
    }

    private fun setupObservers() {
        val bundle = requireArguments()
        viewModel.getPullRequests(bundle.getString(NAME), bundle.getString(AUTHOR_NAME))
        viewModel.pullRequestsReceived.observe(this, Observer {
            viewBinding.recyclerPull.layoutManager = LinearLayoutManager(context)
            when(it) {
                is GitViewStates.Error -> {
                    viewBinding.loading.visibility = View.GONE
                }
                is GitViewStates.Loading -> {
                    viewBinding.loading.visibility = View.VISIBLE
                }
                is GitViewStates.Success -> {
                    viewBinding.loading.visibility = View.GONE
                    viewBinding.recyclerPull.adapter = GitItemRepositoriesAdapter(it.data)
                }
            }
        })
    }

    private fun setupToolbar() {
        val bundle = requireArguments()
        viewBinding.toolbarHome.title = bundle.getString(NAME)
        viewBinding.toolbarHome.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_popback_to_gitRepositories)
        }
    }
}