package com.ismaildev.roomcrudbasic.db

import androidx.room.*


@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun  deleteNotes(note: Note)

    @Query("SELECT*FROM note_table")
    fun getNotes():List<Note>


}