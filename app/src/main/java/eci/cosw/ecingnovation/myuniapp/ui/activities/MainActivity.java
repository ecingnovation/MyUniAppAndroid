package eci.cosw.ecingnovation.myuniapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

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
