package com.marioioannou.runner.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.marioioannou.runner.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
): ViewModel() {
}