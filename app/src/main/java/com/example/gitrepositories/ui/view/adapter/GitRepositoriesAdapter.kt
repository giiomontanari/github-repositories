package com.example.gitrepositories.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gitrepositories.R
import com.example.gitrepositories.data.dto.GitHubItemsDTO
import com.example.gitrepositories.ui.view.GitItemRepositoriesFragment
import kotlinx.android.synthetic.main.item_gitrepositories.view.*

class GitRepositoriesAdapter(
    private val items: List<GitHubItemsDTO>
) : RecyclerView.Adapter<GitRepositoriesAdapter.GitRepositoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepositoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_gitrepositories, parent, false
        )

        return GitRepositoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: GitRepositoriesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class GitRepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: GitHubItemsDTO) {
            with(itemView) {
                title.text = item.name
                description.text = item.description
                author.text = item.owner.login
                stars.text = item.stargazersCount.toString()
                fork.text = item.forksCount.toString()
                setOnClickListener {
                    val bundle = GitItemRepositoriesFragment.buildBundle(item.name, item.owner.login)
                    it.findNavController().navigate(
                        R.id.action_gitRepositoriesFragment_to_gitItemRepositoresFragment,
                        bundle
                    )
                }
            }
            with(itemView.avatar) {
                Glide.with(this)
                    .load(item.owner.avatarUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.ic_git)
                    .into(this)
            }
        }
    }
}