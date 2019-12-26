package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Create", "Creating");
        setContentView(R.layout.activity_weather);
//         Create a new Fragment to be placed in the activity layout
        WeatherFragment secondFragment = new WeatherFragment();
        ForecastFragment firstFragment = new ForecastFragment();
        WeatherAndForecastFragment Fragment1 = new WeatherAndForecastFragment();
        WeatherAndForecastFragment Fragment2 = new WeatherAndForecastFragment();
        WeatherAndForecastFragment Fragment3 = new WeatherAndForecastFragment();
//         Add the fragment to the 'container' FrameLayout
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, secondFragment).commit();
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, firstFragment).commit();
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, Fragment1).commit();
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, Fragment2).commit();
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, Fragment3).commit();
        PagerAdapter adapter = new HomeFragmentPagerAdapter(
                getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);
        ExtractionInbound(R.raw.nhacthoitiet,"nhacthoitiet.mp3");
        MediaPlayer MP = new MediaPlayer();
        MP = MediaPlayer.create(WeatherActivity.this,R.raw.nhacthoitiet);
        MP.start();

    }
    private void ExtractionInbound(int nhacthoitietID, String nhacthoitiet){
        String path = Environment.getExternalStorageDirectory() + "/Android/data/vn.edu.usth.weather/" + nhacthoitiet;
        try{
            InputStream input = getResources().openRawResource(nhacthoitietID);
            FileOutputStream output = null;
            output = new FileOutputStream(path);
            byte[] buff = new byte[42069];
            int length =0;
            try {
                while ((length = input.read(buff)) > 0) {
                    output.write(buff, 0, length);
                }
            } finally
            {
                input.close();
                output.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.i("Start","Starting");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("Resume","Resuming");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("Pause","Pausing");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("Stop","Stoping");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("Destroy","Destroying");
    }

}
