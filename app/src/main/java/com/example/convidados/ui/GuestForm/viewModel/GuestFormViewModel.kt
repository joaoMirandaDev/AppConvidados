package com.example.convidados.ui.GuestForm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application)
    fun insert(guestModel: GuestModel): Boolean {
           return repository.insert(guestModel)
    }
}