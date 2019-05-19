package eci.cosw.ecingnovation.myuniapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import eci.cosw.ecingnovation.myuniapp.R;
import eci.cosw.ecingnovation.myuniapp.network.APIClient;
import eci.cosw.ecingnovation.myuniapp.network.model.AppNew;
import eci.cosw.ecingnovation.myuniapp.network.services.NewsService;
import eci.cosw.ecingnovation.myuniapp.storage.Storage;
import eci.cosw.ecingnovation.myuniapp.ui.utils.StringUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostNewFragment extends Fragment implements View.OnClickListener {

    public PostNewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button_send_new);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AppNew appNew = createNewFromForm();
        Storage storage = new Storage(getContext());
        NewsService newsService = APIClient.getNewsService(storage.getToken());
        Call<ResponseBody> call = newsService.postNew(appNew);
        enqueueCall(call);

    }

    private void enqueueCall(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    System.out.println("Successfull POST");
                    Toast.makeText(getContext(), "Noticia Publicada Correctamente...", Toast.LENGTH_SHORT).show();
                }
                else {
                    System.out.println("Error Posting New");
                    Toast.makeText(getContext(), "Error al publicar la Noticia...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private AppNew createNewFromForm() {
        String titulo = ((EditText) getView().findViewById(R.id.edit_titulo)).getText().toString().trim();
        String editor = ((EditText) getView().findViewById(R.id.edit_editor)).getText().toString().trim();
        String date = ((EditText) getView().findViewById(R.id.edit_date)).getText().toString().trim();
        RadioGroup radioGroup = getView().findViewById(R.id.radio_group);
        String type = changeTypeForAPI(((RadioButton) getView().findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
        String email = ((EditText) getView().findViewById(R.id.edit_email)).getText().toString().trim();
        String content = ((EditText) getView().findViewById(R.id.edit_content)).getText().toString().trim();
        String image = ((EditText) getView().findViewById(R.id.edit_image)).getText().toString().trim();

        AppNew appNew = new AppNew(0, titulo, editor, type, StringUtils.getDateToMillis(date), email, content, image);
        return appNew;
    }

    private String changeTypeForAPI(String type) {
        if (type.equals("Informativa")) return "Info";
        else if (type.equals("Alerta")) return "Warning";
        else return "Normal";
    }
}
