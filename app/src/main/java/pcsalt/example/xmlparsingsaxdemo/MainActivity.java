package pcsalt.example.xmlparsingsaxdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvPcsPost;
    ArrayList<BookDetails> bookDetailsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPcsPost = (ListView) findViewById(R.id.lvPcsPost);
        lvPcsPost.setOnItemClickListener((parent, view, position, id) -> {
            if (bookDetailsArrayList != null && bookDetailsArrayList.size() > 0) {
                BookDetails details = bookDetailsArrayList.get(position);
                Toast.makeText(this, "Clicked : " + details.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        new PostAsync().execute();
    }

    class PostAsync extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;
        XMLHelper helper;


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(MainActivity.this, "PCSalt", "Loading book details from sample XML...", true, false);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            helper = new XMLHelper();
            helper.get();
            bookDetailsArrayList = helper.getPostsList();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            PostBaseAdapter postBaseAdapter = new PostBaseAdapter(bookDetailsArrayList);
            lvPcsPost.setAdapter(postBaseAdapter);
            pd.dismiss();
        }

    }

}
