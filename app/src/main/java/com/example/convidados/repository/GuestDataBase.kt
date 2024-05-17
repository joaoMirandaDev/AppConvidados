package com.example.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.convidados.constants.DataBaseConstants

//Contex e a essencia da aplicação
class GuestDataBase(context: Context?) : SQLiteOpenHelper(context, NAME ,null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE" + DataBaseConstants.GUEST.TABLE_NAME + "( " +
                DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, " +
                DataBaseConstants.GUEST.COLUMNS.NAME + " name text, " +
                DataBaseConstants.GUEST.COLUMNS.PRESENCE + " presence integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}