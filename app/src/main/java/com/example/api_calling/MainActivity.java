package com.example.api_calling;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.api_calling.Models.Address;
import com.example.api_calling.Models.Company;
import com.example.api_calling.Models.Geo;
import com.example.api_calling.Models.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Address> addressList = new ArrayList<>();
    List<Company> companyList = new ArrayList<>();
    List<Geo> geoList = new ArrayList<>();
    List<Users> usersList = new ArrayList<>();

    ArrayAdapter arrayAdapter;

    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/users",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject userobj = jsonArray.getJSONObject(i);

                                int id = userobj.getInt("id");
                                String name = userobj.getString("name");
                                String username = userobj.getString("username");
                                String email = userobj.getString("email");
                                String phone = userobj.getString("phone");
                                String website = userobj.getString("website");

                                Users users = new Users(id, name, username, email, phone, website);
                                usersList.add(users);

                                JSONObject addressobj = userobj.getJSONObject("address");

                                String street = addressobj.getString("street");
                                String suite = addressobj.getString("suite");
                                String city = addressobj.getString("city");
                                String zipcode = addressobj.getString("zipcode");

                                Address address = new Address(street, suite, city, zipcode);
                                addressList.add(address);

                                JSONObject geoobj = addressobj.getJSONObject("geo");

                                String lat = geoobj.getString("lat");
                                String lng = geoobj.getString("lng");

                                Geo geo = new Geo(lat, lng);
                                geoList.add(geo);

                                JSONObject companyobj = userobj.getJSONObject("company");

                                String comname = companyobj.getString("name");
                                String catchPhrase = companyobj.getString("catchPhrase");
                                String bs = companyobj.getString("bs");

                                Company company = new Company(comname, catchPhrase, bs);
                                companyList.add(company);

                            }

                            arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,usersList);
                            listView.setAdapter(arrayAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

    }
}