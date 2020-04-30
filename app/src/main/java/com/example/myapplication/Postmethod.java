package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Postmethod extends AppCompatActivity {
    TextView textView;
  List<AsyncTask> list=new ArrayList<>();
    public static final String url="https://tabeshma.000webhostapp.com/mysites/get_cat.php";
    public static final String URI_SHOW_PARAMS = "https://tabeshma.000webhostapp.com/mysites/showparams.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postmethod);
        textView=findViewById(R.id.textView3);
        textView.setMovementMethod(new ScrollingMovementMethod());
        new postmethod().execute(url);
    }



    public class postmethod extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            list.add(this);
            textView.append("start");


        }



        @Override
        protected String doInBackground(String... strings) {


            return getdata(strings[0]);
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }

        @Override
        protected void onPostExecute(String s) {
            textView.append(s+"\n ");

        }
    }

    public String getdata(String url)
    {
        String content="";
        HttpClient httpClient=new DefaultHttpClient();
        HttpGet httpGet=new HttpGet(url);
        try {
            HttpResponse httpResponse=httpClient.execute(httpGet);
            content=inputstream(httpResponse.getEntity().getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
    public String inputstream(InputStream inputStream)
    {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder =new StringBuilder();
        String line="";
        try{
            while ((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }
            return  stringBuilder.toString().trim();

        }catch (Exception e)
        {

        }

        return null;

    }

    public String getdatabyurl(String uri)
    {
        String content="";

        try {
            URL url=new URL(uri);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            content=inputstream(httpURLConnection.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return content;

    }





}
