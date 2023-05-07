package com.ismaildev.roomcrudbasic

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.room.Room
import com.ismaildev.roomcrudbasic.db.Note
import com.ismaildev.roomcrudbasic.db.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var db:NoteDatabase
    lateinit var title: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = findViewById<TextView>(R.id.title)
        db = Room.databaseBuilder(applicationContext,NoteDatabase::class.java,"note-db").build()
        insertData()
        showData()
    }

    private fun showData() {
        val notes=db.getNoteDao().getNotes()
        var display = ""
        notes.forEach {
            display += "${it.title} isinya ${it.desc}\\n" }
    }

    private fun insertData() {
        val note1= Note(title = "Note1", desc = "Desc Note 1")
        val note2= Note(title = "Note2", desc = "Desc Note 2")
        GlobalScope.launch {
            db.getNoteDao().insertNote(note1)
            db.getNoteDao().insertNote(note2)
        }

    }

}