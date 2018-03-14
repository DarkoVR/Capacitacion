package com.darkovr.patm.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.darkovr.patm.R;
import com.darkovr.patm.Api.BDEmpleados;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsertEmpleadoFragment extends Fragment {

    @BindView(R.id.edtnom) EditText nomemp;
    @BindView(R.id.edtapepat) EditText apepat;
    @BindView(R.id.edtapemat) EditText apemat;
    @BindView(R.id.edtemail) EditText email;
    @BindView(R.id.edtfecha) DatePicker fechanac;
    @BindView(R.id.spnpuesto) Spinner spnpuesto;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    BDEmpleados objE;
    SQLiteDatabase objSQLite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_empleado, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        objE = new BDEmpleados(getContext(),"CAPACITACION",null,1);
        objSQLite = objE.getWritableDatabase();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item);
        String query="SELECT * FROM puesto ORDER BY cvepuesto ASC";
        final Cursor cursor = objSQLite.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                adapter.add(cursor.getString(1));
            }while (cursor.moveToNext());
            spnpuesto.setAdapter(adapter);
        }
    }

    @OnClick(R.id.fab) public void insert(){
        String nombre = nomemp.getText().toString();
        String a_paterno = apepat.getText().toString();
        String a_materno = apemat.getText().toString();
        String fecha = fechanac.getYear()+"-"+fechanac.getMonth()+"-"+fechanac.getDayOfMonth();
        String correo = email.getText().toString();
        int usuario = 200;

        int puesto = 1;
        String query = "SELECT cvepuesto FROM puesto where puesto='"+spnpuesto.getSelectedItem().toString()+"'";
        Cursor cursor1 = objSQLite.rawQuery(query,null);
        if (cursor1.moveToFirst()){
            puesto = cursor1.getInt(0);
        }

        String sql = "INSERT INTO empleado(nomemp,apepat,apemat,fechanac,emailemp,cvepuesto,cveusr) values " +
                "('"+nombre+"','"+a_paterno+"','"+a_materno+"','"+fecha+"','"+correo+"',"+puesto+","+usuario+");";

        if (validate()){
            Toast.makeText(getContext(),"cvepuesto: "+puesto,Toast.LENGTH_SHORT).show();
            objSQLite.execSQL("PRAGMA FOREIGN_KEYS = ON");
            objSQLite.execSQL(sql);
            objSQLite.execSQL("PRAGMA FOREIGN_KEYS = OFF");
            Snackbar.make(getView(), "¡Insertado chavo! :D" , Snackbar.LENGTH_LONG).show();
            //Toast.makeText(getContext(),objSQLite.toString(),Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Fields validation
     * @return
     */
    public boolean validate(){
        boolean validation = true;
        if (nomemp.getText().length()==0) {
            nomemp.setError("¡Campo vacio!");
            validation = false;
        }
        if (apepat.getText().length()==0){
            apepat.setError("¡Campo vacio!");
            validation = false;
        }
        if (apemat.getText().length()==0){
            apemat.setError("¡Campo vacio!");
            validation = false;
        }
        if (!isValidEmail(email.getText().toString())){
            email.setError("¡Email no valido!");
            validation = false;
        }
        return validation;
    }

    /**
     * Regular expression to validate email
     * @param email
     * @return
     */
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}