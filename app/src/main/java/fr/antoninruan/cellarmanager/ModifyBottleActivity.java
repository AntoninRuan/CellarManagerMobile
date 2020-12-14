package fr.antoninruan.cellarmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import fr.antoninruan.cellarmanager.model.Bottle;
import fr.antoninruan.cellarmanager.model.BottleInfo;
import fr.antoninruan.cellarmanager.model.WineType;
import fr.antoninruan.cellarmanager.ui.bottles.BottlesFragment;

public class ModifyBottleActivity extends AppCompatActivity {

    Bottle bottle = null;

    boolean create = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        create = getIntent().getBooleanExtra("create", false);
        System.out.println(create);
        if(!create)
            bottle = MainActivity.getBottles().get(Integer.parseInt(getIntent().getDataString()));
        setContentView(R.layout.activity_modify_bottle);
        TextInputLayout name = findViewById(R.id.name);
        TextInputLayout domain = findViewById(R.id.domain);
        TextInputLayout edition = findViewById(R.id.edition);
        EditText year = findViewById(R.id.year);
        EditText consumeYear = findViewById(R.id.consume_year);
        Spinner type = findViewById(R.id.type);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.wine_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        type.setAdapter(adapter);

        if (bottle != null) {
            name.getEditText().setText(bottle.getName());
            domain.getEditText().setText(bottle.getDomain());
            edition.getEditText().setText(bottle.getEdition());
            year.setText(String.valueOf(bottle.getYear()));
            consumeYear.setText(String.valueOf(bottle.getConsumeYear()));
            type.setSelection(bottle.getType().getId());
        } else {
            year.setText(String.valueOf(1980));
            consumeYear.setText(year.getText());
        }

        FloatingActionButton validate = findViewById(R.id.validate);
        validate.setOnClickListener((view) -> {
            BottleInfo info = new BottleInfo();

            info.setName(name.getEditText().getText().toString());
            info.setDomain(domain.getEditText().getText().toString());
            info.setEdition(edition.getEditText().getText().toString());
            info.setYear(Integer.parseInt(year.getText().toString()));
            info.setConsumeYear(Integer.parseInt(consumeYear.getText().toString()));
            info.setType(WineType.fromId(type.getSelectedItemPosition()));

            if (create) {
                if (!name.getEditText().getText().toString().isEmpty() || !edition.getEditText().getText().toString().isEmpty() ||
                        !domain.getEditText().getText().toString().isEmpty() || Integer.parseInt(year.getText().toString()) != 1980 ||
                        Integer.parseInt(consumeYear.getText().toString()) != 1980)
                    bottle = info.createBottle();
            }
            else
                info.modifyBottle(bottle);
            BottlesFragment.getAdapter().notifyDataSetChanged();
            finish();
        });

        FloatingActionButton delete = findViewById(R.id.delete);
        delete.setOnClickListener((view) -> {
            if (!create) {
                MainActivity.getBottles().remove(bottle.getId());
                BottlesFragment.getAdapter().notifyDataSetChanged();
            }
            finish();
        });
    }
}