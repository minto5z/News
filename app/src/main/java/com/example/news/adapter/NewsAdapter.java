package com.example.news.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.data.News;
import com.example.news.utilities.DateUtils;
import com.example.news.utilities.NewsImageUtils;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> data = new ArrayList<>();
    private ClickListener mClick;
    private Context context;

    public NewsAdapter(ClickListener mClick) {
        this.mClick = mClick;

    }

    public void setAdapter(List<News> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(row);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.source.setText(data.get(position).getSource().getName());
        holder.date.setText(DateUtils.formatDate(data.get(position).getPublishedAt()));
        holder.category.setText(data.get(position).getCategory());
        NewsImageUtils.setImage(data.get(position).getUrlToImage(), holder.newsImage, context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface ClickListener {
        public void onItemClick(int position);
    }

    protected class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, date, source, category;
        ImageView newsImage;

        public NewsViewHolder(View row) {
            super(row);
            row.setOnClickListener(this);
            title = row.findViewById(R.id.title);
            date = row.findViewById(R.id.date);
            category = row.findViewById(R.id.category);
            source = row.findViewById(R.id.source);
            newsImage = row.findViewById(R.id.newsImage);
        }

        @Override
        public void onClick(View v) {
            mClick.onItemClick(getAdapterPosition());

        }
    }
}
