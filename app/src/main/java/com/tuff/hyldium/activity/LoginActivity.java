package com.tuff.hyldium.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tuff.hyldium.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LogActivity";
    private Button btn_login;
    private EditText textEmailAddress;
    private EditText textPassword;
    private MessageDigest digest;
    private String encryptedPassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        //no action bar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        this.btn_login = (Button) findViewById(R.id.btn_login);
        this.textEmailAddress = (EditText) findViewById(R.id.input_user_name);

        this.textPassword = (EditText) findViewById(R.id.input_password);
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    public void login() {
        Log.d(TAG, "Login");

        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(textPassword.getText().toString().getBytes());
        byte[] hash = digest.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }

        encryptedPassword = sb.toString();


        if (!validate()) {
            onLoginFailed();
            return;
        }

        btn_login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("connexion...");
        progressDialog.show();

        String email = textEmailAddress.getText().toString();
        String password = textPassword.getText().toString();


        onLoginSuccess();
        // onLoginFailed();
        progressDialog.dismiss();

    }


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        btn_login.setEnabled(true);
        finish();
        Intent intent = new Intent(this, UserOrderActivity.class);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn_login.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String userName = textEmailAddress.getText().toString();
        String password = textPassword.getText().toString();

        if (userName.isEmpty() || userName.length() < 4) {
            textEmailAddress.setError("entrez un nom d'utilisateur");
            valid = false;
        } else {
            textEmailAddress.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 18) {
            textPassword.setError("mot de passe de 4 à 18 charactères");
            valid = false;
        } else {
            textPassword.setError(null);
        }

        return valid;
    }

}
