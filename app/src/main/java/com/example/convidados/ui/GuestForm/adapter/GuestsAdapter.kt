package com.example.convidados.ui.GuestForm.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.model.GuestModel
import com.example.convidados.ui.GuestForm.viewHolder.GuestViewHolder

class GuestsAdapter: RecyclerView.Adapter<GuestViewHolder>() {

    private var guestList: List<GuestModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuests(list: List<GuestModel>) {
        guestList = list
    }

}