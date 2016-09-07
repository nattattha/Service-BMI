package com.ninvisut.natt.servicebmi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String urlLogo = "http://swiftcodingthai.com/natt/ImageNatt/fitmen_cook.png";
    private EditText userEditText, passwordEditText;
    private ImageView imageView;
    private String userString, passwordString;
    private static final String urlOffice = "http://www.office365thailand.com";

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


    //Create Inner Class
    private class SynUser extends AsyncTask<Void, Void, String> {

        private Context context;
        private String myUserString, myPasswordString;
        private String[] loginStrings;
        private static final String urlJSON = "http://swiftcodingthai.com/natt/get_user_natt.php";

        public SynUser(Context context, String myUserString, String myPasswordString) {
            this.context = context;
            this.myUserString = myUserString;
            this.myPasswordString = myPasswordString;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();



            } catch (Exception e) {
                Log.d("NattV2", "e doInBack ==> " + e.toString());
                return null;
            }
        }   //doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("NattV2", "JSON ==> " + s);

        }   //onPost

    }   //SynUser Class

    public void clickWebSite(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlOffice));
        startActivity(intent);

    }   //clickWeb

    public void clickSingIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,R.drawable.bird48, "Have space", "กรอกข้อมูลให้ครบ");

        } else {
            //No space

            SynUser synUser = new SynUser(this, userString, passwordString);
            synUser.execute();
        }

    }   //clickSighin

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }
}
