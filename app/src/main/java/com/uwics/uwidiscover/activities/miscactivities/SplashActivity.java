package com.uwics.uwidiscover.activities.miscactivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.utils.ConnectionHelper;

/**
 * @author Howard Edwards
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (ConnectionHelper.isNetworkAvailable(this)) {
            int secondsDelayed = 2;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, SponsorActivity.class));
                    finish();
                }
            }, secondsDelayed * 1000);
        } else {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle(getString(R.string.string_connection_error_title))
                    .setMessage(R.string.string_connection_error_message)
                    .setPositiveButton(R.string.string_connection_try_again, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SplashActivity.this.recreate();
                        }
                    }).setNegativeButton(R.string.string_exit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    System.exit(0);
                }
            }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    SplashActivity.this.recreate();
                }
            }).show().setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}
