package com.example.testing_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    Button btn_one;
    SignInButton sign_button;
    ArrayList<BeanDemo> array ;
    BeanDemo beanDemo;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_one = (Button)findViewById(R.id.button_one);
        sign_button = (SignInButton)findViewById(R.id.sign_in_button);
        beanDemo = new BeanDemo();
        array = new ArrayList<BeanDemo>();
        array.add(new BeanDemo("name_one","age_1","add_1"));
        array.add(new BeanDemo("name_2","age_2","add_3"));
        array.add(new BeanDemo("name_3","age_3","add_3"));
        for (int i =0 ; i< array.size();i++){


            beanDemo.setName(array.get(i).getName());
            beanDemo.setAddress(array.get(i).getAddress());
            beanDemo.setAge(array.get(i).getAge());
        }
        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ArrayList<BeanDemo> object = new ArrayList<BeanDemo>();
                Intent intent = new Intent(MainActivity.this,Testing2activity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)array);
                intent.putExtra("BUNDLE",args);
                startActivity(intent);*/
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        Toast.makeText(getApplicationContext(),"Logout successfully",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
               startActivityForResult(intent,REQ_CODE);
            }
        });
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();



    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void handleRequest(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account =  result.getSignInAccount();
            String name = account.getDisplayName();
            String emailid = account.getEmail();
            String imageurl = account.getPhotoUrl().toString();

            Toast.makeText(getApplicationContext(),name+" ++ "+emailid+" ++ "+imageurl+" ++ ",Toast.LENGTH_LONG).show();
        }

    }
    private void showmeout(boolean isLogin){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE){
            GoogleSignInResult  result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleRequest(result);
        }
    }
}
