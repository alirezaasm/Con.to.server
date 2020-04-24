package com.example.myapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Catparser {
    String Log_Lable="Matimassegecat";
    public List<HashMap<String,Object>> parser (String json)
    {
        List<HashMap<String,Object>> all_cats=new ArrayList<>();
        JSONObject jsonObject;
        try
        {
            jsonObject=new JSONObject(json);
            JSONArray jArr =jsonObject.getJSONArray("cat");
            for(int i=0; i<jArr.length();i++)
            {
                HashMap<String,Object> cat=new HashMap<>();
                String id="";
                String name="";

                JSONObject temp=jArr.getJSONObject(i);
                id=temp.getString("id");
                name=temp.getString("name");

                cat.put("id",id);
                cat.put("name",name);

                all_cats.add(cat);


            }



        } catch (JSONException e) {
            Log.i(Log_Lable,"error inn catparser in parser()->"+e.toString());

        }
        return (all_cats);
    }

}
