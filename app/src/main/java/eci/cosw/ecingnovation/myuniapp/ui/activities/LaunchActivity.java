package eci.cosw.ecingnovation.myuniapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import eci.cosw.ecingnovation.myuniapp.storage.Storage;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Storage storage = new Storage( this );
        if ( storage.containsToken() ) {
            startActivity( new Intent( this, MainActivity.class ) );
        }
        else {
            startActivity( new Intent( this, LoginActivity.class ) );
        }
        finish();
    }
}