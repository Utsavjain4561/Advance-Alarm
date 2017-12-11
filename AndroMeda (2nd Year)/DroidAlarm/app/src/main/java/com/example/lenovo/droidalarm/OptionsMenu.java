package com.example.lenovo.droidalarm;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lenovo on 11-Oct-17.
 */

public class OptionsMenu extends AppCompatActivity implements  Counter{


    ImageButton setSnooze;
    TextView snoozeTime;
    SeekBar diffMath;
    SeekBar diffShake;
    Toolbar setToolbar;

    public int mathD=0;
    public int shakeD=0;

    public int snooze=5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);

        setToolbar=(Toolbar)findViewById(R.id.toolbarSet);
        setSupportActionBar(setToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSnooze=(ImageButton)findViewById(R.id.setSnooze);
        snoozeTime=(TextView)findViewById(R.id.snoozetime);
        diffMath=(SeekBar)findViewById(R.id.seekMdiff);
        diffMath.setProgress(BaseActivity.math_diff);
        diffShake=(SeekBar)findViewById(R.id.seekTdiff);
        diffShake.setProgress(BaseActivity.shake_force);
        diffMath.setMax(3);
        diffShake.setMax(3);


        setSnooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getFragmentManager();
                SnoozeCount snoozeCount=new SnoozeCount();
                snoozeCount.show(fragmentManager,"Snooze");
            }
        });

        diffMath.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mathD=(int)seekBar.getProgress();
                Toast.makeText(getApplicationContext(),String.valueOf(mathD),Toast.LENGTH_SHORT).show();
                seekBar.setSecondaryProgress(seekBar.getProgress());
            }
        });

        diffShake.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                shakeD=(int)seekBar.getProgress();
                Toast.makeText(getApplicationContext(),String.valueOf(shakeD),Toast.LENGTH_SHORT).show();
                seekBar.setSecondaryProgress(seekBar.getProgress());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item_settings){
            goBack();
            return true;
        }else if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void countSelected(int n, int id) {

        if(n!=-1 && id==3){
            snooze=n;
        }else
            snooze=5;
        snoozeTime.setText(String.valueOf(snooze));
    }

    public void goBack(){
        Intent rIntent=new Intent();
        rIntent.putExtra(BaseActivity.SNOOZE_TIME,snooze);
        rIntent.putExtra(BaseActivity.MATH_DIFF,mathD);
        rIntent.putExtra(BaseActivity.SHAKE_DIFF,shakeD);
        setResult(Activity.RESULT_OK,rIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setToolbar.inflateMenu(R.menu.menu_action_settings);
        return super.onCreateOptionsMenu(menu);
    }
}
