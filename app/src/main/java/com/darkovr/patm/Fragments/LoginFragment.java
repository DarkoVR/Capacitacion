package com.darkovr.patm.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.darkovr.patm.Activities.MainActivity;
import com.darkovr.patm.Api.Token;
import com.darkovr.patm.BuildConfig;
import com.darkovr.patm.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    @BindView(R.id.txtUser) EditText txtUser;
    @BindView(R.id.txtPassword) EditText txtPassword;
    @BindView(R.id.chkSesion) CheckBox chkSesion;
    @BindView(R.id.btLogin) Button btLogin;
    private final String url= BuildConfig.PROTOCOL+BuildConfig.BASE_URL+BuildConfig.PORT+"/PATM18/api/auth";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.btLogin) public void Login() {
        try {
            loadUser();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadUser() throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("usuario",txtUser.getText());
        jsonObject.put("contrasena",txtPassword.getText());

        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Token.setToken(response.getString("token"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                        getActivity().finish();
                        //Toast.makeText(getContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(getView(), "Revisa usuario o contraseña", Snackbar.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjReq);
    }
}