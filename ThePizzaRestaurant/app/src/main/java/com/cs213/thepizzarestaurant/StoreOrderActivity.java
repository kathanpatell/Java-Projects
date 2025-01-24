package com.cs213.thepizzarestaurant;

import static com.cs213.thepizzarestaurant.MainActivity.currOrder;
import static com.cs213.thepizzarestaurant.MainActivity.storeOrders;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.cs213.thepizzarestaurant.models.Order;

import java.util.ArrayList;

public class StoreOrderActivity extends AppCompatActivity
{
    private ArrayAdapter adapter1;
    private static ListView my_store_orders;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);

        ArrayList<Order> o = storeOrders.getOrders();
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i< o.size();i++)
        {
            temp.add(o.get(i).toString());
        }
        adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, temp);
        my_store_orders = findViewById(R.id.storeOrderListView);
        my_store_orders.setAdapter(adapter1);
        my_store_orders.setOnItemClickListener((AdapterView.OnItemClickListener) new My_Store_Orders());
    }

    class My_Store_Orders implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            //Toast.makeText(getApplicationContext(), "phone_number_string", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alert = new AlertDialog.Builder(StoreOrderActivity.this);
            alert.setMessage("Remove the Order from Register").setTitle("Manage Store Orders");
            alert.setPositiveButton("Remove the Order", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    //displayPrice.setText(listAvailableToppings.getItemAtPosition(position).toString());
                    //Toast.makeText(getApplicationContext(), listAvailableToppings.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    String selected_order = my_store_orders.getItemAtPosition(position).toString();
                    adapter1.remove(selected_order);
                    my_store_orders.setAdapter(adapter1);
                    remove_order_from_store_order(selected_order);
                    Toast.makeText(getApplicationContext(), R.string.store_order_removed_message, Toast.LENGTH_SHORT).show();
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

    private void remove_order_from_store_order(String row)
    {
        if (row == null)
            return;
        String[] tokens = row.split(" ");
        int orderIdToRemove = Integer.parseInt(tokens[2]);

        storeOrders.removeOrder(orderIdToRemove);
    }
}

