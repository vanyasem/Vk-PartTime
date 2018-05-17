package ru.semkin.ivan.parttime.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.semkin.ivan.parttime.model.Message;

/**
 * Created by Ivan Semkin on 5/10/18
 */
@Dao
public interface MessageDao {
    @Query("SELECT * FROM message")
    LiveData<List<Message>> getAll();

    @Query("SELECT * FROM message WHERE uid IN (:userIds)")
    LiveData<List<Message>> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Message... messages);

    @Delete
    void delete(Message message);

    @Query("DELETE FROM message")
    void deleteAll();
}