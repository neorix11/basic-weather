package com.bluelampcreative.basicweather.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluelampcreative.basicweather.R;
import com.bluelampcreative.basicweather.models.ForecastDay;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {


    private List<ForecastDay> items = new ArrayList<>();
    private Context context;

    public void setItems(List<ForecastDay> items) {
        this.items.clear();
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_day, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.titletext.setText(items.get(position).getTitle());
        holder.report.setText(items.get(position).getFcttext());


        Picasso.with(context)
                .load(items.get(position).getIcon_url())
                .resize(50, 50)
                .centerCrop()
                .placeholder(R.drawable.img_ph)
                .into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title)
        TextView titletext;
        @BindView(R.id.txt_report)
        TextView report;
        @BindView(R.id.img_weather)
        ImageView weatherIcon;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
