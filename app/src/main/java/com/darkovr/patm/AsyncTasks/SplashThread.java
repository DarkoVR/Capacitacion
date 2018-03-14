package com.darkovr.patm.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import com.darkovr.patm.R;
import com.darkovr.patm.Activities.MainActivity;
import com.darkovr.patm.Fragments.SelectEmpleadoFragment;

/**
 * Created by marco on 9/03/18.
 */

// The types specified here are the input data type, the progress type, and the result type
public class SplashThread extends AsyncTask<Void, Integer, Void> {

    private ProgressBar splashBar;
    private Context context;

    public SplashThread(ProgressBar splashBar, Context context){
        this.splashBar = splashBar;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        splashBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i=1;i<=5;i++){
            publishProgress(i*20);
            try {
                java.lang.Thread.sleep(1000);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        splashBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        ((MainActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SelectEmpleadoFragment())
                .commit();

        splashBar.setVisibility(ProgressBar.INVISIBLE);
    }
}