package com.kite.joco.asyncprogressp1;

import android.os.AsyncTask;
import android.os.Handler;

/**
 * Created by Joco on 2015.12.25..
 */
public class MyAsyncTask extends AsyncTask<String,Integer,String> {

    Handler mainUIHandler;

    int progressBarStatus;

    public MyAsyncTask(Handler mainUIHandler){
        this.mainUIHandler = mainUIHandler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBarStatus = 0;
    }

    @Override
    protected String doInBackground(String... params) {

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {

                    // process some tasks
                    progressBarStatus = doSomeTasks();

                    // your computer is too fast, sleep 1 second
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    mainUIHandler.post(new Runnable() {
                        public void run() {
//                            mainUIHandler.progressBar.setProgress(progressBarStatus);
                        }
                    });
                }

                // ok, file is downloaded,
                if (progressBarStatus >= 100) {

                    // sleep 2 seconds, so that you can see the 100%
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // close the progress bar dialog
//                    progressBar.dismiss();
                }
            }
        }).start();
        return null;
    }

    private int doSomeTasks() {
        int fileSize =0;
        while (fileSize <= 1000) {

            fileSize++;
            return fileSize;

        }
        return 1000;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}
