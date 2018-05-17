package ru.semkin.ivan.parttime.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import ru.semkin.ivan.parttime.db.PartTimeDatabase;
import ru.semkin.ivan.parttime.db.PostDao;
import ru.semkin.ivan.parttime.model.Post;

/**
 * Created by Ivan Semkin on 5/11/18
 */
public class PostRepository {

    private final PostDao mPostDao;
    private final LiveData<List<Post>> mAllPosts;

    public PostRepository(Application application) {
        PartTimeDatabase db = PartTimeDatabase.getDatabase(application);
        mPostDao = db.postDao();
        mAllPosts = mPostDao.getAll();
    }

    public LiveData<List<Post>> getAllPosts() {
        return mAllPosts;
    }

    public void insert(Post post) {
        new insertAsyncTask(mPostDao).execute(post);
    }

    private static class insertAsyncTask extends AsyncTask<Post, Void, Void> {

        private final PostDao mAsyncTaskDao;

        insertAsyncTask(PostDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Post... params) {
            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }
}