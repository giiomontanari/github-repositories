package com.example.gitrepositories.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gitrepositories.R
import com.example.gitrepositories.data.dto.GitItemPullRequestDTO
import com.example.gitrepositories.databinding.ItemGititemrepositoriesBinding
import com.example.gitrepositories.ui.view.GitItemWebViewFragment
import com.example.gitrepositories.utils.DateConverter

class GitItemRepositoriesAdapter(
    private val items: List<GitItemPullRequestDTO>
) : RecyclerView.Adapter<GitItemRepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            binding = ItemGititemrepositoriesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: ItemGititemrepositoriesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GitItemPullRequestDTO) {
            with(itemView) {
                binding.nameAutor.text = item.user.login
                binding.titlePr.text = item.titlePullRequest
                binding.bodyPr.text = item.body
                binding.datePr.text = DateConverter().converterDate(item.dateCreated)

                this.setOnClickListener {
                    val bundle = GitItemWebViewFragment.buildBundle(
                        item.htmlUrl,
                        item.head.user.login,
                        item.head.repo.name
                    )
                    it.findNavController().navigate(
                        R.id.action_to_webviewFragment,
                        bundle
                    )
                }
                with(binding.avatarAutor) {
                    Glide.with(this)
                        .load(item.user.avatarUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .placeholder(R.drawable.ic_git)
                        .into(this)
                }
            }
        }
    }
}