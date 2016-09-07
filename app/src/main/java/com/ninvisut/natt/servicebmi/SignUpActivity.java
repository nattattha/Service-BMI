package com.ninvisut.natt.servicebmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, userEditText,
            passwordEditText, rePasswordEditText;
    private String nameString, userString,
            passwordString, rePasswordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //bind Widget
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        rePasswordEditText = (EditText) findViewById(R.id.editText4);

    }   //onCreate

    public void clickSignUpSign(View view) {

        //Get Value Form Edit Text

        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        rePasswordString = rePasswordEditText.getText().toString().trim();

        //Check Space
        if (nameString.equals("") ||
                userString.equals("") ||
                passwordString.equals("") ||
                rePasswordString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, R.drawable.doremon48,
                    "มีช่องว่าง", "กรุณากรอกให้ครบ");
        }

    }   //clickSignUp
}
