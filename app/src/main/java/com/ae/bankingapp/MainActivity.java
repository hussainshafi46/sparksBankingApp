package com.ae.bankingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ae.bankingapp.Models.User;
import com.ae.bankingapp.Utils.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Bank.MainActivity";
    private Button viewAll;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.hide();

        init();
        generateDummy();
    }

    private void init() {
        dbHelper = new DataBaseHelper(this);
        viewAll = findViewById(R.id.allUsers);
        viewAll.setOnClickListener(view -> {
            Intent users = new Intent(MainActivity.this, AllUsers.class);
            startActivity(users);
        });
        viewAll.setEnabled(false);
    }

    private void generateDummy(){
        User[] userList = {
                new User(1001,"Alex", "alex123@gmail.com", 5000),
                new User(1002,"Blake", "blake123@gmail.com", 5000),
                new User(1003,"Clark", "clark123@gmail.com", 5000),
                new User(1004,"Danny", "danny123@gmail.com", 5000),
                new User(1005,"Emily", "emily123@gmail.com", 5000),
                new User(1006,"Ferguson", "ferguson123@gmail.com", 5000),
                new User(1007,"George", "george123@gmail.com", 5000),
                new User(1008,"Harry", "harry123@gmail.com", 5000),
                new User(1009,"Ines", "ines123@gmail.com", 5000),
                new User(10010,"Julia", "julia123@gmail.com", 5000)
        };
        new GenerateDummyData().execute(userList);
    }


    @SuppressLint("StaticFieldLeak")
    private class GenerateDummyData extends AsyncTask<User, Void, Void>{

        @Override
        protected Void doInBackground(User... users) {
            for(User user: users) {
                dbHelper.insertToUser(user);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            viewAll.setEnabled(true);
        }
    }
}