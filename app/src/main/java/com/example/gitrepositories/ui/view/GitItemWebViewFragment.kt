package com.example.gitrepositories.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.gitrepositories.R
import kotlinx.android.synthetic.main.fragment_gititemrepositories.*
import kotlinx.android.synthetic.main.fragment_gititemwebview.*

class GitItemWebViewFragment : Fragment(R.layout.fragment_gititemwebview) {

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
        bundle.getString(URL)?.let { webview.loadUrl(it) }
    }

    private fun setupToolbar() {
        val bundle = requireArguments()
        val bundleSend = GitItemRepositoriesFragment.buildBundle(
            bundle.getString(REPO_NAME),
            bundle.getString(AUTHOR_NAME)
        )
        toolbar_webview.setNavigationOnClickListener {
            it.findNavController().navigate(
                R.id.action_popback_to_gitItemRepositories,
                bundleSend
            )
        }
    }
}