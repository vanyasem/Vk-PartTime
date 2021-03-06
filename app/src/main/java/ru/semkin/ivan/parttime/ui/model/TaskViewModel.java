package ru.semkin.ivan.parttime.ui.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import ru.semkin.ivan.parttime.data.TaskRepository;
import ru.semkin.ivan.parttime.model.Task;

/**
 * Created by Ivan Semkin on 5/11/18
 */
public class TaskViewModel extends AndroidViewModel {

    private final TaskRepository mRepository;

    public TaskViewModel(Application application) {
        super(application);
        mRepository = new TaskRepository(application);
    }

    public LiveData<List<Task>> getAllActive() {
        return mRepository.getAllActive();
    }

    public void markDone(Task task) {
        mRepository.markDone(task);
    }
}
