package com.example.gitrepositories.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitrepositories.data.dto.GitItemPullRequestDTO
import com.example.gitrepositories.data.repository.GitRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

typealias RepositoryState = GitViewStates<List<GitItemPullRequestDTO>>

class GitItemRepositoriesViewModel(
    private val repository: GitRepository
) : ViewModel() {

    private val _pullRequestsReceived = MutableLiveData<RepositoryState>()

    val pullRequestsReceived: LiveData<RepositoryState> = _pullRequestsReceived

    fun getPullRequests(name: String?, nameAuthor: String?) {

        viewModelScope.launch {

            _pullRequestsReceived.value = GitViewStates.Loading()
            repository.fetchPullRequests(name, nameAuthor)

                .catch {
                    _pullRequestsReceived.value = GitViewStates.Error()
                }

                .collect { response ->
                    _pullRequestsReceived.value = GitViewStates.Success(response)
                }
        }
    }
}