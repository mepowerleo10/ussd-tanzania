package com.trojan.tony.ussdassistant;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Recharge extends AppCompatActivity {
    private EditText pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Button recharge = (Button) findViewById(R.id.rechargeBtn);
        pin = (EditText)findViewById(R.id.pinEt);

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                RechargeActivity();
            }
        });

    }


    private void RechargeActivity(){

        String rail = Uri.encode("#");
        String pin_no = pin.getText().toString();

        if(TextUtils.isEmpty(pin_no)) {
            Toast.makeText(this, "Please type your Voucher PIN", Toast.LENGTH_SHORT).show();
        }
        else if (pin_no.length() < 12)
            Toast.makeText(this, "Your Voucher PIN cannot have less than 12 digits", Toast.LENGTH_SHORT).show();

        else {
            Intent dialerIntent = new Intent(Intent.ACTION_CALL);
            dialerIntent.setData(Uri.parse("tel:*104*" + pin_no + rail));
            startActivity(dialerIntent);

        }

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
