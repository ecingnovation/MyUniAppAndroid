package eci.cosw.ecingnovation.myuniapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eci.cosw.ecingnovation.myuniapp.R;
import eci.cosw.ecingnovation.myuniapp.network.APIClient;
import eci.cosw.ecingnovation.myuniapp.network.model.LoginWrapper;
import eci.cosw.ecingnovation.myuniapp.network.model.Token;
import eci.cosw.ecingnovation.myuniapp.network.services.LoginService;
import eci.cosw.ecingnovation.myuniapp.storage.Storage;
import eci.cosw.ecingnovation.myuniapp.ui.utils.StringUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        storage = new Storage( this );
    }

    /** Called when the user taps the Login button */
    public void onLoginClicked(View view) {
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        boolean validFields = checkFields(editTextEmail, editTextPassword);
        if (validFields) {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            handleLogin(email, password);
        }
    }

    private void handleLogin(String email, String password) {
        LoginService apiService = APIClient.getLoginService();
        Call<Token> tokenCall = apiService.attemptLogin(new LoginWrapper(email, password));
        // Enqueue makes an asynchronous request, which doesn't block ui thread, therefore no ExecutorService.execute needed
        tokenCall.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    Token token = response.body();
                    storage.saveToken(token);
                    loginSuccessfulStartIntent();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.login_failed_error, Toast.LENGTH_SHORT).show();
                    try {
                        //TODO Use actual logging x2
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                //TODO Use actual logging
                System.out.println("Callback Error: " + t.getLocalizedMessage());
                Toast.makeText(getApplicationContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginSuccessfulStartIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Returns true if editTextEmail is valid and editTextPassword is not empty
     * @param editTextEmail email to check
     * @param editTextPassword password to check
     * @return true if both are valid
     */
    private boolean checkFields(EditText editTextEmail, EditText editTextPassword) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!StringUtils.isValidEmail(email)){
            editTextEmail.setError(getString(R.string.invalid_email));
            return false;
        }
        else {
            editTextEmail.setError(null);
            if (!password.isEmpty()) {
                return true;
            } else {
                editTextPassword.setError(getString(R.string.password_empty_error));
                return false;
            }
        }
    }
}
