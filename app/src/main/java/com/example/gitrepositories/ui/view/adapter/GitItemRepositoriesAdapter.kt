package com.example.gitrepositories.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepositories.R
import com.example.gitrepositories.data.dto.GitItemPullRequestDTO
import kotlinx.android.synthetic.main.item_gititemrepositories.view.*

class GitItemRepositoriesAdapter(
    private val items: List<GitItemPullRequestDTO>
) : RecyclerView.Adapter<GitItemRepositoriesAdapter.GitItemRepositoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitItemRepositoriesAdapter.GitItemRepositoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_gititemrepositories, parent, false
        )
        return GitItemRepositoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: GitItemRepositoriesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class GitItemRepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: GitItemPullRequestDTO) {
            with(itemView) {
                item_title.text = item.titlePullRequest
            }
        }
    }
}