package com.doug.appcalcgorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView textPorcent;
    private TextView textGorja;
    private TextView textTotal;
    private SeekBar seekBarGorja;

    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor   = findViewById(R.id.editValor);
        textGorja   = findViewById(R.id.textGorja);
        textPorcent = findViewById(R.id.textPorcent);
        textTotal   = findViewById(R.id.textTotal);
        seekBarGorja= findViewById(R.id.seekBarGorja);

        //Adicionar listener seekbar
        seekBarGorja.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                textPorcent.setText(Math.round(porcentagem) + "%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calcular(){
        String valorRecuperado = editValor.getText().toString();
        if (valorRecuperado == null || valorRecuperado.equals("")) {
            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor primeiro!",
                    Toast.LENGTH_LONG
            ).show();
        }else{
            //Converter string para double
            double valorDigitado = Double.parseDouble(valorRecuperado);
            //Calcula a gorjeta total
            double gorjeta = valorDigitado * (porcentagem/100);
            double total = gorjeta + valorDigitado;
            //Exibe a gorjeta e total
            textGorja.setText("R$ " + String.format("%.2f", gorjeta));
            textTotal.setText("R$ " + String.format("%.2f", total));
        }
    }
}