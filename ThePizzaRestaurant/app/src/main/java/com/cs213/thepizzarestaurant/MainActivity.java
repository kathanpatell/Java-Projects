package com.cs213.thepizzarestaurant;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cs213.thepizzarestaurant.models.*;
import static com.cs213.thepizzarestaurant.ModifyPizzaActivity.currentPizza;

public class MainActivity extends AppCompatActivity
{
    Button my_current_order_button;
    Button my_store_order_button;
    Button my_phone_number_enter_button;
    ImageButton my_modify_pizza_button;
    EditText my_phone_number;

    public static String phone_number_string = null;
    /**
     * Current Order
     */
    public static Order currOrder = null;
    /**
     * Store Orders
     */
    public static StoreOrders storeOrders = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storeOrders = new StoreOrders();
    }

    public void getPhoneNumber(View view)
    {
        my_phone_number = findViewById(R.id.phoneNum);
        my_phone_number_enter_button = findViewById(R.id.phoneNumButton);
        my_phone_number_enter_button.setOnClickListener(v -> {
            phone_number_string = my_phone_number.getText().toString();
            //System.out.println(phone_number_string);
                    if (isNumeric(phone_number_string)) {
                        currOrder = new Order(phone_number_string);
                        Toast.makeText(getApplicationContext(), phone_number_string, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        phone_number_string = null;
                        Toast.makeText(getApplicationContext(), R.string.invalid_phone_number, Toast.LENGTH_SHORT).show();
                    }


        });


    }




    public void currentOrder(View view)
    {
        if(currOrder != null) {
            my_current_order_button = findViewById(R.id.currentOrderButton);
            my_current_order_button.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CurrentOrderActivity.class)));
        }
    }

    public void storeOrders(View view)
    {
        if(storeOrders != null) {
            my_store_order_button = findViewById(R.id.storeOrderButton);
            my_store_order_button.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, StoreOrderActivity.class)));
        }
    }

    public void modifyPizza1(View view)
    {
        if (phone_number_string != null) {
            my_modify_pizza_button = findViewById(R.id.deluxeButton);
            Intent intent = new Intent(MainActivity.this, ModifyPizzaActivity.class);
            intent.putExtra("TYPE", "Deluxe");
            my_modify_pizza_button.setOnClickListener(v -> startActivityForResult(intent, 1));
        }
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Pizza pizza = (Pizza) data.getParcelableExtra("PIZZA");
            currOrder.addToOrder(pizza);
        }
    } */



    public void modifyPizza2(View view) {
        if (phone_number_string != null) {
            my_modify_pizza_button = findViewById(R.id.pepperoniButton);
            Intent intent = new Intent(MainActivity.this, ModifyPizzaActivity.class);
            intent.putExtra("TYPE", "Pepperoni");
            my_modify_pizza_button.setOnClickListener(v -> startActivity(intent));

        }
    }

    public void modifyPizza3(View view)
    {
        if (phone_number_string != null) {
            my_modify_pizza_button = findViewById(R.id.hawaiianButton);
            Intent intent = new Intent(MainActivity.this, ModifyPizzaActivity.class);
            intent.putExtra("TYPE", "Hawaiian");
            my_modify_pizza_button.setOnClickListener(v -> startActivity(intent));
        }
    }

    private boolean isNumeric(String str) {
        if (str == null)
            return false;
        if (str.length() != 10)
            return false;

        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

