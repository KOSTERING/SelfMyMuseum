package com.SelfMyMuseum.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.SelfMyMuseum.R;
import com.SelfMyMuseum.activitys.SearchResultActivity;
import com.SelfMyMuseum.classes.Type;

import java.util.List;

/**
 * Created by Reene on 2017/5/20.
 */

public class TypeListAdapter extends RecyclerView.Adapter<TypeListAdapter.ViewHolder>{
    private List<Type> types;

    private Context mContext;

    public TypeListAdapter(List<Type> list) {
        types = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View typeView;
        ImageView typeImage;
        TextView typeName;

        public ViewHolder(View view) {
            super(view);
            typeView = view;

            typeImage = (ImageView)view.findViewById(R.id.type_image);
            typeName = (TextView)view.findViewById(R.id.type_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.type_cardview,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.typeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Type type = types.get(position);
                Intent intent = new Intent(mContext,SearchResultActivity.class);

                intent.putExtra("searchType","type");
                intent.putExtra("searchString",type.getName().toString());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Type type = types.get(position);
        holder.typeName.setText(type.getName().toString());

        holder.typeImage.setImageResource(type.getImageid());
    }

    @Override
    public int getItemCount() {
        return types.size();
    }
}
