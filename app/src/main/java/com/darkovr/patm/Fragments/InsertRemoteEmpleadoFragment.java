package com.darkovr.patm.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.darkovr.patm.Api.Token;
import com.darkovr.patm.BuildConfig;
import com.darkovr.patm.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsertRemoteEmpleadoFragment extends Fragment {

    @BindView(R.id.edtnom) EditText nomemp;
    @BindView(R.id.edtapepat) EditText apepat;
    @BindView(R.id.edtapemat) EditText apemat;
    @BindView(R.id.edtemail) EditText email;
    @BindView(R.id.edtfecha) DatePicker fechanac;
    @BindView(R.id.spnpuesto) Spinner spnpuesto;
    @BindView(R.id.fab) FloatingActionButton fab;
    private final String url= BuildConfig.PROTOCOL+BuildConfig.BASE_URL+BuildConfig.PORT+"/PATM18/api/empleado/insEmpleado";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_empleado, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.fab) public void insert(){
        try {
            if (validate()) insertEmpleado();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void insertEmpleado() throws JSONException {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("nomEmp",nomemp.getText());
            jsonBody.put("apePat",apepat.getText());
            jsonBody.put("apeMat",apemat.getText());
            jsonBody.put("fecNac",Birth(fechanac.getYear(),fechanac.getMonth(),fechanac.getDayOfMonth()));
            jsonBody.put("emailEmp",email.getText());
            jsonBody.put("cvePuesto",1);
            jsonBody.put("cveUsuario",2);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                    Snackbar.make(getView(),"¡Empleado registrado!",Snackbar.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    Snackbar.make(getView(),"Error...",Snackbar.LENGTH_LONG).show();
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("Authorization", "Bearer "+Token.getToken());

                    return params;
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
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

    private String Birth(int year,int month,int day){
        int increase = month + 1;
        String formatMonth,formatDay,Birth;
        if (increase<10){
            formatMonth="0"+increase;
        }else{
            formatMonth=""+increase;
        }
        if (day<10){
            formatDay="0"+day;
        }else{
            formatDay=""+day;
        }
        Birth=year+"-"+formatMonth+"-"+formatDay+"T00:00:00Z";

        return Birth;
    }
}