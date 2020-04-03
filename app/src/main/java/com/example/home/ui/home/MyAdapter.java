package com.example.home.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String data1[],data2[],data3[];
    Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView codigo,destino,fecha;
        // each data item is just a string in this case
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigo=itemView.findViewById(R.id.id_salida);
            destino=itemView.findViewById(R.id.id_destino);
            fecha=itemView.findViewById(R.id.id_fecha);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context ct, String s1[], String s2[], String s3[]) {
            context=ct;
            data1=s1;
            data2=s2;
            data3=s3;
        ;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  =inflater.inflate(R.layout.homecard,parent,false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.codigo.setText(data1[position]);
        holder.destino.setText(data2[position]);
        holder.fecha.setText(data3[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data1.length;
    }
}