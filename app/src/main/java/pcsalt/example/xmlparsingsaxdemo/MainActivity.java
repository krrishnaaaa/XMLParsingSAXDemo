package pcsalt.example.xmlparsingsaxdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

    ListView lvPcsPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPcsPost = (ListView) findViewById(R.id.lvPcsPost);
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
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            PostBaseAdapter postBaseAdapter = new PostBaseAdapter(MainActivity.this, helper.posts);
            lvPcsPost.setAdapter(postBaseAdapter);
            pd.dismiss();
        }

    }

}
