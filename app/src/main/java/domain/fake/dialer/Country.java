package domain.fake.dialer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class Country extends AppCompatActivity {
    private WebView myWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(message);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri number = Uri.parse("tel:" + message);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        String country = getCountry(message);
        myWebView.loadUrl("http://en.wikipedia.org/wiki/"+country);
    }

    public String getCountry(String phoneNumber)
    {
        PhoneNumberUtil pUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber pNum = null;
        try {
            pNum = pUtil.parse(phoneNumber, "ZZ");

        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown:" + e.toString());
        }

        String regionCode = pUtil.getRegionCodeForNumber(pNum);
        String country = "ISO_3166-1:" + regionCode;
        return country;
    }
}
