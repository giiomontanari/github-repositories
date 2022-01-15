package com.example.gitrepositories.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitrepositories.data.dto.GitHubRepositoriesDTO
import com.example.gitrepositories.data.repository.GitRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class GitRepositoriesViewModel(
    private val repository: GitRepository
) : ViewModel() {

    private val _githubReceived = MutableLiveData<GitHubRepositoriesDTO>()

    val githubReceived: LiveData<GitHubRepositoriesDTO> = _githubReceived

    fun getGithub() {
        viewModelScope.launch {
            repository.fetchGithub()
                .catch {
                    Timber.d("Error")
                }
                .collect { response ->
                    _githubReceived.value = response
                }
        }
    }
}
