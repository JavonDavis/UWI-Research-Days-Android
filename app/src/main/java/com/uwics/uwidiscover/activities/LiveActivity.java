package com.uwics.uwidiscover.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.uwics.uwidiscover.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class LiveActivity extends AppCompatActivity {

    private final static Calendar CURRENT_DAY = Calendar.getInstance();
    private final static Calendar DAY_ONE = new GregorianCalendar(2017, 1, 1);
    private final static Calendar DAY_TWO = new GregorianCalendar(2017, 1, 2);
    private final static Calendar DAY_THREE = new GregorianCalendar(2017, 1, 3);

    private final static int DAY_ONE_DAY_OF_YEAR = DAY_ONE.get(Calendar.DAY_OF_YEAR);
    private final static int DAY_TWO_DAY_OF_YEAR = DAY_TWO.get(Calendar.DAY_OF_YEAR);
    private final static int DAY_THREE_DAY_OF_YEAR = DAY_THREE.get(Calendar.DAY_OF_YEAR);

    private final static int DAY_ONE_YEAR = DAY_ONE.get(Calendar.YEAR);
    private final static int DAY_TWO_YEAR = DAY_TWO.get(Calendar.YEAR);
    private final static int DAY_THREE_YEAR = DAY_THREE.get(Calendar.YEAR);

    private WebView mWebView;
    private ProgressBar progressBar;
    private Spinner streamChannelSpinner;
    private TextView noStreamTextView;
    private TextView channelLiveStreamTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));
        setContentView(R.layout.activity_live);

        if (getActionBar() != null) {
            getActionBar().setTitle("Live");
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mWebView = (WebView) findViewById(R.id.webview);
        streamChannelSpinner = (Spinner) findViewById(R.id.channel_selector_spinner);
        noStreamTextView = (TextView) findViewById(R.id.text_no_stream_available);
        channelLiveStreamTitleTextView = (TextView) findViewById(R.id.channel_live_stream_title);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setMax(100);

        setUpChannelSpinner();
        setUpWebView();
    }

    private void setUpChannelSpinner() {
        ArrayAdapter<String> streamChannelOptionsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.array_stream_channels));
        streamChannelOptionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        streamChannelSpinner.setAdapter(streamChannelOptionsAdapter);
        streamChannelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mWebView.loadUrl("about:blank");
                int channelIndex = parent.getSelectedItemPosition();
                showStream(channelIndex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void showStream(int channelIndex) {
        // TODO: Gonna probably run this through an Async after testing
        // TODO: Do some check to show unavailable stream if user is on screen and the stream ends
        int i = researchDayIndex();
        if (i != 0) {
            String streamUrl = validLinkToStreamForTime(i, channelIndex);
            if (streamUrl != null) {
                noStreamTextView.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
                mWebView.loadUrl(streamUrl);
            } else {
                mWebView.setVisibility(View.GONE);
                noStreamTextView.setVisibility(View.VISIBLE);
            }
        } else {
            mWebView.setVisibility(View.GONE);
            noStreamTextView.setVisibility(View.VISIBLE);
        }
    }

    private int researchDayIndex() {
        if ((CURRENT_DAY.get(Calendar.YEAR) == DAY_ONE_YEAR) && (CURRENT_DAY.get(Calendar.DAY_OF_YEAR) == DAY_ONE_DAY_OF_YEAR)) {
            return 1;
        } else if ((CURRENT_DAY.get(Calendar.YEAR) == DAY_TWO_YEAR) && (CURRENT_DAY.get(Calendar.DAY_OF_YEAR) == DAY_TWO_DAY_OF_YEAR)) {
            return 2;
        } else if ((CURRENT_DAY.get(Calendar.YEAR) == DAY_THREE_YEAR) && (CURRENT_DAY.get(Calendar.DAY_OF_YEAR) == DAY_THREE_DAY_OF_YEAR)) {
            return 3;
        } else return 0;
    }

    /* D1
    9 - 12 : C1 - S1
    10:30 - 3 : C2 - S2
    12 - ?? : C1 - S3
    2 - 7 : C3 - S4
    5:16 - 9 : C2 - S5

    D2
    11 - 1:30 : C1 - S1
    12 - 3 : C2 - S2
    1:30 - 4 : C1 - S3
    1:30 - 5 : C3 - S4
    2:30 - 5:30 : C4 - S5
    4 - 9 : C1 - S6
    4 - 9 : C2 - S7

    D3
    (9:30 (10 - 12) 12:30) : C1 - S1
    (11:45 (12 - 12:45) 1) : C2 - S2
    3 - ?? : C1 - S3 */
    @Nullable
    private String validLinkToStreamForTime(int rDayIndex, int cStream) {
        if (rDayIndex == 1) {
            if (cStream == 0) {
                if (CURRENT_DAY.after(tempDay(rDayIndex, 9, 0)) && CURRENT_DAY.before(tempDay(rDayIndex, 12, 0))) {
                    channelLiveStreamTitleTextView.setText(getString(R.string.d1_s1));
                    return getString(R.string.string_d1_s1_c1);
                } else if (CURRENT_DAY.after(tempDay(rDayIndex, 12, 0)) && CURRENT_DAY.before(tempDay(rDayIndex, 13, 0))) {
                    // TODO: Verify this time
                    channelLiveStreamTitleTextView.setText(getString(R.string.d1_s3));
                    return getString(R.string.string_d1_s3_c1);
                }
            } else if (cStream == 1) {
                if (CURRENT_DAY.after(tempDay(rDayIndex, 10, 30)) && CURRENT_DAY.before(tempDay(rDayIndex, 15, 0))) {
                    channelLiveStreamTitleTextView.setText(getString(R.string.d1_s2));
                    return getString(R.string.string_d1_s2_c2);
                } else if (CURRENT_DAY.after(tempDay(rDayIndex, 17, 16)) && CURRENT_DAY.before(tempDay(rDayIndex, 9, 0))) {
                    channelLiveStreamTitleTextView.setText(getString(R.string.d1_s5));
                    return getString(R.string.string_d1_s5_c2);
                }
            } else if (cStream == 2) {
                if (CURRENT_DAY.after(tempDay(rDayIndex, 14, 0)) && CURRENT_DAY.before(tempDay(rDayIndex, 19, 0))) {
                    channelLiveStreamTitleTextView.setText(getString(R.string.d1_s4));
                    return getString(R.string.string_d1_s4_c3);
                }
            } else if (cStream == 3) {
                channelLiveStreamTitleTextView.setText("");
                return null;
            }
        } else if (rDayIndex == 2) {
            if (cStream == 0) {
                if (CURRENT_DAY.after(tempDay(rDayIndex, 11, 0)) && CURRENT_DAY.before(tempDay(rDayIndex, 13, 30))) {
                    channelLiveStreamTitleTextView.setText(getString(R.string.d2_s1));
                    return getString(R.string.string_d2_s1_c1);
                } else if (CURRENT_DAY.after(tempDay(rDayIndex, 13, 30)) && CURRENT_DAY.before(tempDay(rDayIndex, 16, 0))) {
                    channelLiveStreamTitleTextView.setText(getString(R.string.d2_s3));
                    return getString(R.string.string_d2_s3_c1);
                } else if (CURRENT_DAY.after(tempDay(rDayIndex, 16, 0)) && CURRENT_DAY.before(tempDay(rDayIndex, 21, 0))) {
                    channelLiveStreamTitleTextView.setText(getString(R.string.d2_s6));
                    return getString(R.string.string_d2_s6_c1);
                }
            } else if (cStream == 1) {
                if (CURRENT_DAY.after(tempDay(rDayIndex, 21, 0)) && CURRENT_DAY.before(tempDay(rDayIndex, 21, 0))) {
                    channelLiveStreamTitleTextView.setText(getString(R.string.d2_s2));
                    return getString(R.string.string_d2_s2_c2);
                } else if (CURRENT_DAY.after(tempDay(rDayIndex, 16, 0)) && CURRENT_DAY.before(tempDay(rDayIndex, 21, 0))) {
                    channelLiveStreamTitleTextView.setText(getString(R.string.d2_s7));
                    return getString(R.string.string_d2_s7_c2);
                }
            } else if (cStream == 2) {
                if (CURRENT_DAY.after(tempDay(rDayIndex, 13, 30)) && CURRENT_DAY.before(tempDay(rDayIndex, 17, 0))) {
                    channelLiveStreamTitleTextView.setText(getString(R.string.d2_s4));
                    return getString(R.string.string_d2_s4_c3);
                }
            } else if (cStream == 3) {
                if (CURRENT_DAY.after(tempDay(rDayIndex, 14, 30)) && CURRENT_DAY.before(tempDay(rDayIndex, 17, 30))) {
                    channelLiveStreamTitleTextView.setText(getString(R.string.d2_s5));
                    return getString(R.string.string_d2_s5_c4);
                }
            }
        } else if (rDayIndex == 3) {
            if (cStream == 0) {
                if (CURRENT_DAY.after(tempDay(rDayIndex, 10, 0)) && CURRENT_DAY.before(tempDay(rDayIndex, 12, 0))) {
                    // TODO: Verify this time
                    channelLiveStreamTitleTextView.setText(getString(R.string.d3_s1));
                    return getString(R.string.string_d3_s1_c1);
                } else if (CURRENT_DAY.after(tempDay(rDayIndex, 15, 0)) && CURRENT_DAY.before(tempDay(rDayIndex, 17, 0))) {
                    // TODO: Verify this time
                    channelLiveStreamTitleTextView.setText(getString(R.string.d3_s3));
                    return getString(R.string.string_d3_s3_c1);
                }
            } else if (cStream == 1) {
                if (CURRENT_DAY.after(tempDay(rDayIndex, 12, 0)) && CURRENT_DAY.before(tempDay(rDayIndex, 12, 45))) {
                    // TODO: Verify this time
                    channelLiveStreamTitleTextView.setText(getString(R.string.d3_s2));
                    return getString(R.string.string_d3_s2_c2);
                }
            } else if (cStream == 2) {
                channelLiveStreamTitleTextView.setText("");
                return null;
            } else if (cStream == 3) {
                channelLiveStreamTitleTextView.setText("");
                return null;
            }
        }
        channelLiveStreamTitleTextView.setText("");
        return null;
    }

    @Nullable
    private Calendar tempDay(int cal, int hourOfDay, int min) {
        Calendar temp;
        switch (cal) {
            case 1:
                temp = DAY_ONE;
                temp.set(Calendar.HOUR_OF_DAY, hourOfDay);
                temp.set(Calendar.MINUTE, min);
                return temp;
            case 2:
                temp = DAY_TWO;
                temp.set(Calendar.HOUR_OF_DAY, hourOfDay);
                temp.set(Calendar.MINUTE, min);
                return temp;
            case 3:
                temp = DAY_THREE;
                temp.set(Calendar.HOUR_OF_DAY, hourOfDay);
                temp.set(Calendar.MINUTE, min);
                return temp;
            default:
                return null;
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setUpWebView() {
        mWebView.setWebViewClient(new WebClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class WebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setProgress(100);
            progressBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }
    }
}
