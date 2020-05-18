package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int qty =2;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText et=(EditText)findViewById(R.id.name_field);
        String name=et.getText().toString();

        CheckBox ch1=(CheckBox)findViewById(R.id.chk_box_whippedCream);
        Boolean chk=ch1.isChecked();

        CheckBox ch=(CheckBox)findViewById(R.id.chk_box_chocolate);
        Boolean chk1=ch.isChecked();

        int price =calculatePrice(chk,chk1);

        String priceMeassage = createOrderSummary(name,price,chk,chk1);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Oder for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMeassage);

        if(intent.resolveActivity(getPackageManager()) != null);
        {
            startActivity(intent);
        }

    }
    private int calculatePrice(boolean chk,boolean chk1)
    {
        int price=5;
        if(chk)
        {
            price+=1;
        }
        if(chk1)
        {
            price+=2;
        }
        return qty*price;
    }
    private String createOrderSummary(String nameofuser,int price,boolean chk,boolean chk1)
    {
        String priceMsg = "Name:"+nameofuser;
        priceMsg="Add Whipped Cream?"+chk;
        priceMsg="Add Chocolate?"+chk1;
        priceMsg=priceMsg+"\nQuantity"+qty;
        priceMsg =priceMsg+"\nTotal: $"+price;
        priceMsg = priceMsg+"\nThank You!";
        return priceMsg;
    }
    /**
     * This method displays the price value on the screen.
     */


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view){
        if(qty==100)
        {
            Toast.makeText(this,"You Cannot Order more than 100 coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        qty=qty+1;
        display(qty);
    }
    public void decrement(View view)
    {
        if(qty==1)
        {
            Toast.makeText(this,"You Cannot Order less than 1 coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        qty=qty-1;
        display(qty);
    }

}
