package com.darkovr.patm.Api.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.darkovr.patm.R;
import com.darkovr.patm.Api.Models.Empleado;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marco on 11/12/17.
 */

public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.EmpleadoHolder> {

    Context context;
    List<Empleado> empleadoList = new ArrayList<>();

    public EmpleadoAdapter(Context context, List<Empleado> productList){
        this.context = context;
        this.empleadoList = productList;
    }

    @Override
    public EmpleadoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.empleado_row, parent, false);
        EmpleadoHolder productHolder = new EmpleadoHolder(view);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(final EmpleadoHolder holder, final int position) {
        final Empleado empleado = empleadoList.get(position);
        holder.tvName.setText(empleado.getNomemp());
        holder.tvApePat.setText(empleado.getApepat());
        holder.tvApeMat.setText(empleado.getApemat());
        holder.tvFechaNac.setText(empleado.getFechanac());
        holder.tvEmail.setText(empleado.getEmailemp());

        Picasso.with(context).load(R.drawable.user).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return empleadoList.size();
    }

    class EmpleadoHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_nomemp) TextView tvName;
        @BindView(R.id.tv_apepat) TextView tvApePat;
        @BindView(R.id.tv_apemat) TextView tvApeMat;
        @BindView(R.id.tv_fechanac) TextView tvFechaNac;
        @BindView(R.id.tv_email) TextView tvEmail;
        @BindView(R.id.iv_imgempleado) ImageView ivImage;

        public EmpleadoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
