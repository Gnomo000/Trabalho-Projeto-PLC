package pt.ipbeja.catlogoeletrnico;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapterBook extends RecyclerView.Adapter<RecyclerViewAdapterBook.ViewHolder> {

    private List<Book> item;
    private Context context;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapterBook(Context context, List<Book> item) {
        this.context = context;
        this.item = item;
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
        Glide.with(this.context).load(book.getImage()).into(holder.getImageView());
        holder.getTextView().setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        return this.item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View parentLayout;
        private final ImageView imageView;
        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageViewSimple);
            this.textView = itemView.findViewById(R.id.textViewSimple);
            this.parentLayout = itemView.findViewById(R.id.parentLayout);
        }

        public View getParentLayout() {
            return parentLayout;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
