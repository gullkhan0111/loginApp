package com.example.haseeb.loginapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haseeb.loginapp.R;
import com.example.haseeb.loginapp.models.User;

import java.util.ArrayList;
import java.util.List;


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private List<User> data = new ArrayList<>();
    private UserListListener listener;
    private Context context;


    public UserListAdapter(UserListListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    public void addData(List<User> data) {
        this.data.clear();
        this.data.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public UserListAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.userlist, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final UserListAdapter.UserViewHolder holder, int position) {
        holder.userlst.setText(data.get(position).getUsername());

        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDelete(data.get(holder.getAdapterPosition()).getUid(), holder.getAdapterPosition());
            }
        });
        holder.userlst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(data.get(holder.getAdapterPosition()));
            }
        });

    }

    public void itemDelete(int index) {
        data.remove(index);
        notifyItemRemoved(index);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userlst;
        ImageView deleteIcon;

        public UserViewHolder(View itemView) {
            super(itemView);
            userlst = itemView.findViewById(R.id.UserName);
            deleteIcon = itemView.findViewById(R.id.deleteButton);
        }
    }

    public interface UserListListener {
        void onDelete(int id, int index);

        void onClick(User user);

    }

}


