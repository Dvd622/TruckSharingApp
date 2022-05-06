package com.example.trucksharingapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class SignupActivity extends AppCompatActivity {

    Button addImageButton, createAccountButton;
    ImageButton addImageImageButton;
    EditText fullNameEditText, usernameEditText, passwordEditText, confirmPasswordEditText, phoneNumberEditText;
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        addImageButton = findViewById(R.id.signupAddImageButton);
        createAccountButton = findViewById(R.id.signupCreateAccountButton);
        addImageImageButton = findViewById(R.id.addImageImageButton);
        fullNameEditText = findViewById(R.id.signupFullNameEditText);
        usernameEditText = findViewById(R.id.signupUsernameEditText);
        passwordEditText = findViewById(R.id.signupPasswordEditText);
        confirmPasswordEditText = findViewById(R.id.signupConfirmPasswordEditText);
        phoneNumberEditText = findViewById(R.id.signupPhoneNumberEditText);
        selectedImage = Uri.parse("src/main/res/drawable/resource_default.jpg");

        DatabaseHelper db = new DatabaseHelper(this);

        addImageButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 3);
        });

        addImageImageButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 3);
        });

        createAccountButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();
            String fullName = fullNameEditText.getText().toString();
            String phoneNumber = phoneNumberEditText.getText().toString();
            Bitmap accountImageBitmap;

            try {
                accountImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
                accountImageBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.add_image);
            }

            if (fullNameEditText.getText().toString().equals("") || usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("") || phoneNumberEditText.getText().toString().equals("")) {
                Toast.makeText(this, "Fill all details to proceed", Toast.LENGTH_SHORT).show();
            } else if (password.equals(confirmPassword)) {
                User user = new User(username, password, accountImageBitmap, fullName, phoneNumber);
                long result = db.insertUser(user);
                if (result > -1) {
                    Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(this, "Signup failed, error: " + result, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            addImageImageButton.setImageURI(selectedImage);
        }
    }
}