package com.example.gitrepositories.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gitrepositories.R
import com.example.gitrepositories.databinding.FragmentGititemwebviewBinding

class GitItemWebViewFragment : Fragment(R.layout.fragment_gititemwebview) {

    private val viewBinding by viewBinding<FragmentGititemwebviewBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openWebview()
        setupToolbar()
    }

    companion object {

        private const val URL = "URL"
        private const val AUTHOR_NAME = "AUTHOR_NAME"
        private const val REPO_NAME = "REPO_NAME"

        fun buildBundle(
            url: String,
            author: String,
            repo: String
        ): Bundle = Bundle().apply {
            putString(URL, url)
            putString(AUTHOR_NAME, author)
            putString(REPO_NAME, repo)
        }
    }

    private fun openWebview() {
        val bundle = requireArguments()
        bundle.getString(URL)?.let { viewBinding.webview.loadUrl(it) }
    }

    private fun setupToolbar() {
        val bundle = requireArguments()
        val bundleSend = GitItemRepositoriesFragment.buildBundle(
            bundle.getString(REPO_NAME),
            bundle.getString(AUTHOR_NAME)
        )
        viewBinding.toolbarWebview.setNavigationOnClickListener {
            it.findNavController().navigate(
                R.id.action_popback_to_gitItemRepositories,
                bundleSend
            )
        }
    }
}