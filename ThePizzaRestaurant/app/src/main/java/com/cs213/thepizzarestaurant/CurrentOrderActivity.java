package com.cs213.thepizzarestaurant;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static com.cs213.thepizzarestaurant.MainActivity.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CurrentOrderActivity extends AppCompatActivity
{
    Button place_order_button;
    private TextView subTotalPrice;
    private TextView TaxToPay;
    private TextView FinalPrice;
    private ArrayAdapter adapter1;
    private static ListView my_current_order_pizzas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        place_order_button = findViewById(R.id.addOrderButton);
        place_order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeOrders.addOrder(currOrder);
                currOrder = null;
                Toast.makeText(getApplicationContext(), R.string.blah_blah, Toast.LENGTH_SHORT).show();

            }
        });

        subTotalPrice = findViewById(R.id.subTotalText);
        TaxToPay = findViewById(R.id.taxText);
        FinalPrice = findViewById(R.id.finalAmountText);

        calculateCost();

        adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, currOrder.getPizzasOrdered());
        my_current_order_pizzas = findViewById(R.id.listOfPizzaView);
        my_current_order_pizzas.setAdapter(adapter1);
        my_current_order_pizzas.setOnItemClickListener((AdapterView.OnItemClickListener) new My_Current_Order());
    }

    class My_Current_Order implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            //Toast.makeText(getApplicationContext(), "phone_number_string", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alert = new AlertDialog.Builder(CurrentOrderActivity.this);
            alert.setMessage("Remove the Selected Pizza").setTitle("Manage Cart");
            alert.setPositiveButton("Remove from Cart", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    //displayPrice.setText(listAvailableToppings.getItemAtPosition(position).toString());
                    //Toast.makeText(getApplicationContext(), listAvailableToppings.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    String selected_pizza = my_current_order_pizzas.getItemAtPosition(position).toString();
                    adapter1.remove(selected_pizza);
                    my_current_order_pizzas.setAdapter(adapter1);
                    currOrder.removeFromOrder(position);
                    calculateCost();
                    Toast.makeText(getApplicationContext(), R.string.removed_from_cart_message, Toast.LENGTH_SHORT).show();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                }
            });

            AlertDialog dialog = alert.create();
            dialog.show();
        }
    }

    /**
     * display the cost related to the order
     */
    private void calculateCost() {
        if (currOrder == null)
            return;
        double netOrderCost = currOrder.orderCost();
        subTotalPrice.setText(String.format("%,.2f", netOrderCost));
        double taxes = (6.625 / 100) * netOrderCost;
        TaxToPay.setText(String.format("%,.2f", taxes));
        double total = taxes + netOrderCost;
        FinalPrice.setText(String.format("%,.2f", total));
    }
}

