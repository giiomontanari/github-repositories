package com.example.gitrepositories.di

import com.example.gitrepositories.data.repository.GitRepository
import com.example.gitrepositories.ui.viewmodel.GitRepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GitRepositoriesViewModel(get()) }
}

val repositoryModule = module {
    factory {
        GitRepository(get())
    }
}