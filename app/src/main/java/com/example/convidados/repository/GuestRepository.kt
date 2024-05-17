package com.example.convidados.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.model.GuestModel
import java.util.Objects

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
    fun insert(guest: GuestModel): Boolean {
      return  try {
          val db =  guestDataBase.writableDatabase
          val values = ContentValues()
          val presence = if (guest.presence) 1 else 0
          values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
          values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
          db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
          true
        } catch (e : Exception) {
            false
        }
    }
    fun save() {
        GuestDataBase(null)
    }

    fun update(guest: GuestModel): Boolean {
       return try {
            val db = guestDataBase.writableDatabase
            val values = ContentValues()

            val presence = if (guest.presence) 1 else 0
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())
            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }
    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())
            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    @SuppressLint("Range")
    fun getAll(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase
            val selection = arrayOf(DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )
            val dados = db.query(DataBaseConstants.GUEST.TABLE_NAME, selection,
                null, null, null, null, null
            )
            if (Objects.nonNull(dados) && dados.count > 0) {
                while (dados.moveToNext()) {
                    val id = dados.getInt(dados.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =  dados.getString(dados.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = dados.getInt(dados.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            dados.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    @SuppressLint("Range")
    fun getPresent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase
            val args = arrayOf("1")
            val dados = db.rawQuery("SELECT * FROM Guest WHERE presence = 1", args)


            if (Objects.nonNull(dados) && dados.count > 0) {
                while (dados.moveToNext()) {
                    val id = dados.getInt(dados.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =  dados.getString(dados.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = dados.getInt(dados.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            dados.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    @SuppressLint("Range")
    fun getAbsent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        val absent = 0
        try {
            val db = guestDataBase.readableDatabase
            val dados = db.rawQuery("SELECT * FROM Guest WHERE presence = $absent", null)

            if (Objects.nonNull(dados) && dados.count > 0) {
                while (dados.moveToNext()) {
                    val id = dados.getInt(dados.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =  dados.getString(dados.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = dados.getInt(dados.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            dados.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }
}