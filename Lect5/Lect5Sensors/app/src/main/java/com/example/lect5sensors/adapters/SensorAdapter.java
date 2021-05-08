package com.example.lect5sensors.adapters;

import android.content.Context;
import android.hardware.Sensor;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lect5sensors.R;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorHolder> {

    private List<Sensor> sensors;

    public SensorAdapter(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public int getItemCount() {
        return this.sensors.size();
    }

    @NonNull
    @Override
    public SensorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.item_sensor, parent, false);

        return new SensorHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorHolder holder, int position) {
        Sensor sensor = this.sensors.get(position);

        holder.bind(sensor);
    }

    private class SensorHolder extends RecyclerView.ViewHolder {
        private Sensor sensor;
        private TextView tvSensor;

        public SensorHolder(@NonNull View itemView, final Context context) {
            super(itemView);

            tvSensor = itemView.findViewById(R.id.tvSensor);

            // handle click event
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, sensor.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bind(Sensor sensor) {
            this.sensor = sensor;
            tvSensor.setText(sensor.getName());
        }
    }
}
