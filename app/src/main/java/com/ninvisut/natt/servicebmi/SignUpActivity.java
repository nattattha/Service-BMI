package com.ninvisut.natt.servicebmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, userEditText,
            passwordEditText, rePasswordEditText;
    private String nameString, userString,
            passwordString, rePasswordString;
    private static final String urlPHP = "http://swiftcodingthai.com/natt/add_user_natt.php";


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
        } else if (!(passwordString.equals(rePasswordString))) {
            //Password False
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, R.drawable.nobita48,
                    "Password ไม่ตรงกัน", "กรุณากรอกรหัสให้ตรงกัน");
        } else {
            //Password OK
            uploadValueToServer();
        }

    }   //clickSignUp

    private void uploadValueToServer() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("User", userString)
                .add("Password", passwordString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("NattV1", "e ==> " + e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("NattV1", "Result ==> " + response.body().string());
                finish();

            }
        });






    }   //uploadValueToServer
}
