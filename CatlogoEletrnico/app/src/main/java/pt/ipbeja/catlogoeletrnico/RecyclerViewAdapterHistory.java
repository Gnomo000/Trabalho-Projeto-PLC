package pt.ipbeja.catlogoeletrnico;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapterHistory extends RecyclerView.Adapter<RecyclerViewAdapterHistory.ViewHolder>{

    private List<Request> requests;
    private Context context;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapterHistory(Context context, List<Request> requests) {
        this.context = context;
        this.requests = requests;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerViewAdapterHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.history_simple_layout, parent, false);
        return new RecyclerViewAdapterHistory.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Request request = this.requests.get(position);
        Book book = AppDataBaseBook.getInstance(this.context).getBookDao().getBookByTitle(request.getTitle());
        Glide.with(this.context).load(book.getImage()).into(holder.getImageView());
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
                HistoryDetailsActivity.startActivity(RecyclerViewAdapterHistory.this.context, (int) request.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.requests.size();
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
