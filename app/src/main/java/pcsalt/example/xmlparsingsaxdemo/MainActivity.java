package pcsalt.example.xmlparsingsaxdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvPcsPost;
    ArrayList<PostValue> postValueArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPcsPost = (ListView) findViewById(R.id.lvPcsPost);
        lvPcsPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (postValueArrayList != null && postValueArrayList.size() > 0) {
                    Intent intentShowPost = new Intent(Intent.ACTION_VIEW, Uri.parse(postValueArrayList.get(position).getGuid()));
                    startActivity(intentShowPost);
                }
            }
        });
        new PostAsync().execute();
    }

    class PostAsync extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;
        XMLHelper helper;


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(MainActivity.this, "PCSalt", "Loading posts for PCSalt.com ...", true, false);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            helper = new XMLHelper();
            helper.get();
            postValueArrayList = helper.getPostsList();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            PostBaseAdapter postBaseAdapter = new PostBaseAdapter(MainActivity.this, postValueArrayList);
            lvPcsPost.setAdapter(postBaseAdapter);
            pd.dismiss();
        }

    }

}
