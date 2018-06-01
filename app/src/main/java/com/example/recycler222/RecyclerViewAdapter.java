package com.example.recycler222;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mExtra = new ArrayList<>();

    private ArrayList<String> mImageNames = new ArrayList<>();

    private ArrayList<String> mImages = new ArrayList<>();

    private ArrayList<String> mIDs = new ArrayList<>();
    private ArrayList<String> mNumbers = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images, ArrayList<String> extra, ArrayList<String> ids,  ArrayList<String> numbers)
    {
        mIDs = ids;
        mImageNames = imageNames;
        mImages = images;
        mContext = context;
        mExtra =  extra;
        mNumbers = numbers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.parentLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String s1 =holder.txtViewId.getText().toString();

                Intent intent=new Intent(mContext, Update.class);
                intent.putExtra("idValue",s1);
                mContext.startActivity(intent);
            }

        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.imageName.setText(mImageNames.get(position));

        holder.text_extra.setText(mExtra.get(position));
        holder.txtViewId.setText(mIDs.get(position));

        holder.txtViewNumber.setText(mNumbers.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;

        TextView imageName;
        RelativeLayout parentLayout;
        TextView text_extra;
        TextView txtViewId;
        TextView txtViewNumber;

        public ViewHolder(View itemView)
        {
            super(itemView);

            txtViewId = itemView.findViewById(R.id.textViewIDItem);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            text_extra = itemView.findViewById(R.id.text_extra_rv);
            txtViewNumber = itemView.findViewById(R.id.textViewNumberItem);
        }
    }
}