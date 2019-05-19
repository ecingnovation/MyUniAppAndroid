package eci.cosw.ecingnovation.myuniapp.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private NewsAdapter newsAdapter;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.recyclerView);
        configureRecyclerView();
        getNewsFromAPI();
    }

    private void configureRecyclerView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter();
        recyclerView.setAdapter(newsAdapter);
    }

    private void getNewsFromAPI() {
        Storage storage = new Storage(getContext());
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
}
