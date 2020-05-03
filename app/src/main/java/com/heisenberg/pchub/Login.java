package com.heisenberg.pchub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Login extends AppCompatActivity {

    EditText Pass;
    Button lbtn;
    String str = null,pass="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Pass = findViewById(R.id.password);
        lbtn = findViewById(R.id.lButton);
        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = Pass.getText().toString().trim();
                pass = password;

                if(TextUtils.isEmpty(password)){
                    Pass.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    Pass.setError("Password Must be >= 6 Characters");
                    return;
                }
                getWebsite();
                if (str != null) {
                    Intent intent = new Intent(Login.this ,MainActivity.class);
                    intent.putExtra("Name",str);
                    intent.putExtra("Code",pass);
                    startActivity(intent);
                }

            }
        });

    }

    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect("https://heisenberg3562.github.io/MyFirstHtmlWebpage/myHtml.html").get();

                    String id = pass;
                    Elements links = doc.select("p[id="+id+"]");



                    for (Element link : links) {
                        builder.append(link.text());
                    }
                } catch (IOException e) {
                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    //builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        str = builder.toString();
                    }
                });
            }
        }).start();
    }
}
