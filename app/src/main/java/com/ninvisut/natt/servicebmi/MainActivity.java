package com.ninvisut.natt.servicebmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String urlLogo = "http://swiftcodingthai.com/natt/ImageNatt/fitmen_cook.png";
    private EditText userEditText, passwordEditText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);
        imageView = (ImageView) findViewById(R.id.imageView);


        //Load Image From Server
        Picasso.with(this).load(urlLogo).resize(180,180).into(imageView);


    }   //onCreate

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }
}
