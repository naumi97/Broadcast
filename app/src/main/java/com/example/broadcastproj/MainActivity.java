package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String BROADCAST_ACTION = "";
    Button button;
    TextView text;
    private Receiver localListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button  = findViewById(R.id.button);
        text = findViewById(R.id.text);


    }


    @Override
    protected void onStart() {
        super.onStart();
        localListener = new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    public void onClick(View view){
        if (view.getId() == R.id.button){
            BackgroundServices.startAction(this.getApplicationContext());
        }
    }


    public class Receiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText = text.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived" + message;
            text.setText(currentText);
        }
    }
}