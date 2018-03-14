package com.darkovr.patm.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darkovr.patm.R;
import com.darkovr.patm.Api.BDEmpleados;
import com.darkovr.patm.Api.Adapters.EmpleadoAdapter;
import com.darkovr.patm.Api.Models.Empleado;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectEmpleadoFragment extends Fragment {
    List<Empleado> list_empleados = new ArrayList<Empleado>();
    @BindView(R.id.main_recycler) RecyclerView mainRecycler;
    BDEmpleados objE;
    SQLiteDatabase objSQLite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_recycler, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppBarLayout appBarLayout = getActivity().findViewById(R.id.appBarMain);
        appBarLayout.setVisibility(AppBarLayout.VISIBLE);
        LoadEmpleados();
    }

    private void LoadEmpleados() {
        objE = new BDEmpleados(getContext(),"CAPACITACION",null,1);
        objSQLite = objE.getWritableDatabase();

        String query = "SELECT * FROM empleado ORDER BY cveemp ASC";
        Cursor cursor = objSQLite.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                list_empleados.add(new Empleado(
                        cursor.getInt(cursor.getColumnIndex("cveemp")),
                        cursor.getInt(cursor.getColumnIndex("cvepuesto")),
                        cursor.getInt(cursor.getColumnIndex("cveusr")),
                        cursor.getString(cursor.getColumnIndex("nomemp")),
                        cursor.getString(cursor.getColumnIndex("apepat")),
                        cursor.getString(cursor.getColumnIndex("apemat")),
                        cursor.getString(cursor.getColumnIndex("fechanac")),
                        cursor.getString(cursor.getColumnIndex("emailemp"))
                ));
            }while (cursor.moveToNext());
            cursor.close();
            mainRecycler.setAdapter(new EmpleadoAdapter(getContext(), list_empleados));
            mainRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }
}