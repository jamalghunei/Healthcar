package com.example.healthinsurance;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class HealthInsu extends Fragment {
    ArrayList<UserInfo> userInfos;
    RecyclerView recycleuser;
    RequestQueue u;
    TextView name;
    ArrayList<UserInfo> user;
    TextView number;
    TextView type;
    TextView status;
    TextView place;
    TextView startdate;
    TextView enddate;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.fragment_health_insu, container, false);
        u = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://apps.moh.gov.ps/mohmob/RESTAPI/HinsAPI";
        user=new ArrayList<>();
        recycleuser = view.findViewById(R.id.recycleuser);
        name = view.findViewById(R.id.textView2);
        number = view.findViewById(R.id.healthnumber);
        type = view.findViewById(R.id.tybe);
        status = view.findViewById(R.id.status);
        place = view.findViewById(R.id.place);
        startdate = view.findViewById(R.id.start);
        enddate = view.findViewById(R.id.end);
        context=getActivity().getApplicationContext();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getActivity().getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("result");
                    for (int i = 0; i < array.length(); i++) {
                        if (i == 0) {
                            JSONObject object1 = array.getJSONObject(i);
                            String name1 = object1.getString("FULL_NAME_AR");
                            String number1 = object1.getString("CARD_NO");
                            String type1 = object1.getString("INS_TYPE_DESC");
                            String statuse1 = object1.getString("INS_STATUS_DESC");
                            String plase1 = object1.getString("CLINIC_CODE_DESC");
                            String startdate1 = object1.getString("START_DATE");
                            String enddate1 = object1.getString("EXP_DATE");
                            name.setText(name1);
                            number.setText(number1);
                            type.setText(type1);
                            status.setText(statuse1);
                            place.setText(plase1);
                            startdate.setText(startdate1);
                            enddate.setText(enddate1);
                        } else {
                            if (i > 0) {
                                JSONObject object1 = array.getJSONObject(i);
                                String name = object1.getString("DEP_NAME");
                                String contact = object1.getString("REL_TYPE_DESC");
                                String id = object1.getString("DEP_ID_NO");
                                UserInfo n = new UserInfo();
                                n.setName(name);
                                n.setContact(contact);
                                n.setId(id);
                                user.add(n);

                            }
                        }
                    }
                    recycleuser.setLayoutManager(new LinearLayoutManager(context));
                    Adabteruser adabteruser=new Adabteruser(context, user);
                    recycleuser.setAdapter(adabteruser);





                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error+ "",Toast.LENGTH_SHORT).show();

            }
        })
        {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("User-ID","801064759");
                headers.put("Authorization",
                        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjE2YjgyNDhhNTIwYjljNzUyNzk5MWNmOThjMWI2OGYxZGU2Njc1MjQ5MDkwNTY3Y2I4NGFkZGFhYzZkMjJjNmVhMDNmZjFkNmRhNzdiMDA2In0.eyJhdWQiOiJBUFBfTU9IIiwianRpIjoiMTZiODI0OGE1MjBiOWM3NTI3OTkxY2Y5OGMxYjY4ZjFkZTY2NzUyNDkwOTA1NjdjYjg0YWRkYWFjNmQyMmM2ZWEwM2ZmMWQ2ZGE3N2IwMDYiLCJpYXQiOjE1ODQwMDc2NDUsIm5iZiI6MTU4NDAwNzY0NSwiZXhwIjoxNTg0MDMyODQ1LCJzdWIiOiI4MDEwNjQ3NTkiLCJzY29wZXMiOlsiYmFzaWMiXX0.LAq2tagOz_e8jYp6NqlfOjkGclLecORjPDU-BsBWyqIjpb_2PQ7bZSALuq2izaq--PX3jnkR_j1YrgjtK_G2WauFaoYhPiqghbRaVa-QiaqOWU-E-Rzz5IksRhPPsZlyeIrGTTkM0d7Izvq9PdSiZLAx7vq4s2UbvY0GeLNxrPA");
                Log.e("onResponse", headers.toString());
                return headers;
            }
        };
        u.add(request);


return view;
    }


}




