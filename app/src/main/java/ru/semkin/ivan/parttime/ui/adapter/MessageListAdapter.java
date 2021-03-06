package ru.semkin.ivan.parttime.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.semkin.ivan.parttime.R;
import ru.semkin.ivan.parttime.model.Message;
import ru.semkin.ivan.parttime.util.Util;

/**
 * Created by Ivan Semkin on 5/11/18
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {

    class MessageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bubble_layout_parent) LinearLayout parentLayout;
        @BindView(R.id.bubble_layout) LinearLayout layout;
        @BindView(R.id.message_text) TextView textMessage;
        @BindView(R.id.date_text) TextView textDate;

        private MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Message> mMessages; // Cached copy of messages

    public MessageListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_message, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        if (mMessages != null) {
            Message current = mMessages.get(position);
            String body = current.getBody();
            if(!body.isEmpty())
                holder.textMessage.setText(body);
            else
                holder.textMessage.setText(
                        holder.parentLayout.getContext().getString(R.string.unsupported_media));
            holder.textDate.setText(Util.formatDate(holder.parentLayout.getContext(), current.getDate()));

            if (current.isOut()) {
                holder.layout.setBackgroundResource(R.drawable.rounded_corners_bubble_right);
                holder.parentLayout.setGravity(Gravity.END);
            } else {
                holder.layout.setBackgroundResource(R.drawable.rounded_corners_bubble_left);
                holder.parentLayout.setGravity(Gravity.START);
            }
        } else {
            // Covers the case of data not being ready yet.
            holder.textMessage.setText(R.string.no_content);
            holder.textDate.setText(R.string.no_date);
        }


    }

    public void setMessages(List<Message> messages){
        mMessages = messages;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mMessages has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mMessages != null)
            return mMessages.size();
        else return 0;
    }
}
