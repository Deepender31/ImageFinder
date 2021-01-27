package com.example.imagefinder;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText s_Text;
Button s_Button;
ImageView image1;
String src,url;
ArrayList<String> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        s_Button=findViewById(R.id.search_button);
        s_Text=findViewById(R.id.Edit_T1);
        url=getString(R.string.amazon)+s_Text;
        image1=findViewById(R.id.image1);
        s_Button.setOnClickListener(v -> {
          Content content =new Content();
          content.execute();
          view();

        });
    }
    private class Content extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document doc =(Document)Jsoup.connect(url).get();
                Elements img =(Elements) doc.getElementsByTagName("img");
                for(Element el: img)
                {
                    src=el.absUrl("src");
                    list.add(src);
                }
                Log.d("images links", list.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void view(){
        final Target<GlideDrawable> into = Glide.with(this).load(list.get(3)).into(image1);

    }
}