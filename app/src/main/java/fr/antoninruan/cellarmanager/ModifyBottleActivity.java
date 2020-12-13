package fr.antoninruan.cellarmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import fr.antoninruan.cellarmanager.model.Bottle;

public class ModifyBottleActivity extends AppCompatActivity {

    Bottle bottle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Integer.parseInt(getIntent().getDataString()) != -1)
            bottle = MainActivity.getBottles().get(Integer.parseInt(getIntent().getDataString()));
        setContentView(R.layout.activity_modify_bottle);
        TextInputLayout name = findViewById(R.id.name);
        TextInputLayout domain = findViewById(R.id.domain);
        TextInputLayout edition = findViewById(R.id.edition);
        EditText year = findViewById(R.id.year);
        EditText consumeYear = findViewById(R.id.consume_year);

        if (bottle != null) {
            name.getEditText().setText(bottle.getName());
            domain.getEditText().setText(bottle.getDomain());
            edition.getEditText().setText(bottle.getEdition());
            year.setText(bottle.getYear());
            consumeYear.setText(bottle.getConsumeYear());
        }

        FloatingActionButton validate = findViewById(R.id.validate);
        validate.setOnClickListener((view) -> {
            Intent intent = new Intent();
            String result = "nom=" + name.getEditText().getText().toString() + ", domaine=" + domain.getEditText().getText().toString() +
                    ", edition=" + edition.getEditText().getText().toString();
            intent.setData(Uri.parse(result));
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}