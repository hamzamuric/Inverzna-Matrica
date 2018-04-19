package com.hamza.inverznamatrica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText00;
    EditText editText01;
    EditText editText02;
    EditText editText10;
    EditText editText11;
    EditText editText12;
    EditText editText20;
    EditText editText21;
    EditText editText22;

    TextView textView00;
    TextView textView01;
    TextView textView02;
    TextView textView10;
    TextView textView11;
    TextView textView12;
    TextView textView20;
    TextView textView21;
    TextView textView22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText00 = findViewById(R.id.editText00);
        editText01 = findViewById(R.id.editText01);
        editText02 = findViewById(R.id.editText02);
        editText10 = findViewById(R.id.editText10);
        editText11 = findViewById(R.id.editText11);
        editText12 = findViewById(R.id.editText12);
        editText20 = findViewById(R.id.editText20);
        editText21 = findViewById(R.id.editText21);
        editText22 = findViewById(R.id.editText22);

        textView00 = findViewById(R.id.textView00);
        textView01 = findViewById(R.id.textView01);
        textView02 = findViewById(R.id.textView02);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);
        textView20 = findViewById(R.id.textView20);
        textView21 = findViewById(R.id.textView21);
        textView22 = findViewById(R.id.textView22);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    izracunaj();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Popunite prazna polja!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void izracunaj() {
        double[][] matrica = new double[3][3];
        matrica[0][0] = Double.parseDouble(editText00.getText().toString());
        matrica[0][1] = Double.parseDouble(editText01.getText().toString());
        matrica[0][2] = Double.parseDouble(editText02.getText().toString());
        matrica[1][0] = Double.parseDouble(editText10.getText().toString());
        matrica[1][1] = Double.parseDouble(editText11.getText().toString());
        matrica[1][2] = Double.parseDouble(editText12.getText().toString());
        matrica[2][0] = Double.parseDouble(editText20.getText().toString());
        matrica[2][1] = Double.parseDouble(editText21.getText().toString());
        matrica[2][2] = Double.parseDouble(editText22.getText().toString());

        double[][] adj = new double[3][3];
        adj[0][0] =   matrica[1][1] * matrica[2][2] - matrica[2][1] * matrica[1][2];
        adj[1][0] = -(matrica[1][0] * matrica[2][2] - matrica[2][0] * matrica[1][2]);
        adj[2][0] =   matrica[1][0] * matrica[2][1] - matrica[2][0] * matrica[1][1];
        adj[0][1] = -(matrica[0][1] * matrica[2][2] - matrica[2][1] * matrica[0][2]);
        adj[1][1] =   matrica[0][0] * matrica[2][2] - matrica[2][0] * matrica[0][2];
        adj[2][1] = -(matrica[0][0] * matrica[2][1] - matrica[2][0] * matrica[0][1]);
        adj[0][2] =   matrica[0][1] * matrica[1][2] - matrica[1][1] * matrica[0][2];
        adj[1][2] = -(matrica[0][0] * matrica[1][2] - matrica[1][0] * matrica[0][2]);
        adj[2][2] =   matrica[0][0] * matrica[1][1] - matrica[1][0] * matrica[0][1];

        double det = matrica[0][0] * matrica[1][1] * matrica[2][2]
                + matrica[0][1] * matrica[1][2] * matrica[2][0]
                + matrica[0][2] * matrica[1][0] * matrica[2][1]
                -(matrica[2][0] * matrica[1][1] * matrica[0][2]
                + matrica[2][1] * matrica[1][2] * matrica[0][0]
                + matrica[2][2] * matrica[1][0] * matrica[0][1]);

        double[][] inverzna = new double[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                inverzna[i][j] = 1 / det * adj[i][j];


        String[][] res = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (inverzna[i][j] == (int) inverzna[i][j]) {
                    res[i][j] = String.format("%d", (int)inverzna[i][j]);
                } else {
                    // res[i][j] = String.format("%.2f", inverzna[i][j]);
                    res[i][j] = razlomak(inverzna[i][j]);
                }
            }
        }

        textView00.setText(res[0][0]);
        textView01.setText(res[0][1]);
        textView02.setText(res[0][2]);
        textView10.setText(res[1][0]);
        textView11.setText(res[1][1]);
        textView12.setText(res[1][2]);
        textView20.setText(res[2][0]);
        textView21.setText(res[2][1]);
        textView22.setText(res[2][2]);
    }

    public int gcd(int x, int y) {
        if (x < 0) x = -x;
        if (y < 0) y = -y;
        if (y == 0) return x;
        else return gcd(y, x % y);
    }

    public String razlomak(double br) {
        int brojilac, imenilac;
        String s = String.valueOf(br);
        int cifreDec = s.length() - 1 - s.indexOf('.');

        imenilac = 1;
        for (int i = 0; i < cifreDec; i++) {
            br *= 10;
            imenilac *= 10;
        }

        brojilac = (int) Math.round(br);
        int gcd = gcd(brojilac, imenilac);
        brojilac /= gcd;
        imenilac /= gcd;


        return String.valueOf(brojilac) + '/' + String.valueOf(imenilac);
    }
}
