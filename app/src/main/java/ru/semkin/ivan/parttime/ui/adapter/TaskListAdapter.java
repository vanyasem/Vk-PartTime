package ru.semkin.ivan.parttime.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.semkin.ivan.parttime.R;
import ru.semkin.ivan.parttime.model.Task;
import ru.semkin.ivan.parttime.util.Util;

/**
 * Created by Ivan Semkin on 5/11/18
 */
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    class TaskViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textBody) TextView taskBody;
        @BindView(R.id.textDue) TextView taskDue;

        private TaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Task> mTasks; // Cached copy of tasks

    public TaskListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_task, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        if (mTasks != null) {
            Task current = mTasks.get(position);
            holder.taskBody.setText(current.getBody());
            if(current.getType() == Task.TYPE_MESSAGE)
                holder.taskDue.setText(Util.formatDate(
                        holder.taskDue.getContext(), current.getDate() + Task.TS_MESSAGE));
            else
                holder.taskDue.setText(Util.formatDate(
                    holder.taskDue.getContext(), current.getDate() + Task.TS_POST));
        } else {
            // Covers the case of data not being ready yet.
            holder.taskBody.setText(R.string.no_content);
            holder.taskDue.setText(R.string.no_date);
        }
    }

    public void setTasks(List<Task> tasks){
        mTasks = tasks;
        notifyDataSetChanged();
    }

    public Task get(int position) {
        return mTasks.get(position);
    }

    // getItemCount() is called many times, and when it is first called,
    // mTasks has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }
}
