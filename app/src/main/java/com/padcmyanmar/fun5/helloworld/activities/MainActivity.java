package com.padcmyanmar.fun5.helloworld.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.padcmyanmar.fun5.helloworld.R;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btnLogin= findViewById(R.id.btn_login);
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk >= Build.VERSION_CODES.O) {
            btnLogin.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ripple));

        } else {
            //btnLogin.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ripple));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"User Tapped login button");

                EditText etUserPhone = findViewById(R.id.et_user_phone);
                String userPhone = etUserPhone.getText().toString();
                if(TextUtils.isEmpty(userPhone)){
                    etUserPhone.setError("Enter your phone here");
                    return;
                }

                EditText etUserPassword = findViewById(R.id.et_user_password);
                String userPassword = etUserPassword.getText().toString();
                if(TextUtils.isEmpty(userPassword)){
                    etUserPassword.setError("Enter your password");
                    return;
                }

                if(TextUtils.equals(userPhone,"09976359159")
                        && TextUtils.equals(userPassword,"abc")){

                    Snackbar.make(v,
                            "Login Success",
                            Snackbar.LENGTH_INDEFINITE).show();
                }else{
                    Toast.makeText(v.getContext(),
                            "Phone number or password is incorrect",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView tvRegister = findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Register",Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
