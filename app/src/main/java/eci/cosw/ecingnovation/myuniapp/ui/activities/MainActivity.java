package eci.cosw.ecingnovation.myuniapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import eci.cosw.ecingnovation.myuniapp.R;
import eci.cosw.ecingnovation.myuniapp.network.APIClient;
import eci.cosw.ecingnovation.myuniapp.network.model.AppNew;
import eci.cosw.ecingnovation.myuniapp.network.model.User;
import eci.cosw.ecingnovation.myuniapp.network.services.AccountsService;
import eci.cosw.ecingnovation.myuniapp.network.services.NewsService;
import eci.cosw.ecingnovation.myuniapp.storage.Storage;
import eci.cosw.ecingnovation.myuniapp.ui.adapters.NewsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        configureRecyclerView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        getBasicUserInformation();
        getNewsFromAPI();

    }

    private void configureRecyclerView() {
        recyclerView.setHasFixedSize( true );
        layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter();
        recyclerView.setAdapter(newsAdapter);
    }

    private void getNewsFromAPI() {
        Storage storage = new Storage(this);
        System.out.println(storage.getToken());
        NewsService newsService = APIClient.getNewsService(storage.getToken());
        Call<List<AppNew>> call = newsService.getAllNews();
        call.enqueue(new Callback<List<AppNew>>() {
            @Override
            public void onResponse(Call<List<AppNew>> call, Response<List<AppNew>> response) {
                ArrayList<AppNew> result = (ArrayList<AppNew>) response.body();
                newsAdapter.updateNewsList(result);
                if (!response.isSuccessful()) {
                    System.out.println("[ERROR] There was a an error at getNewsFromAPI -> onResponse... ");
                }
            }

            @Override
            public void onFailure(Call<List<AppNew>> call, Throwable t) {
                System.out.println("onFailure: " + t.getMessage());
            }
        });
    }

    private void getBasicUserInformation() {
        Storage storage = new Storage(this);
        String email = storage.getEmail();
        System.out.println("[EXPECTING A LOT] " + email);
        AccountsService accountsService = APIClient.getAccountsService(storage.getToken());
        final Call<User> userCall = accountsService.getUserByEmail(email);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    TextView viewEmail = findViewById(R.id.user_email);
                    TextView viewName = findViewById(R.id.user_name);
                    viewEmail.setText(user.getEmail());
                    viewName.setText(user.getName() + " " + user.getLastName());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("Callback Error: " + t.getLocalizedMessage());
                Toast.makeText(getApplicationContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_logout) {
            handleLogout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleLogout() {
        Storage storage = new Storage(this);
        storage.clearToken();
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mapa) {

        } else if (id == R.id.nav_noticias) {

        } else if (id == R.id.nav_kioskos) {

        } else if (id == R.id.nav_sitio) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
