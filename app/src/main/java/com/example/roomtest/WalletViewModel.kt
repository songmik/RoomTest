package com.example.roomtest

import androidx.lifecycle.*
import com.example.roomtest.data.Wallet
import com.example.roomtest.data.WalletRepository
import kotlinx.coroutines.launch

class WalletViewModel(private val repository: WalletRepository): ViewModel() {

    val allWallets : LiveData<List<Wallet>> = repository.allWallets.asLiveData()

    fun insert(wallet: Wallet) = viewModelScope.launch {
        repository.insert(wallet)
    }
}

// WalletViewModel을 만드는 데 필요한 종속 항목(WalletRepository)을 매개변수로 가져오는 WalletProvider.Factory를 구현

class WalletViewModelFactory(private val repository: WalletRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WalletViewModel::class.java)) {
            @Suppress("UNCHECKED_CASE")
            return WalletViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}