package pt.ipbeja.catlogoeletrnico.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pt.ipbeja.catlogoeletrnico.R;
import pt.ipbeja.catlogoeletrnico.view.BookDetailsActivity;

public class AdapterBook extends RecyclerView.Adapter<AdapterBook.ViewHolder> {

    private List<Book> item;
    private Context context;
    private LayoutInflater layoutInflater;

    public AdapterBook(Context context) {
        this.context = context;
        this.item = new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.book_simple_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = this.item.get(position);



        if (book.getQuantity() == 0) {
            holder.getNoStckView().setVisibility(View.VISIBLE);
            holder.getNoStock().setVisibility(View.VISIBLE);
        }else {
            holder.getNoStckView().setVisibility(View.GONE);
            holder.getNoStock().setVisibility(View.GONE);
        }

        Glide.with(this.context).load(book.getImage()).into(holder.getImageView());
        holder.getTextView().setText(book.getTitle());
        holder.getParentLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDetailsActivity.startActivity(AdapterBook.this.context, (int) book.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.item.size();
    }

    public void update(List<Book> newList) {
        if (newList != null) {
            this.item = newList;
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View parentLayout;
        private final ImageView imageView;
        private final TextView textView;
        private final TextView noStock;
        private final View noStckView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageViewSimple);
            this.textView = itemView.findViewById(R.id.textViewSimple);
            this.parentLayout = itemView.findViewById(R.id.parentLayout);
            this.noStock = itemView.findViewById(R.id.noStockText);
            this.noStckView = itemView.findViewById(R.id.myRectangleViewHs);
        }

        public TextView getNoStock() {
            return noStock;
        }

        public View getNoStckView() {
            return noStckView;
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
