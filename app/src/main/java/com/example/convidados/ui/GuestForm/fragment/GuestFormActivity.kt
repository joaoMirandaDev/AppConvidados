package com.example.convidados.ui.GuestForm.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.ui.GuestForm.viewModel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)
        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.button_save) {
            val nome = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(0, nome, presence)
            viewModel.insert(model)
        }
    }
}