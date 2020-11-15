package pcsalt.example.xmlparsingsaxdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Krrishnaaaa on Jan 01, 2015
 */
public class PostBaseAdapter extends BaseAdapter {

    private final ArrayList<BookDetails> bookDetailsArrayList;

    public PostBaseAdapter(ArrayList<BookDetails> bookDetailsArrayList) {
        this.bookDetailsArrayList = bookDetailsArrayList;
    }

    @Override
    public int getCount() {
        return bookDetailsArrayList.size();
    }

    @Override
    public BookDetails getItem(int position) {
        return bookDetailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BookDetails bookDetails = getItem(position);

        viewHolder.setData(bookDetails);

        return convertView;
    }

    private class ViewHolder {
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvGenre;
        TextView tvDescription;
        TextView tvPublishDate;

        public ViewHolder(View item) {
            tvTitle = (TextView) item.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) item.findViewById(R.id.tvAuthor);
            tvGenre = (TextView) item.findViewById(R.id.tvGenre);
            tvDescription = (TextView) item.findViewById(R.id.tvDescription);
            tvPublishDate = (TextView) item.findViewById(R.id.tvPublishDate);
        }

        public void setData(BookDetails details) {
            tvTitle.setText(details.getTitle());
            tvAuthor.setText(details.getAuthor());
            tvGenre.setText(details.getGenre());
            tvDescription.setText(details.getDescription());
            tvPublishDate.setText(details.getPublishDate());
        }
    }
}
