    package com.example.android.farrel_1202150033_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

    public class ListNamaActivity extends AppCompatActivity {

        private ListView mListNama;
        private Button mStartAsyncTask;
        private ProgressBar mProgressBar;
        private String[] namaArray = {
                "Asep", "Uju", "Ned", "Jon", "Jin", "Jun", "Omo", "Ojo", "Rafi"
                , "Adit", "Maung Bandung", "Siaomi"};
        private AddItemToListView mAddItemToListView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_nama);

            mListNama = findViewById(R.id.listNama);
            mProgressBar = findViewById(R.id.progressBar);
            mStartAsyncTask = findViewById(R.id.Mulai);

            mListNama.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

            mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //process adapter with asyncTask
                    mAddItemToListView = new AddItemToListView();
                    mAddItemToListView.execute();
                }
            });
        }

        public class AddItemToListView extends AsyncTask<Void, String, Void> {

            private ArrayAdapter<String> mAdapter;
            private int counter = 1;
            ProgressDialog mProgressDialog = new ProgressDialog(ListNamaActivity.this);

            @Override
            protected void onPreExecute() {
                mAdapter = (ArrayAdapter<String>) mListNama.getAdapter(); //casting suggestion
                //isi progress dialog
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setTitle("Loading Data");
                mProgressDialog.setCancelable(false);
                mProgressDialog.setMessage("Please wait....");
                mProgressDialog.setProgress(0);
                mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAddItemToListView.cancel(true);
                        mProgressBar.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                });
                mProgressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                for (String item : namaArray) {
                    publishProgress(item);
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (isCancelled()) {
                        mAddItemToListView.cancel(true);
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                mAdapter.add(values[0]);

                Integer current_status = (int) ((counter / (float) namaArray.length) * 100);
                mProgressBar.setProgress(current_status);

                //set progress only working for horizontal loading
                mProgressDialog.setProgress(current_status);

                //set message will not working when using horizontal loading
                mProgressDialog.setMessage(String.valueOf(current_status + "%"));
                counter++;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                //hide progreebar
                mProgressBar.setVisibility(View.GONE);

                //remove progress dialog
                mProgressDialog.dismiss();
                mListNama.setVisibility(View.VISIBLE);
            }
        }
    }

