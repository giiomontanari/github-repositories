package com.example.gitrepositories.ui.viewmodel

sealed interface GitViewStates<T> {

    class Loading<T>: GitViewStates<T>

    class Error<T>: GitViewStates<T>

    class Success<T>(val data: T) : GitViewStates<T>
}