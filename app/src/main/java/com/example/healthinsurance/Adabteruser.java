package com.example.healthinsurance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adabteruser extends RecyclerView.Adapter<Adabteruser.viewHolder> {
        List<UserInfo> userinfo;
        Context context;


public Adabteruser(Context context,List<UserInfo> userinfo) {
        this.userinfo=userinfo;
        this.context=context;
        }

@NonNull
@Override
public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_user,parent,false);
        return new viewHolder(view) ;
        }


@Override
public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        UserInfo item = userinfo.get(position);
    holder.id.setText(item.getId());
    holder.name.setText(item.getName());
    holder.contact.setText(item.getContact());

        }

@Override
public int getItemCount() {
        return userinfo.size();
        }

class viewHolder extends RecyclerView.ViewHolder{

    ImageView imageuser;
    TextView id;
    TextView name;
    TextView contact;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        id=itemView.findViewById(R.id.idh1);
        name=itemView.findViewById(R.id.name1);
        contact=itemView.findViewById(R.id.contact1);





    }}
}

