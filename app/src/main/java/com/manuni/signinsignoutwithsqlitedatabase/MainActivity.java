package com.manuni.signinsignoutwithsqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.manuni.signinsignoutwithsqlitedatabase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signInBtn.setOnClickListener(this);
        binding.signUpBtn.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(this);
       SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {
        String name = binding.userNameET.getText().toString().trim();
        String password = binding.passwordET.getText().toString().trim();

        if (view.getId() == R.id.signInBtn){
           Boolean matched = databaseHelper.findPassword(name,password);
           if (matched==true){
               startActivity(new Intent(MainActivity.this,WelcomeActivity.class));
               finishAffinity();
           }else {
               Toast.makeText(this, "Nothing to do...try again!", Toast.LENGTH_SHORT).show();
           }

        }else if (view.getId()==R.id.signUpBtn){
            startActivity(new Intent(MainActivity.this,SignUpActivity.class));
        }

    }
}