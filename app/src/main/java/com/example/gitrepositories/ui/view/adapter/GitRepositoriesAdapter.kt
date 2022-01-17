package com.example.gitrepositories.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gitrepositories.R
import com.example.gitrepositories.data.dto.GitHubItemsDTO
import com.example.gitrepositories.databinding.ItemGitrepositoriesBinding
import com.example.gitrepositories.ui.view.GitItemRepositoriesFragment

class GitRepositoriesAdapter(
    private val items: List<GitHubItemsDTO>
) : RecyclerView.Adapter<GitRepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            binding = ItemGitrepositoriesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: ItemGitrepositoriesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GitHubItemsDTO) {
            with(itemView) {
                binding.title.text = item.name
                binding.description.text = item.description
                binding.author.text = item.owner.login
                binding.stars.text = item.stargazersCount.toString()
                binding.fork.text = item.forksCount.toString()
                setOnClickListener {
                    val bundle = GitItemRepositoriesFragment.buildBundle(item.name, item.owner.login)
                    it.findNavController().navigate(
                        R.id.action_gitRepositoriesFragment_to_gitItemRepositoresFragment,
                        bundle
                    )
                }
                with(binding.avatar) {
                    Glide.with(this)
                        .load(item.owner.avatarUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .placeholder(R.drawable.ic_git)
                        .into(this)
                }
            }
        }
    }
}