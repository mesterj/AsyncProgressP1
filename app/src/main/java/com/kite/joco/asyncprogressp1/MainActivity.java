package com.kite.joco.asyncprogressp1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kite.joco.asyncprogressp1.db.Partner;
import com.kite.joco.asyncprogressp1.db.Sorszamok;
import com.kite.joco.asyncprogressp1.rest.ServiceGen;
import com.orm.SugarRecord;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    Handler asyncHandler = new Handler();
    ProgressBar pb;

    EditText etTosz, etSorszam;

    ProgressDialog pbDialog;

    TextView tvProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        etTosz = (EditText) findViewById(R.id.etTosz);
        etSorszam = (EditText) findViewById(R.id.etSsz);
        tvProgress = (TextView) findViewById(R.id.tvProgress);
        tvProgress.setText("0/83000");
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setProgress(0);
        pb.setIndeterminate(false);
        pb.setMax(83000);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartNormal:
                new ProgAsyncTask().execute();
                break;
            case R.id.btnStartDialog:
                pbDialog = new ProgressDialog(v.getContext());
                pbDialog.setCancelable(false);
                pbDialog.setMessage("Ps downloading ...");
                pbDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pbDialog.setProgress(0);
                pbDialog.setMax(83000);
                pbDialog.show();
                new DialogAsyncTask().execute();
                break;
            case R.id.btnPost:
                sorszampost();
                break;
            default:
                break;
        }
    }

    private void sorszampost() {
        Sorszamok s = new Sorszamok();
        s.setTosz(etTosz.getText().toString());
        s.setSorszam(etSorszam.getText().toString());
        s.setNyomtkod("KLAP201516PROBA");
        //s.setId(6l);

        ServiceGen.get().sendujSorszam(s, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.i("Nyomtavany","sikeresen mentve a sorszám");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Nyomtatvany","ajjajjj");
                error.printStackTrace();
            }
        });

    }

    public void handleProgress(int p) {
        tvProgress.setText(p + "/83000");
        pb.setProgress(p);
    }

    public void handleDialogProgress(int p){
        pbDialog.setProgress(p);
    }


    class ProgAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            Log.i("PREEXECUTE", "STARTED");
            //  setProgressBarIndeterminate(false);
            //  setProgressBarVisibility(true);
        }

        @Override
        protected String doInBackground(String... params) {
            Log.i("DOINBACKGROUND", "STARTED");
            final Thread t = new Thread() {
                @Override
                public void run() {
                    int jumptime = 0;
                    int totalProgressTime = 360000;
                    while (jumptime < totalProgressTime) {
                        try {
                            sleep(434);
                            jumptime += 100;
                            publishProgress(jumptime);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            };

            t.start();

            List<Partner> partners = ServiceGen.get().getSyncListOfPartner();

            SugarRecord.saveInTx(partners);

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //  Log.i("PROGRESSUPDATE", "CALLED");
            //setProgress(values[0]);
            handleProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String aVoid) {
            Log.i("POSTEXECUTE", "STARTED");
            //setProgressBarVisibility(false);
        }
    }

    class DialogAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            Thread dt = new Thread() {
                @Override
                public void run() {
                    int jumptime = 0;
                    int totalProgressTime = 360000;
                    while (jumptime < totalProgressTime) {
                        try {
                            sleep(434);
                            jumptime += 100;
                            publishProgress(jumptime);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            };
            dt.start();
            List<Partner> partners = ServiceGen.get().getSyncListOfPartner();

            SugarRecord.saveInTx(partners);

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            handleDialogProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            pbDialog.dismiss();
        }
    }
}

