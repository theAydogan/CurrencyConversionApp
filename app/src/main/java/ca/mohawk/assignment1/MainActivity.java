package ca.mohawk.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

//I, Ahmet,Aydogan, 000792453 certify that this material is my original
//work. No other person's work has been used without due acknowledgement.

public class MainActivity extends AppCompatActivity {
    double usd = 0.74;
    double rup = 61.32;
    double gbp = 0.61;
    double aud = 1.07;
    double euro = 0.69;

    double convertedValue;
    public ImageView flagLeft;
    public ImageView flagRight;
    public EditText userInput;
    public Button convertBut;
    public TextView resultValue;

    public Button prefButton;
    public boolean usdSelected = false;
    public boolean audSelected = false;
    public boolean rupSelected = false;
    public boolean gbpSelected = false;
    public boolean euroSelected = false;

    double val = 0;
    String convertText = "@ ";
    String val3 = MainActivity2.selectedCurrency;
    String temp = "";



    @SuppressLint("SetTextI18n")
    @Override

    /**
     onCreate method is upon the start up of the program, various
     objects are initialized and placed onto the main activity.
    **/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Currency Conversion");

        flagLeft = (ImageView) findViewById(R.id.flag1);
        flagRight = (ImageView) findViewById(R.id.flag2);
        userInput = (EditText) findViewById(R.id.inputID);
        convertBut = (Button) findViewById(R.id.convert);
        resultValue = (TextView) findViewById(R.id.resultID);
        prefButton = (Button) findViewById(R.id.preferences);

        userInput.setText("0");

        /**
            Since USD is going to be the default currency, it is put in place of the right flag holder
            and according to the flag, it selects and switches its according boolean value true to change the convert button value
        **/
         if(findViewById(R.id.flag2).equals(findViewById(R.id.flag2))){
            usdSelected = true;
            convertBut.setText(convertText + usd + " = ");
        }
        /**
            This bundle allows for the import of information of variables in the second activity.
            According to the value presented in the second activity, a switch-case decides which flag
            is presented, what the convert button value is, and which boolean value is now true according
            to the currency selected.
         **/
        Bundle bundle = getIntent().getExtras();
        int redId;
        if(bundle != null) {
            switch (val3) {
                case "AUD":
                    redId = bundle.getInt("aud.png");
                    flagRight.setImageResource(redId);
                    convertBut.setText(convertText + aud + " = ");
                    resultValue.setText("0");
                    audSelected = true;
                    usdSelected = false;
                    gbpSelected = false;
                    rupSelected = false;
                    euroSelected = false;
                    break;
                case "USD":
                    redId = bundle.getInt("murica.png");
                    flagRight.setImageResource(redId);
                    convertBut.setText(convertText + usd + " = ");
                    audSelected = false;
                    gbpSelected = false;
                    rupSelected = false;
                    usdSelected = true;
                    euroSelected = false;
                    break;
                case "RUP":
                    redId = bundle.getInt("rup.png");
                    flagRight.setImageResource(redId);
                    convertBut.setText(convertText + rup + " = ");
                    audSelected = false;
                    usdSelected = false;
                    rupSelected = true;
                    gbpSelected = false;
                    euroSelected = false;
                    break;
                case "GBP":
                    redId = bundle.getInt("gbp.png");
                    flagRight.setImageResource(redId);
                    convertBut.setText(convertText + gbp + " = ");
                    audSelected = false;
                    usdSelected = false;
                    rupSelected = false;
                    gbpSelected = true;
                    euroSelected = false;
                    break;
                case "EUR":
                    redId = bundle.getInt("euro.png");
                    flagRight.setImageResource(redId);
                    convertBut.setText(convertText + euro + " = ");
                    audSelected = false;
                    usdSelected = false;
                    rupSelected = false;
                    gbpSelected = false;
                    euroSelected = true;
                    break;
            }

        }

        /**
            When the convert button is clicked, according to the boolean currency value selected,
            the conversion happens and the result is displayed in the textview next to the button.
         **/
        convertBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userInput.length() != 0 && usdSelected) {
                    String temp = userInput.getText().toString();
                    val = Integer.parseInt(temp);
                    convertedValue = val * usd;
                    resultValue.setText(String.format(Double.toString(convertedValue)));
                }
                else if (audSelected) {
                    temp = userInput.getText().toString();
                    val = Integer.parseInt(temp);
                    convertedValue = val * aud;
                    resultValue.setText(String.format(Double.toString(convertedValue)));
                }
                else if (rupSelected) {
                    temp = userInput.getText().toString();
                    val = Integer.parseInt(temp);
                    convertedValue = val * rup;
                    resultValue.setText(String.format(Double.toString(convertedValue)));
                }
                else if (gbpSelected) {
                    temp = userInput.getText().toString();
                    val = Integer.parseInt(temp);
                    convertedValue = val * gbp;
                    resultValue.setText(String.format(Double.toString(convertedValue)));
                }
                else if (euroSelected) {
                    temp = userInput.getText().toString();
                    val = Integer.parseInt(temp);
                    convertedValue = val * euro;
                    resultValue.setText(String.format(Double.toString(convertedValue)));
                }
            }
        });
        /**
         * This button leads to the next activity screen
         **/
        prefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("usd_rate", usd);
                intent.putExtra("aud_rate", aud);
                intent.putExtra("rup_rate", rup);
                intent.putExtra("gbp_rate", gbp);
                intent.putExtra("euro_rate", euro);
                startActivity(intent);
            }
        });
    }

}