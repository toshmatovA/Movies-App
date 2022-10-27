package uz.gita.moviesapp.presentation.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.moviesapp.presentation.viewModel.SplashViewModel
import uz.gita.moviesapp.utils.extension.eventFlow
import uz.gita.moviesapp.utils.extension.eventValueFlow
import uz.gita.moviesapp.utils.internetConnection.isConnected
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor() : SplashViewModel, ViewModel() {
    override val showLoadingFlow = eventValueFlow<Boolean>()
    override val noConnectionFlow = eventValueFlow<Boolean>()
    override val showMassageFlow = eventValueFlow<String>()
    override val openNextScreenFlow = eventFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            if (!isConnected()) {
                noConnectionFlow.emit(false)
                return@launch
            }
            openNextScreenFlow.emit(Unit)
        }
    }
}