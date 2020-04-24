package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
   EditText editText;
    String data;
    public static final String url="https://tabeshma.000webhostapp.com/mysites/get_cat.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        Thread thread=new Thread(new Runnable() {

            Handler handler =new Handler()
            {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    String content=(String) msg.getData().get("content");
                    editText.setText(content);
                }
            };

            @Override
            public void run() {
                String content=getdata();
                android.os.Message message=new Message();
                Bundle bundle=new Bundle();
                bundle.putString("content",content);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
        thread.start();



    }

    public String getdata()
    {
        String content="";
        HttpClient client =new DefaultHttpClient();
        HttpGet getmethod=new HttpGet(url);
        try
        {
            HttpResponse response=client.execute(getmethod);
            content=inputStreamToString(response.getEntity().getContent());

        }
        catch(Exception e)
        {

        }
        return  content;


    }
    public static  String inputStreamToString(InputStream stream)
    {
        BufferedReader reader =new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb=new StringBuilder();
        String Line="";
        try
        {
            while ((Line=reader.readLine())!=null)
            {
                sb.append(Line+"\n");


            }
            return sb.toString().trim();

        }catch (Exception e)
        {
            Log.i("kirrrrrrrrrrrrrr",e.getMessage());
        }



        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuItem menupost= menu.add("post");
        menupost.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(getApplicationContext(),Postmethod.class));
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
