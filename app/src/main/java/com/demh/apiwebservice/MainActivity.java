package com.demh.apiwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.demh.apiwebservice.webservice.Asynchtask;
import com.demh.apiwebservice.webservice.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View viewlista){
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService ("https://gorest.co.in/public/v1/users", datos,
                MainActivity. this, MainActivity. this) ;
        ws.execute ("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtlistusuario= findViewById(R.id.txtulistuario);
        String list = "";
        JSONObject objecjson = new JSONObject(result);
        JSONArray JSONLista = objecjson.getJSONArray("data");
        for(int i =0; i < JSONLista.length();i++){
            JSONObject mostrarusuario = JSONLista.getJSONObject(i);
            list = list +
                    "ID: " + mostrarusuario.getString("id").toString() +
                    "\n Nombre: " + mostrarusuario.getString("name").toString() +
                    "\n Email: " + mostrarusuario.getString("email").toString() +
                    "\n Genero: "+ mostrarusuario.getString("gender").toString() +
                    "\n Estado: "+ mostrarusuario.getString("status").toString() +
                   "\n\n";
        }
        txtlistusuario.setText(list);
    }
}