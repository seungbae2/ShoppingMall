package com.example.domain.usecase

import com.example.domain.model.AccountInfo
import com.example.domain.repository.AccountRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    fun getAccountInfo() : StateFlow<AccountInfo?>{
        return accountRepository.getAccountInfo()
    }

    suspend fun signIn(accountInfo: AccountInfo) {
        accountRepository.signIn(accountInfo)
    }

    suspend fun  signOut() {
        accountRepository.signOut()
    }
}