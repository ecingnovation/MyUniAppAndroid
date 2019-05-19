package eci.cosw.ecingnovation.myuniapp.ui.adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eci.cosw.ecingnovation.myuniapp.R;
import eci.cosw.ecingnovation.myuniapp.network.model.AppNew;
import eci.cosw.ecingnovation.myuniapp.ui.utils.StringUtils;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<AppNew> newsList = null;

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.appnew_row, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        AppNew appNew = newsList.get(position);
        holder.title.setText(appNew.title);
        holder.publisher.setText("Por " + appNew.publisher);
        //holder.type.setText(appNew.type);
        holder.date.setText(StringUtils.getFormattedDate(String.valueOf(appNew.date)));
        holder.email.setText(appNew.email);
        holder.content.setText(appNew.content);
        holder.typeIcon.setImageResource(selectIcon(appNew));
        if (!appNew.imageURL.equals("")) Picasso.get().load(appNew.imageURL).into(holder.image);
    }

    private int selectIcon(AppNew appNew) {
        if (appNew.type.equals("Normal")) return R.drawable.ic_check_circle_black_24dp;
        else if (appNew.type.equals("Warning")) return R.drawable.ic_warning_black_24dp;
        else if (appNew.type.equals("Info")) return R.drawable.ic_info_black_24dp;
        else return R.drawable.ic_check_circle_black_24dp;
    }

    public void updateNewsList(List<AppNew> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView publisher;
        public TextView date;
        public TextView email;
        public TextView content;
        public ImageView typeIcon;
        public ImageView image;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.new_title);
            date = itemView.findViewById(R.id.new_date);
            publisher = itemView.findViewById(R.id.new_publisher);
            email = itemView.findViewById(R.id.new_email);
            content = itemView.findViewById(R.id.new_content);
            typeIcon = itemView.findViewById(R.id.new_icontype);
            image = itemView.findViewById(R.id.new_image);
        }
    }

}
