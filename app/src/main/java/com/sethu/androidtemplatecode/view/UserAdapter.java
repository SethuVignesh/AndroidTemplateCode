package com.sethu.androidtemplatecode.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sethu.androidtemplatecode.R;
import com.sethu.androidtemplatecode.model.User;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private final Context context;
    private List<User> userList;

    public UserAdapter(List<User> usersList, Context context) {
        this.userList = usersList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       User user = userList.get(position);
        holder.id.setText(user.getUserId());
        holder.firstName.setText(user.getFirstName());
        holder.lastName.setText(user.getLastName());
        Picasso.with(context)
                .load(user.getAvatar())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.userid)
        TextView id;
        @BindView(R.id.first_name)
        TextView firstName ;
        @BindView(R.id.last_name)
        TextView lastName ;
        @BindView(R.id.imageView2)
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}