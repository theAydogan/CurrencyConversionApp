package ca.mohawk.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

//I, Ahmet,Aydogan, 000792453 certify that this material is my original
//work. No other person's work has been used without due acknowledgement.

public class MainActivity2 extends AppCompatActivity {
    public Button saveBut;
    public TextView convRate;
    public static String selectedCurrency;

    private double audRate;
    private double usdRate;
    private double rupRate;
    private double gbpRate;
    private double euroRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Currency Conversion");
        saveBut = (Button) findViewById(R.id.saveButton);
        convRate = (TextView) findViewById(R.id.conversionRate);
        final String[] newItem = new String[1];

        Intent intent = getIntent();
        audRate = intent.getDoubleExtra("aud_rate", 0.0);
        usdRate = intent.getDoubleExtra("usd_rate", 0.0);
        rupRate = intent.getDoubleExtra("rup_rate", 0.0);
        gbpRate = intent.getDoubleExtra("gbp_rate", 0.0);
        euroRate = intent.getDoubleExtra("euro_rate", 0.0);

        /**
         * The variables below create an ArrayAdapter using the string array and a default spinner layout.
         * The listed items are callled from the strings.xml file and placed into the array to be accessed.
         * Apply the adapter to the spinner and the choices appear upon the screen
         **/

        Spinner spinner = (Spinner) findViewById(R.id.spinner_currencies);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        /**
         * In the OnItemSelected method, when an item is selected within the spinner, the string value of that item is
         * placed into an array called newItem which is the size of 1. This is going to be sent
         * back to the first activity to run the switch-case for conversion.
         **/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newItem[0] = spinner.getSelectedItem().toString();
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                double rate = 0.0;
                switch (selectedItem) {
                    case "AUD":
                        rate = audRate;
                        break;
                    case "RUP":
                        rate = rupRate;
                        break;
                    case "USD":
                        rate = usdRate;
                        break;
                    case "GBP":
                        rate = gbpRate;
                        break;
                    case "EUR":
                        rate = euroRate;
                        break;
                }
                convRate.setText(String.format("%.2f", rate));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /**
         * When the save button is clicked, an intent variable is assigned and accesses the first screen.
         * This intent also has a putExtra method attatched to it with every switch-case, it sends back
         * the value of which flag should be assigned according to which currency selected. This is
         * accessed by the bundle wtihin the first screen.
         **/
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCurrency = newItem[0];
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                switch (selectedCurrency) {
                    case "AUD":
                        intent.putExtra("aud.png", R.drawable.aud);
                        startActivity(intent);
                        break;
                    case "RUP":
                        intent.putExtra("rup.png", R.drawable.rup);
                        startActivity(intent);
                        break;
                    case "USD":
                        intent.putExtra("murica.png", R.drawable.murica);
                        startActivity(intent);
                        break;
                    case "GBP":
                        intent.putExtra("gbp.png", R.drawable.gbp);
                        startActivity(intent);
                        break;
                    case "EUR":
                        intent.putExtra("euro.png", R.drawable.euro);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}