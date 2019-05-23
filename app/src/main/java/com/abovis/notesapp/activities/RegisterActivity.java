package com.abovis.notesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abovis.notesapp.R;
import com.abovis.notesapp.repositories.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Registro de usuario");

        usernameInput = findViewById(R.id.username_input);
        fullnameInput = findViewById(R.id.fullname_input);
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegister();
            }
        });

    }

    private void callRegister(){

        try{
            String username = usernameInput.getText().toString();
            String fullname = fullnameInput.getText().toString();
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (username.isEmpty() || fullname.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            UserRepository.create(username,fullname, email, password);

            Toast.makeText(this, "Registro satisfactorio", Toast.LENGTH_SHORT).show();

            finish();

        }catch(Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
