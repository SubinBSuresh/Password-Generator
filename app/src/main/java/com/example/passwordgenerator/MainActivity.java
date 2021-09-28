package com.example.passwordgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private int passwordLength;
    private StringBuilder sb;
    private String allCharacters;
    private ClipboardManager myClipboard;
    private ClipData myClipData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGenerate = (Button) findViewById(R.id.btn_generate);
        Button btnReset = (Button) findViewById(R.id.btn_reset);
        final TextView tvPasswordShow = (TextView) findViewById(R.id.tv_password);
        final EditText etLength = (EditText) findViewById(R.id.et_passwordLength);
        final CheckBox symbolCheckBox = (CheckBox) findViewById(R.id.checkBox1);
        Button copyButton = (Button) findViewById(R.id.button1);


        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        final String symbols = "!@#$%^&*()_+.";
        final String total = (capitalLetters + smallLetters + numbers);

        // Password generate button
        btnGenerate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub


                if (symbolCheckBox.isChecked()) {
                    allCharacters = total + symbols;
                } else {
                    allCharacters = total;
                }

                if (etLength.getText().toString().isEmpty()) {
                    etLength.setError("Invalid Input");
                    etLength.requestFocus();
                } else {

                    passwordLength = Integer
                            .parseInt(etLength.getText().toString());
                    sb = new StringBuilder(passwordLength);

                    for (int i = 0; i < passwordLength; i++) {
                        int letterIndex = (int) (allCharacters.length() * Math.random());
                        sb.append(allCharacters.charAt(letterIndex));
                    }
                    tvPasswordShow.setText(sb.toString());
                }

            }
        });

        // Reset Button
        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                etLength.setText("");
                tvPasswordShow.setText("");
                symbolCheckBox.setChecked(false);

            }
        });

        //Copy Password
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClipData = ClipData.newPlainText("text", sb.toString());
                myClipboard.setPrimaryClip(myClipData);
                Toast.makeText(MainActivity.this, "Password Copied", Toast.LENGTH_SHORT).show();

            }
        });
    }
}