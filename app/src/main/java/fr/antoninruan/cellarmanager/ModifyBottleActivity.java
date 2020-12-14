package fr.antoninruan.cellarmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import fr.antoninruan.cellarmanager.model.Bottle;
import fr.antoninruan.cellarmanager.model.WineType;

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
        RadioGroup type = findViewById(R.id.type);

        if (bottle != null) {
            name.getEditText().setText(bottle.getName());
            domain.getEditText().setText(bottle.getDomain());
            edition.getEditText().setText(bottle.getEdition());
            year.setText(String.valueOf(bottle.getYear()));
            consumeYear.setText(String.valueOf(bottle.getConsumeYear()));
            switch (bottle.getType()) {
                case ROUGE:
                    type.check(R.id.red);
                    break;
                case BLANC:
                    type.check(R.id.blanc);
                    break;
                case ROSE:
                    type.check(R.id.rose);
                    break;
                case CHAMPAGNE:
                    type.check(R.id.champagne);
                    break;
                case AUTRES:
                    type.check(R.id.other);
                    break;
            }
        }

        FloatingActionButton validate = findViewById(R.id.validate);
        validate.setOnClickListener((view) -> {
            Intent intent = new Intent();
            String result = "nom=" + name.getEditText().getText().toString() + ", domaine=" + domain.getEditText().getText().toString() +
                    ", edition=" + edition.getEditText().getText().toString();
            bottle.setName(name.getEditText().getText().toString());
            bottle.setDomain(domain.getEditText().getText().toString());
            bottle.setEdition(edition.getEditText().getText().toString());
            bottle.setYear(Integer.parseInt(year.getText().toString()));
            bottle.setConsumeYear(Integer.parseInt(consumeYear.getText().toString()));
            switch (type.getCheckedRadioButtonId()) {
                case R.id.red:
                    bottle.setType(WineType.ROUGE);
                    break;
                case R.id.blanc:
                    bottle.setType(WineType.BLANC);
                    break;
                case R.id.champagne:
                    bottle.setType(WineType.CHAMPAGNE);
                    break;
                case R.id.rose:
                    bottle.setType(WineType.ROSE);
                    break;
                case R.id.other:
                    bottle.setType(WineType.AUTRES);
                    break;
                default:
                    break;
            }
            //FIXME la mise à jour de la bouteille ne se fait pas dans la liste affiché
            finish();
        });
    }
}