package com.abadzheva.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private NoteDatabase noteDatabase;
    private int count = 0;
    private MutableLiveData<Integer> countLD = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        noteDatabase = NoteDatabase.getInstance(application);
    }

    public LiveData<List<Note>> getNotes() {
        return noteDatabase.notesDao().getNotes();
    }

    public void showCount() {
        count++;
        countLD.setValue(count);
    }

    public LiveData<Integer> getCount() {
        return countLD;
    }

    public void remove (Note note) {
        Thread thread = new Thread(() -> noteDatabase.notesDao().remove(note.getId()));
        thread.start();
    }
}
