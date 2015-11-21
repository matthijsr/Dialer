package domain.fake.dialer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.String;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String pNumber = "";

    public void onButtonClick(View v) {
        Button button = (Button) v;
        String bText = button.getText().toString();
        pNumber = pNumber.concat(bText);
        TextView myTextView = (TextView)
                findViewById(R.id.textView);
        myTextView.setText(pNumber);
    }

    public void backButtonClick(View v)
    {
        if(pNumber.length() > 0)
        {
            pNumber = pNumber.substring(0, pNumber.length() - 1);
        }
        TextView myTextView = (TextView)
                findViewById(R.id.textView);
        myTextView.setText(pNumber);
    }
}
