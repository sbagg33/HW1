package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static String numArray[]; //array to hold input
    public static int numbersIntArray[]; //arrayt to hold integers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //called when the user taps the send button
    public void sendMessage( View view) {

        EditText editText = (EditText) findViewById(R.id.editText); // get text from input
        String message = editText.getText().toString(); //convert to string

        TextView textView = findViewById(R.id.textView2); //get view for output text view
        TextView valuesText = findViewById(R.id.digitValueBoolText); //get view for value text view
        TextView sizeText = findViewById(R.id.sizeBoolText); //get view for value text view
        TextView sortedListText = findViewById(R.id.sortedListFinal); //get view for value text view
        TextView iterationsText  = findViewById(R.id.iterationsText); //get view for output text view



        textView.setText(message); //set the text to show input text
       // textView.setVisibility(view.VISIBLE); // make text visible
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
            //sortedListText.setVisibility(view.INVISIBLE); // make text visible
        }

        //Check method return and update text for size checking 3-8
        if(sizeLimitCheck(numArray)){
            sizeText.setText("True");
        } else {
            Toast.makeText(context, "Error: Please enter 3 to 8 digits, no more no less", duration).show();
            sizeText.setText("False");
            sortedListText.setVisibility(view.INVISIBLE); // make text visible
        }
        //valuesText.setVisibility((view.VISIBLE));
        //sizeText.setVisibility((view.VISIBLE));

        //send success toast on valid input
        if(valueCheck(numArray) && sizeLimitCheck(numArray)){
            Toast.makeText(context, "Valid Input", duration).show(); //valid input toast

            //make string array into int array
            numbersIntArray = new int[numArray.length]; //integera array
            for(int i = 0;i < numArray.length;i++)
            {
                numbersIntArray[i] = Integer.parseInt(numArray[i]); //changing type to int and adding to new array
            }
            iterationsText.setVisibility(view.VISIBLE); // make text visible
            numbersIntArray = bubbleSort(numbersIntArray);
            System.out.println("Final answer below!");
            System.out.println(Arrays.toString(numbersIntArray));
            //print to screen
            sortedListText.setText(Arrays.toString(numbersIntArray)); //set the text to show input text
            //sortedListText.setVisibility(view.VISIBLE); // make text visible
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

    //swap those two numbers here
    public static void swap(int[] arr, int from, int to){
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
        //system.out.printf("Iteration %d: %s %n",swapNumber, Arrays.toString(arr)); //bring to front mobile
    }

    public int[] bubbleSort(int[] numbers){
        TextView iterationsText  = findViewById(R.id.iterationsText); //get view for output text view
        iterationsText.setText("");
        //Print the unsorted array
        //System.out.printf("Unsorted array: %s%n", Arrays.toString(numbers));
        int sIndex = 0;
        int nValues = numbers.length;
        int eIndex = nValues - 1;
        boolean firstLine = true;
        int curIteration = 0; //used for underlining first line of iteration
        int indexSize = (numbers.length * 2) - 1;
        //int curEndText = 12;

        while (sIndex < eIndex){

            if(firstLine == true){
                firstLine = false;
                String topLine = cleanLine(numbers);
                //System.out.println(topLine);
                SpannableString content = new SpannableString(topLine);
                System.out.println(content);
                content.setSpan(new UnderlineSpan(),curIteration,indexSize, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                System.out.println(curIteration);
                System.out.println(indexSize);
                curIteration = curIteration + 2;
                //iterationsText.setText(fullIterationsText + "\n");
                iterationsText.append(content);
                iterationsText.append("\n");

                //update values for next iteration
                //curEndTextIndex = curEndTextIndex + numbers.length;
                //break;
            }

            String otherLine = cleanLine(numbers);
            iterationsText.append(otherLine + "\n");

            for (int index = eIndex; index > sIndex; index--){
                if (numbers[index] < numbers[index -1]) {
                    //iterationsText.append(Arrays.toString(numbers) + "\n");
                    swap(numbers, index, index - 1);
                }
                //System.out.println(Arrays.toString(numbers));
                String curLine = cleanLine(numbers);

                iterationsText.append(curLine + "\n");
            }
            String endLine = "Iteration " + Integer.toString(curIteration) +" Ended";
//            SpannableString myContent = new SpannableString(endLine);
//            myContent.setSpan(new UnderlineSpan(), 0, endLine.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            iterationsText.append(endLine +"\n\n");
            firstLine = true;
            sIndex++;
            //break;
        }
        //System.out.println(Arrays.toString((numbers)));
        String finalLine = cleanLine(numbers);
        SpannableString lastContent = new SpannableString(finalLine);
        System.out.println(lastContent);
        lastContent.setSpan(new ForegroundColorSpan(Color.RED),0,indexSize, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        lastContent.setSpan(new StyleSpan(Typeface.BOLD),0,indexSize, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        iterationsText.append("Sorted Array");
        iterationsText.append("\n");
        iterationsText.append(lastContent);
        return numbers;
        //System.out.println("Solution: %s", Arrays.toString(arr)); //bring to front mobile
    }

    public String cleanLine (int[] numbers) {
        String curString = Arrays.toString(numbers)
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
        return curString;
    }
}