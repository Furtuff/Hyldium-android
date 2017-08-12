package com.tuff.hyldium.adapter;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuff.hyldium.R;
import com.tuff.hyldium.fragment_callback.UserList;
import com.tuff.hyldium.model.UserModel;
import com.tuff.hyldium.utils.Utils;

import java.util.List;

/**
 * Created by tuffery on 08/08/17.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    List<UserModel> userList;

    public UserListAdapter(List<UserModel> userList) {
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user_list_item, parent, false);
        final UserViewHolder result = new UserViewHolder(itemView);
        result.erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((UserList) view.getContext()).deleteUser(userList.get(result.getAdapterPosition()));
            }
        });
        result.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((UserList) view.getContext()).editUser(userList.get(result.getAdapterPosition()));
            }
        });

        return result;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        if (userList.get(position).name != null) {
            holder.name.setText(userList.get(position).name);
        }
        if (userList.get(position).createdDate != 0) {
            holder.date.setText(Utils.dateMillisToString(userList.get(position).createdDate));
        }
        if (userList.get(position).photo != null) {
            holder.photo.setImageBitmap(BitmapFactory.decodeByteArray(userList.get(position).photo, 0, userList.get(position).photo.length));
        }

    }

    @Override
    public int getItemCount() {
        if (this.userList != null) {
            return this.userList.size();
        } else {
            return 0;
        }
    }

    public void setUserList(List<UserModel> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public ImageView photo;
        public TextView name;
        public TextView date;
        public ImageButton erase;
        public ImageButton edit;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.photo = (ImageView) itemView.findViewById(R.id.userPhoto);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.erase = (ImageButton) itemView.findViewById(R.id.delete);
            this.edit = (ImageButton) itemView.findViewById(R.id.edit);
        }
    }
}
