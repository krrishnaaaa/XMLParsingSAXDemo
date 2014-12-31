package pcsalt.example.xmlparsingsaxdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResponse = (TextView) findViewById(R.id.tvResponse);
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
            StringBuilder builder = new StringBuilder();
            for (PostValue post : helper.posts) {
                builder.append("\nPost: ").append(post.getTitle());
                builder.append("\nPublish Date: ").append(post.getDate());
                builder.append("\nGuid: ").append(post.getGuid());
                builder.append("\n");
            }
            tvResponse.setText(builder.toString());
            pd.dismiss();
        }

    }

}
