package com.example.gitrepositories.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitrepositories.R
import com.example.gitrepositories.ui.view.adapter.GitItemRepositoriesAdapter
import com.example.gitrepositories.ui.viewmodel.GitItemRepositoriesViewModel
import kotlinx.android.synthetic.main.fragment_gititemrepositories.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GitItemRepositoriesFragment : Fragment(R.layout.fragment_gititemrepositories) {

    private val viewModel: GitItemRepositoriesViewModel by viewModel()

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
            recycler_pull.layoutManager = LinearLayoutManager(context)
            recycler_pull.adapter = GitItemRepositoriesAdapter(it)
        })
    }

    private fun setupToolbar() {
        val bundle = requireArguments()
        toolbar_home.title = bundle.getString(NAME)
        toolbar_home.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_popback_to_gitRepositories)
        }
    }
}