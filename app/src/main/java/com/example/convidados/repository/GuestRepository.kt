package com.example.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    // Singleton -> controla o numero de instancia da classe
    companion object {
        //lateinit var fala que o objeto vai ser iniciado depois
        private lateinit var repository: GuestRepository
        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }
    fun insert(guest: GuestModel) {
      val db =  guestDataBase.writableDatabase
        val values = ContentValues()
        values.put("name", guest.name)
        val presence = if (guest.presence) 1 else 0
        values.put("presence", presence)
        db.insert("Guest", null, values)
    }
    fun save() {
        GuestDataBase(null)
    }
}