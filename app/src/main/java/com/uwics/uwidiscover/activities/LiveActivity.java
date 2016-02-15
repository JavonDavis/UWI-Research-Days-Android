package com.uwics.uwidiscover.activities;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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

    private final static Calendar DAY_ONE = new GregorianCalendar(2016, 1, 17);
    private final static Calendar DAY_TWO = new GregorianCalendar(2016, 1, 18);
    private final static Calendar DAY_THREE = new GregorianCalendar(2016, 1, 19);
    private final Calendar CURRENT_DAY = Calendar.getInstance();
    private WebView mWebView;
    private ProgressBar progressBar;
    private Spinner streamChannelSpinner;
    private TextView noStreamTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        if (getActionBar() != null) {
            getActionBar().setTitle("Live");
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mWebView = (WebView) findViewById(R.id.webview);
        streamChannelSpinner = (Spinner) findViewById(R.id.channel_selector_spinner);
        noStreamTextView = (TextView) findViewById(R.id.text_no_stream_available);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setMax(100);

        setUpChannelSpinner();
        setUpWebView();
    }

    private void setUpChannelSpinner() {
        ArrayAdapter<String> streamChannelOptionsAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.array_stream_channels));
        streamChannelOptionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        streamChannelSpinner.setAdapter(streamChannelOptionsAdapter);
        streamChannelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int channelIndex = parent.getSelectedItemPosition();
                showStream(channelIndex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private void showStream(int channelIndex) {
        // TODO: Gonna probably run this through an Async after testing
        // TODO: Do some check to show unavailable stream if user is on screen and the stream ends
        int rDay = currResearchDay();
        String streamUrl = validTimeForStream(rDay, channelIndex);
        if (rDay != -1) {
            switch (channelIndex) {
                case 0:
                    switch (rDay) {
                        case 1:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                        case 2:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                        case 3:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                    }
                    break;
                case 1:
                    switch (rDay) {
                        case 1:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                        case 2:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                        case 3:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                    }
                    break;
                case 2:
                    switch (rDay) {
                        case 1:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                        case 2:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                        case 3:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                    }
                    break;
                case 3:
                    switch (rDay) {
                        case 1:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                        case 2:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                        case 3:
                            if (streamUrl != null) {
                                noStreamTextView.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                mWebView.loadUrl(streamUrl);
                            }
                            break;
                    }
                    break;
            }
        } else {
            mWebView.setVisibility(View.GONE);
            noStreamTextView.setVisibility(View.VISIBLE);
        }
    }

    private int currResearchDay() {
        if (isResearchDay(DAY_ONE)) {
            return 1;
        } else if (isResearchDay(DAY_TWO)) {
            return 2;
        } else if (isResearchDay(DAY_THREE)) {
            return 3;
        }
        return -1;
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
    private String validTimeForStream(int rDay, int cStream) {
        if (rDay == 1) {
            if (cStream == 1) {
                if (CURRENT_DAY.after(tempDay(rDay, 9, 0)) && CURRENT_DAY.before(tempDay(rDay, 12, 0))) {
                    return getString(R.string.string_d1_s1_c1);
                } else if (CURRENT_DAY.after(tempDay(rDay, 12, 0)) && CURRENT_DAY.before(tempDay(rDay, 12, 45))) {
                    // TODO: Verify this time
                    return getString(R.string.string_d1_s3_c1);
                }
            } else if (cStream == 2) {
                if (CURRENT_DAY.after(tempDay(rDay, 10, 30)) && CURRENT_DAY.before(tempDay(rDay, 3, 0))) {
                    return getString(R.string.string_d1_s2_c2);
                } else if (CURRENT_DAY.after(tempDay(rDay, 5, 16)) && CURRENT_DAY.before(tempDay(rDay, 9, 0))) {
                    return getString(R.string.string_d1_s5_c2);
                }
            } else if (cStream == 3) {
                if (CURRENT_DAY.after(tempDay(rDay, 2, 0)) && CURRENT_DAY.before(tempDay(rDay, 7, 0))) {
                    return getString(R.string.string_d1_s4_c3);
                }
            } else if (cStream == 4) {
                return null;
            }
        } else if (rDay == 2) {
            if (cStream == 1) {
                if (CURRENT_DAY.after(tempDay(rDay, 11, 0)) && CURRENT_DAY.before(tempDay(rDay, 1, 30))) {
                    return getString(R.string.string_d2_s1_c1);
                } else if (CURRENT_DAY.after(tempDay(rDay, 1, 30)) && CURRENT_DAY.before(tempDay(rDay, 4, 0))) {
                    return getString(R.string.string_d2_s3_c1);
                } else if (CURRENT_DAY.after(tempDay(rDay, 4, 0)) && CURRENT_DAY.before(tempDay(rDay, 9, 0))) {
                    return getString(R.string.string_d2_s6_c1);
                }
            } else if (cStream == 2) {
                if (CURRENT_DAY.after(tempDay(rDay, 12, 0)) && CURRENT_DAY.before(tempDay(rDay, 3, 0))) {
                    return getString(R.string.string_d2_s2_c2);
                } else if (CURRENT_DAY.after(tempDay(rDay, 4, 0)) && CURRENT_DAY.before(tempDay(rDay, 9, 0))) {
                    return getString(R.string.string_d2_s7_c2);
                }

            } else if (cStream == 3) {
                if (CURRENT_DAY.after(tempDay(rDay, 1, 30)) && CURRENT_DAY.before(tempDay(rDay, 5, 0))) {
                    return getString(R.string.string_d2_s4_c3);
                }
            } else if (cStream == 4) {
                if (CURRENT_DAY.after(tempDay(rDay, 2, 30)) && CURRENT_DAY.before(tempDay(rDay, 5, 30))) {
                    return getString(R.string.string_d2_s5_c4);
                }
            }
        } else if (rDay == 3) {
            if (cStream == 1) {
                if (CURRENT_DAY.after(tempDay(rDay, 10, 0)) && CURRENT_DAY.before(tempDay(rDay, 12, 0))) {
                    // TODO: Verify this time
                    return getString(R.string.string_d3_s1_c1);
                } else if (CURRENT_DAY.after(tempDay(rDay, 3, 0)) && CURRENT_DAY.before(tempDay(rDay, 5, 0))) {
                    // TODO: Verify this time
                    return getString(R.string.string_d3_s3_c1);
                }
            } else if (cStream == 2) {
                if (CURRENT_DAY.after(tempDay(rDay, 12, 0)) && CURRENT_DAY.before(tempDay(rDay, 12, 45))) {
                    // TODO: Verify this time
                    return getString(R.string.string_d3_s2_c2);
                }
            } else if (cStream == 3) {
                return null;
            } else if (cStream == 4) {
                return null;
            }
        }
        return null;
    }

    private boolean isResearchDay(Calendar rDay) {
        return CURRENT_DAY.get(Calendar.YEAR) == rDay.get(Calendar.YEAR) &&
                CURRENT_DAY.get(Calendar.DAY_OF_YEAR) == rDay.get(Calendar.DAY_OF_YEAR);
    }

    @Nullable
    private Calendar tempDay(int cal, int hourOfDay, int min) {
        Calendar temp;
        switch (cal) {
            case 1:
                temp = DAY_ONE;
                temp.add(Calendar.HOUR_OF_DAY, hourOfDay);
                temp.add(Calendar.MINUTE, min);
                return temp;
            case 2:
                temp = DAY_TWO;
                temp.add(Calendar.HOUR_OF_DAY, hourOfDay);
                temp.add(Calendar.MINUTE, min);
                return temp;
            case 3:
                temp = DAY_THREE;
                temp.add(Calendar.HOUR_OF_DAY, hourOfDay);
                temp.add(Calendar.MINUTE, min);
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

//        mWebView.loadUrl("http://tv.mona.uwi.edu/");
//        mWebView.loadUrl("http://player.twitch.tv/?volume=0.5&channel=esl_sc2&time=14");
//        mWebView.loadUrl("http://www.uwicfptv.com/livestream");
//        mWebView.loadUrl("http://tv.mona.uwi.edu/research-days-2016-fhe-lunch-hour-concert");
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
