package com.gimenez.danielgga.dihi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MedicoLogin extends AppCompatActivity {

    private Button button_ingresar;
    private Button button_reg;
    private EditText email,password;
    private RequestQueue requestQueue;
    private static final String URL = "http://192.168.1.2/test4/user_medicos2.php";
    private static final String URL2 = "http://192.168.1.6/test4/user_control2.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_login);
        OnClickAPaciente();

        email = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);
        button_ingresar = (Button) findViewById(R.id.button5);

        requestQueue = Volley.newRequestQueue(this);


    }

    public void OnClickAPaciente() {

        button_ingresar = (Button)findViewById(R.id.button5);
        button_ingresar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if(jsonObject.names().get(0).equals("success")){
                                        Toast.makeText(getApplicationContext(),"SUCCESS "+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MedicoAccion.class)); // Revisar clase
                                    }else {
                                        Toast.makeText(getApplicationContext(), "Error" +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> hashMap = new HashMap<String, String>();
                                hashMap.put("email",email.getText().toString());
                                hashMap.put("password",password.getText().toString());

                                return hashMap;
                            }
                        };

                        requestQueue.add(request);
                    }
                });
    }




}


