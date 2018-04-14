package com.example.turner.carbonchainpos_v10;


import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.verifone.commerce.CommerceAppMessage;
import com.verifone.commerce.CommerceMessage;
import com.verifone.commerce.Status;
import com.verifone.commerce.entities.Basket;
import com.verifone.commerce.entities.Payment;
import com.verifone.commerce.payment.BasketManager;
import com.verifone.commerce.payment.DirectResponse;
import com.verifone.commerce.payment.PaymentCompletedEvent;
import com.verifone.commerce.payment.TransactionEvent;
import com.verifone.commerce.payment.TransactionManager;
import com.verifone.commerce.payment.reports.ReportManager;
import com.verifone.commerce.triggers.CommerceTrigger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView output;
    TextView cost;
    static Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cart = new Cart();

        Product beverages[] =
                {
                        new Product("Beverage 1", 5.20),
                        new Product("Beverage 2", 9.30),
                        new Product("Beverage 3", 8.13),
                        new Product("Beverage 4", 5.42),
                        new Product("Beverage 5", 3.99),
                };

        Product appetizers[] =
                {
                        new Product("Appetizer 1", 5.20),
                        new Product("Appetizer 2", 9.30),
                        new Product("Appetizer 3", 8.13),
                        new Product("Appetizer 4", 5.42),
                        new Product("Appetizer 5", 3.99),
                };

        Product entrees[] =
                {
                        new Product("Entree 1", 5.20),
                        new Product("Entree 2", 9.30),
                        new Product("Entree 3", 8.13),
                        new Product("Entree 4", 5.42),
                        new Product("Entree 5", 3.99),
                };


        Button beverage1 = (Button)findViewById(R.id.beverage1);
        beverage1.setText(beverages[0].getName() + "\t $" + beverages[0].getValue());
        beverage1.setTag(beverages[0]);
        beverage1.setOnClickListener(this);

        Button beverage2 = (Button)findViewById(R.id.beverage2);
        beverage2.setText(beverages[1].getName() + "\t $" + beverages[1].getValue());
        beverage2.setTag(beverages[1]);
        beverage2.setOnClickListener(this);

        Button beverage3 = (Button)findViewById(R.id.beverage3);
        beverage3.setText(beverages[2].getName() + "\t $" + beverages[2].getValue());
        beverage3.setTag(beverages[2]);
        beverage3.setOnClickListener(this);

        Button beverage4 = (Button)findViewById(R.id.beverage4);
        beverage4.setText(beverages[3].getName() + "\t $" + beverages[3].getValue());
        beverage4.setTag(beverages[3]);
        beverage4.setOnClickListener(this);

        Button beverage5 = (Button)findViewById(R.id.beverage5);
        beverage5.setText(beverages[4].getName() + "\t $" + beverages[4].getValue());
        beverage5.setTag(beverages[4]);
        beverage5.setOnClickListener(this);

        //Appetizer Buttons to set Text, Value, onClick
        Button appetizer1 = (Button)findViewById(R.id.appetizer1);
        appetizer1.setText(appetizers[0].getName() + "\t $" + appetizers[0].getValue());
        appetizer1.setTag(appetizers[0]);
        appetizer1.setOnClickListener(this);

        Button appetizer2 = (Button)findViewById(R.id.appetizer2);
        appetizer2.setText(appetizers[1].getName() + "\t $" + appetizers[1].getValue());
        appetizer2.setTag(appetizers[1]);
        appetizer2.setOnClickListener(this);

        Button appetizer3 = (Button)findViewById(R.id.appetizer3);
        appetizer3.setText(appetizers[2].getName() + "\t $" + appetizers[2].getValue());
        appetizer3.setTag(appetizers[2]);
        appetizer3.setOnClickListener(this);

        Button appetizer4 = (Button)findViewById(R.id.appetizer4);
        appetizer4.setText(appetizers[3].getName() + "\t $" + appetizers[3].getValue());
        appetizer4.setTag(appetizers[3]);
        appetizer4.setOnClickListener(this);

        Button appetizer5 = (Button)findViewById(R.id.appetizer5);
        appetizer5.setText(appetizers[4].getName() + "\t $" + appetizers[4].getValue());
        appetizer5.setTag(appetizers[4]);
        appetizer5.setOnClickListener(this);

        //Entrees Buttons to set Text, Value, onClick
        Button entree1 = (Button)findViewById(R.id.entree1);
        entree1.setText(entrees[0].getName() + "\t\t $" + entrees[0].getValue());
        entree1.setTag(entrees[1]);
        entree1.setOnClickListener(this);

        Button entree2 = (Button)findViewById(R.id.entree2);
        entree2.setText(entrees[1].getName() + "\t\t $" + entrees[1].getValue());
        entree2.setTag(entrees[1]);
        entree2.setOnClickListener(this);

        Button entree3 = (Button)findViewById(R.id.entree3);
        entree3.setText(entrees[2].getName() + "\t\t $" + entrees[2].getValue());
        entree3.setTag(entrees[2]);
        entree3.setOnClickListener(this);

        Button entree4 = (Button)findViewById(R.id.entree4);
        entree4.setText(entrees[3].getName() + "\t\t $" + entrees[3].getValue());
        entree4.setTag(entrees[3]);
        entree4.setOnClickListener(this);

        Button entree5 = (Button)findViewById(R.id.entree5);
        entree5.setText(entrees[4].getName() + "\t\t $" + entrees[4].getValue());
        entree5.setTag(entrees[4]);
        entree5.setOnClickListener(this);

    }

    public void onClick(View view) {
        Button button = (Button) view;
        Product product = (Product) button.getTag();
        cost = (TextView)findViewById(R.id.totalCost);
        output = (TextView)findViewById(R.id.listOutput);
        Payment pmnt = new Payment();
        Basket bsk = new Basket();

        pmnt.setTimestamp("timestamp");
        pmnt.getTimestamp();
        cart.addToCart(product);
        output.setText(output.getText() + "\n" + product.getName() + "\t\t\t" + product.getValue());
        cost.setText("$ "  + String.format("%.2f", cart.getValue()));


        CommerceTrigger ct;
        Intent intent = new Intent();
        CommerceMessage.CommerceMessageBuilder cmb = new CommerceMessage.CommerceMessageBuilder();
        CommerceMessage cm = new CommerceMessage() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getHandle() {
                return null;
            }

            @Override
            public String getMessageType() {
                return null;
            }

            @Override
            public boolean isTypeOfMessage(String s) {
                return false;
            }

            @Override
            public boolean put(String s, Object o) {
                return false;
            }

            @Override
            public Object get(String s) {
                return null;
            }

            @Override
            public String[] getKeys() {
                return new String[0];
            }

            @Override
            public Object[] getValues() {
                return new Object[0];
            }

            @Override
            public Status send() {
                return null;
            }
        };

        cm.send();

        //BasketManager
    }

    public void reset(View view){
        output.setText("");
        cost.setText("");
        cart.empty();
    }
}
