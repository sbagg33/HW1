package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static String numArray[]; //array to hold input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //called when the user taps the send button
    public void sendMessage( View view) {
       // Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText); // get text from input
        String message = editText.getText().toString(); //convert to string

        TextView textView = findViewById(R.id.textView2); //get view for output text view
        TextView valuesText = findViewById(R.id.digitValueBoolText); //get view for value text view
        TextView sizeText = findViewById(R.id.sizeBoolText); //get view for value text view

        textView.setText(message); //set the text to show input text
        textView.setVisibility(view.VISIBLE); // make text visible
        System.out.println(message);

        numArray = (message.split("\\s")); //split input string by spaces

        //setup toast attributes
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

       //Check method return and update text for value checking [0-9]
        if(valueCheck(numArray)) {
            valuesText.setText("True");
        } else {
            Toast.makeText(context, "Error: Please enter number values of 0-9 only", duration).show();
            valuesText.setText("False");
        }

        //Check method return and update text for size checking 3-8
        if(sizeLimitCheck(numArray)){
            sizeText.setText("True");
        } else {
            Toast.makeText(context, "Error: Please enter 3 to 8 digits, no more no less", duration).show();
            sizeText.setText("False");
        }
        valuesText.setVisibility((view.VISIBLE));
        sizeText.setVisibility((view.VISIBLE));

        //send success toast on valid input
        if(valueCheck(numArray) && sizeLimitCheck(numArray)){
            Toast.makeText(context, "Valid Input", duration).show();
        }
    }

    //helper function to check sizing limit
    public static boolean sizeLimitCheck(String[] numArray) {
        int arraySize = numArray.length; //get length of array
        if(arraySize < 3 || arraySize > 8) {  //size condition check
            return false; //does not meet sizing standards
        } else {
            return true;  //meets sizing standards
        }
    }

    //helper function to check input contains only digits 0-9
    public static boolean valueCheck(String[] numArray) {
        for(String index: numArray) {
            //System.out.println(index);
            //System.out.println(index.matches("[0-9]{1}"));
            if(!index.matches("[0-9]{1}")){ //if current number in array isnt a single digit between 0-9
                return false;
            }
        }
        return true; //all digit in array are digits between 0-9
    }
}