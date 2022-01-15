package com.example.gitrepositories.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gitrepositories.R
import kotlinx.android.synthetic.main.fragment_gititemrepositories.*

class GitItemRepositoriesFragment : Fragment(R.layout.fragment_gititemrepositories) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    companion object {

        private const val NAME = "NAME"
        private const val AUTHOR_NAME = "AUTHOR_NAME"

        fun buildBundle(
            name: String,
            authorName: String
        ): Bundle = Bundle().apply {
            putString(NAME, name)
            putString(AUTHOR_NAME, authorName)
        }
    }

    private fun setupObservers() {
        val bundle = arguments?.getBundle(NAME)
        print(bundle)
        um.text = bundle?.getString("NAME")
        dois.text = bundle?.getString("AUTHOR_NAME")
    }
}