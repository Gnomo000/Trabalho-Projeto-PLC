package pt.ipbeja.catlogoeletrnico.models;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pt.ipbeja.catlogoeletrnico.R;
import pt.ipbeja.catlogoeletrnico.view.HistoryDetailsActivity;
import pt.ipbeja.catlogoeletrnico.viewModels.AdapterHistoryViewModel;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder>{

    private List<Request> requests;
    private Context context;
    private LayoutInflater layoutInflater;
    private AdapterHistoryViewModel adapterHistoryViewModel;

    public AdapterHistory(Context context) {
        this.context = context;
        this.requests = new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(context);
        this.adapterHistoryViewModel = new ViewModelProvider((ViewModelStoreOwner) this.context).get(AdapterHistoryViewModel.class);
    }

    @NonNull
    @Override
    public AdapterHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.history_simple_layout, parent, false);
        return new AdapterHistory.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Request request = this.requests.get(position);

        adapterHistoryViewModel.getBookByTitle(request.getTitle()).observe((LifecycleOwner) this.context, new Observer<Book>() {
            @Override
            public void onChanged(Book book) {
                Glide.with(AdapterHistory.this.context).load(book.getImage()).into(holder.getImageView());
                holder.getTextView().setText(request.getTitle());


                if (request.getStatus().equals("Por Levantar")) {
                    holder.getViewRectangle().setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));
                }else if (request.getStatus().equals("Por Entregar")){
                    holder.getViewRectangle().setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                }else if (request.getStatus().equals("Atrazado")){
                    holder.getViewRectangle().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                }else if (request.getStatus().equals("Entrague")){
                    holder.getViewRectangle().setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                }else {
                    Log.i("POOP","|"+request.getStatus()+"|");
                }
                holder.getParentLayout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HistoryDetailsActivity.startActivity(AdapterHistory.this.context, request.getId());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.requests.size();
    }

    public void update(List<Request> newList) {
        if (newList != null) {
            this.requests = newList;
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View parentLayout;
        private final ImageView imageView;
        private final TextView textView;
        private final View viewRectangle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageViewSimple);
            this.textView = itemView.findViewById(R.id.textViewSimple);
            this.parentLayout = itemView.findViewById(R.id.parentLayout);
            this.viewRectangle = itemView.findViewById(R.id.myRectangleViewHs);
        }

        public View getViewRectangle() {
            return this.viewRectangle;
        }

        public View getParentLayout() {
            return this.parentLayout;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }
    }

}
