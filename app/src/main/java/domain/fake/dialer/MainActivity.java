package domain.fake.dialer;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.lang.String;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "domain.fake.dialer.Country";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String pNumber = "+";

    public void onButtonClick(View v) {
        Button button = (Button) v;
        Button dialButton = (Button) findViewById(R.id.button);
        String bText = button.getText().toString();
        pNumber = pNumber.concat(bText);
        TextView myTextView = (TextView)
                findViewById(R.id.textView);
        myTextView.setText(pNumber);
        if(pNumber.length() > 1)
            dialButton.setEnabled(true);
    }

    public void backButtonClick(View v)
    {
        Button dialButton = (Button) findViewById(R.id.button);
        if(pNumber.length() > 1)
        {
            pNumber = pNumber.substring(0, pNumber.length() - 1);
        }
        TextView myTextView = (TextView)
                findViewById(R.id.textView);
        myTextView.setText(pNumber);
        if(pNumber.length() < 1)
            dialButton.setEnabled(false);
    }

    public void dialButtonClick(View view) {
        PhoneNumberUtil pUtil = PhoneNumberUtil.getInstance();
        boolean validNumber = true;
        try {
            pUtil.parse(pNumber, "ZZ");

        } catch (NumberParseException e) {
            validNumber = false;
        }
        if(validNumber)
            sendMessage(view);
        else
            errorPopup(view);
    }

    public void errorPopup(View view)
    {
        Snackbar mySnackbar = Snackbar.make(view, R.string.invalid_number, Snackbar.LENGTH_SHORT);
        mySnackbar.show();
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, Country.class);
        String message = pNumber;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
