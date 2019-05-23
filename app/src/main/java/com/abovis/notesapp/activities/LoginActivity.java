package com.abovis.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abovis.notesapp.R;
import com.abovis.notesapp.models.User;
import com.abovis.notesapp.repositories.UserRepository;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Inicia Sesion");

        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);

        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLogin();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegister();
            }
        });

        verifySession();
    }

    private void callLogin(){
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = UserRepository.login(username,password);

        if(user == null) {
            Toast.makeText(this, "Usuario y/o contaseña inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean success = sp.edit()
                .putString("username", username)
                .putLong("id", user.getId())
                .putBoolean("islogged", true)
                .commit();
        goMain();
    }

    private void showRegister(){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void verifySession() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if(sp.getBoolean("islogged", false)){
            // Go to main
            goMain();
        }
    }

    private void goMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
