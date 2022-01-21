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

class GitRepositoriesViewModel(
    private val repository: GitRepository
) : ViewModel() {

    private val _githubReceived = MutableLiveData<GitViewStates<GitHubRepositoriesDTO>>()

    val githubReceived: LiveData<GitViewStates<GitHubRepositoriesDTO>> = _githubReceived

    fun getGithub() {
        viewModelScope.launch {
            _githubReceived.value = GitViewStates.Loading()
            repository.fetchGithub()
                .catch {
                    _githubReceived.value = GitViewStates.Error()
                }
                .collect { response ->
                    _githubReceived.value = GitViewStates.Success(response)
                }
        }
    }
}
