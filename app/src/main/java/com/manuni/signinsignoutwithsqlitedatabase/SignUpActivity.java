package com.manuni.signinsignoutwithsqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.manuni.signinsignoutwithsqlitedatabase.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySignUpBinding binding;
    UserData data;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        data = new UserData();

        binding.signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = binding.nameET.getText().toString().trim();
        String email = binding.emailET.getText().toString().trim();
        String username = binding.username.getText().toString().trim();
        String password = binding.password.getText().toString().trim();

        data.setName(name);
        data.setEmail(email);
        data.setUsername(username);
        data.setPassword(password);

        if (view.getId() == R.id.signup){
            long rowId = databaseHelper.insertData(data);
            if (rowId > 0){
                Toast.makeText(this, "Row "+rowId+ " is successfully inserted.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Failed to perform.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}