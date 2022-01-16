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
import timber.log.Timber

class GitItemRepositoriesViewModel(
    private val repository: GitRepository
) : ViewModel() {

    private val _pullRequestsReceived = MutableLiveData<List<GitItemPullRequestDTO>>()

    val pullRequestsReceived: LiveData<List<GitItemPullRequestDTO>> = _pullRequestsReceived

    fun getPullRequests(name: String?, nameAuthor: String?) {
        viewModelScope.launch {
            repository.fetchPullRequests(name, nameAuthor)
                .catch {
                    Timber.d("Error")
                }
                .collect { response ->
                    _pullRequestsReceived.value = response
                }
        }
    }
}