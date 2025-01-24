package com.cs213.thepizzarestaurant;

import static com.cs213.thepizzarestaurant.MainActivity.currOrder;
import com.cs213.thepizzarestaurant.models.*;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.cs213.thepizzarestaurant.models.Pizza;
import com.cs213.thepizzarestaurant.models.PizzaMaker;

import java.util.ArrayList;

public class ModifyPizzaActivity extends AppCompatActivity
{
    Button place_my_order_button;
    public static Pizza currentPizza;
    private TextView displayPrice;
    private ArrayList<String> availableToppings = new ArrayList<>();
    private ArrayList<String> addedToppings = new ArrayList<>();
    private ArrayAdapter adapter1, adapter2;
    private static ListView listAvailableToppings;
    private static ListView listAddedToppings;
    private static ArrayAdapter<CharSequence> adapter;
    private Spinner spinner1;
    private static Intent intent;
    //private Button addPizzaToOrderButton;

    @Override
    public void onBackPressed(){
        Intent intentBack = new Intent();
        intentBack.putExtra("PIZZA", currentPizza);
        setResult(Activity.RESULT_OK, intentBack);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pizza_activity);

       // addPizzaToOrderButton = findViewById(R.id.addOrderButton);
        spinner1 = findViewById(R.id.sizeSpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.pizzaSize_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        populateAvailableToppings(availableToppings);

        intent = getIntent();
        String flavor = intent.getStringExtra("TYPE");

        updateTheArrays(availableToppings, addedToppings, flavor);

        adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, availableToppings);
        adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, addedToppings);

        listAvailableToppings = findViewById(R.id.availableToppingsListView);
        listAddedToppings = findViewById(R.id.selectedToppingsListView);

        listAvailableToppings.setAdapter(adapter1);
        listAddedToppings.setAdapter(adapter2);

        currentPizza = PizzaMaker.createPizza(flavor);

        displayPrice = findViewById(R.id.currPizzaPriceText);
        displayPrice.setText(String.format("%,.2f", currentPizza.price()));

        listAvailableToppings.setOnItemClickListener((AdapterView.OnItemClickListener) new AvailableToppingsListView());
        listAddedToppings.setOnItemClickListener((AdapterView.OnItemClickListener) new AddedToppingsListView());
        spinner1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) new SizeSpinner());
        //addPizzaToOrderButton.setOnClickListener((AdapterView.OnClickListener) new AddToOrder());

        place_my_order_button = findViewById(R.id.addPizzaToOrderButton);
        place_my_order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currOrder.addToOrder(currentPizza);
                Toast.makeText(getApplicationContext(), R.string.add_to_order_message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class AvailableToppingsListView implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            //Toast.makeText(getApplicationContext(), "phone_number_string", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alert = new AlertDialog.Builder(ModifyPizzaActivity.this);
            alert.setMessage("Add the Clicked Topping").setTitle("Manage Toppings");
            alert.setPositiveButton("Add Topping", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    //displayPrice.setText(listAvailableToppings.getItemAtPosition(position).toString());
                    //Toast.makeText(getApplicationContext(), listAvailableToppings.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    String selected_topping = listAvailableToppings.getItemAtPosition(position).toString();
                    addedToppings.add(selected_topping);
                    availableToppings.remove(selected_topping);
                    adapter1.remove(selected_topping);
                    adapter2.add(selected_topping);
                    listAvailableToppings.setAdapter(adapter1);
                    listAddedToppings.setAdapter(adapter2);
                    currentPizza.addTopping(selected_topping);
                    displayPrice.setText(String.format("%,.2f", currentPizza.price()));
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

    class AddedToppingsListView implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            //Toast.makeText(getApplicationContext(), "phone_number_string", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alert = new AlertDialog.Builder(ModifyPizzaActivity.this);
            alert.setMessage("Remove the Clicked Topping").setTitle("Manage Toppings");
            alert.setPositiveButton("Remove Topping", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    //displayPrice.setText(listAvailableToppings.getItemAtPosition(position).toString());
                    //Toast.makeText(getApplicationContext(), listAvailableToppings.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    String selected_topping = listAddedToppings.getItemAtPosition(position).toString();
                    addedToppings.remove(selected_topping);
                    availableToppings.add(selected_topping);
                    adapter1.add(selected_topping);
                    adapter2.remove(selected_topping);
                    listAvailableToppings.setAdapter(adapter1);
                    listAddedToppings.setAdapter(adapter2);
                    currentPizza.removeTopping(selected_topping);
                    displayPrice.setText(String.format("%,.2f", currentPizza.price()));
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

    class SizeSpinner implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
        {
            String text = spinner1.getItemAtPosition(pos).toString();
            currentPizza.setSize(text);
            displayPrice.setText(String.format("%,.2f", currentPizza.price()));
        }

        public void onNothingSelected(AdapterView<?> parent)
        {

        }
    }

    private static void populateAvailableToppings(ArrayList<String> availableToppings)
    {
        availableToppings.add("Pepperoni");
        availableToppings.add("Ham");
        availableToppings.add("Pineapple");
        availableToppings.add("Onion");
        availableToppings.add("Pepper");
        availableToppings.add("Olives");
        availableToppings.add("Chicken");
        availableToppings.add("Sausage");
        availableToppings.add("Beef");
        availableToppings.add("Mushroom");
    }

    private static void updateTheArrays(ArrayList<String> availableToppings, ArrayList<String> addedToppings, String flavor)
    {
        if (flavor.equals("Deluxe")) {
            availableToppings.remove("Chicken");
            availableToppings.remove("Pepper");
            availableToppings.remove("Ham");
            availableToppings.remove("Olives");
            availableToppings.remove("Onion");
            addedToppings.add("Chicken");
            addedToppings.add("Pepper");
            addedToppings.add("Ham");
            addedToppings.add("Olives");
            addedToppings.add("Onion");
        } else if (flavor.equals("Hawaiian")) {
            availableToppings.remove("Pineapple");
            availableToppings.remove("Ham");
            addedToppings.add("Pineapple");
            addedToppings.add("Ham");
        } else if (flavor.equals("Pepperoni")) {
            availableToppings.remove("Pepperoni");
            addedToppings.add("Pepperoni");
        }
    }
}

