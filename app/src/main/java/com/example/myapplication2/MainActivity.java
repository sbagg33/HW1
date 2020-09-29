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

    public void quit ( View view){
        //setContentView(R.layout.activity_main);
        finishAffinity();
        System.exit(0);
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

        iterationsText.setVisibility(view.INVISIBLE); // Make Iteration scroll bar Invisible when starting after sort press



        textView.setText(message); //set the text to show input text
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
            sortedListText.setVisibility(view.INVISIBLE); // make text visible
        }

        //Check method return and update text for size checking 3-8
        if(sizeLimitCheck(numArray)){
            sizeText.setText("True");
        } else {
            Toast.makeText(context, "Error: Please enter 3 to 8 digits, no more no less", duration).show();
            sizeText.setText("False");
            sortedListText.setVisibility(view.INVISIBLE); // make text visible
        }

        //send success toast on valid input
        if(valueCheck(numArray) && sizeLimitCheck(numArray)){
            Toast.makeText(context, "Congratulations! Valid Input", duration).show(); //valid input toast

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
            sortedListText.setText(Arrays.toString(numbersIntArray)); //set the text to show input text
        } else {
            sortedListText.setVisibility(view.INVISIBLE); // make text visible
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
        int sIndex = 0;
        int nValues = numbers.length;
        int eIndex = nValues - 1;
        boolean firstLine = true;
        int curIteration = 0; //used for underlining first line of iteration
        int indexSize = (numbers.length * 2) - 1;
        int curIterationEndNumber = 1;

        while (sIndex < eIndex){

             int rightIndex = indexSize;
             int leftIndex = indexSize - 3;

             //if its the first row in an iteration
            if(firstLine == true){
                firstLine = false;
                String topLine = cleanLine(numbers);

                SpannableString content = new SpannableString(topLine);
                //add styles to top line
                content.setSpan(new UnderlineSpan(),curIteration,indexSize, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                content.setSpan(new ForegroundColorSpan(Color.RED),0,curIteration, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                content.setSpan(new StyleSpan(Typeface.BOLD),0,curIteration, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                //display top row
                curIteration = curIteration + 2;
                iterationsText.append(content);
                iterationsText.append("\n");

            }

            //display and style 2nd row
            String otherLine = cleanLine(numbers);
            SpannableString secondLine = new SpannableString(otherLine);
            secondLine.setSpan(new UnderlineSpan(),leftIndex,rightIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            secondLine.setSpan(new ForegroundColorSpan(Color.BLACK),rightIndex-1,rightIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            secondLine.setSpan(new StyleSpan(Typeface.BOLD),rightIndex-1,rightIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            iterationsText.append(secondLine); //Second line
            iterationsText.append("\n");

            // go through bubble sort algorithm
            for (int index = eIndex; index > sIndex; index--){
                if (numbers[index] < numbers[index -1]) {
                    //iterationsText.append(Arrays.toString(numbers) + "\n");
                    swap(numbers, index, index - 1);
                }
                rightIndex = leftIndex + 1;
                leftIndex = leftIndex -2;

                String curLine = cleanLine(numbers);
                SpannableString middleLines = new SpannableString(curLine);

                //add underline and black bold font
                if(leftIndex >= 0) {
                    middleLines.setSpan(new UnderlineSpan(), leftIndex, rightIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    middleLines.setSpan(new ForegroundColorSpan(Color.BLACK),rightIndex-1,rightIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    middleLines.setSpan(new StyleSpan(Typeface.BOLD),rightIndex-1,rightIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                //if it's the last Line in the iteration style red and bold  for sorted part
                if(curIterationEndNumber > 1 && curIterationEndNumber == index) {
                    middleLines.setSpan(new ForegroundColorSpan(Color.RED), 0, rightIndex-2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    middleLines.setSpan(new StyleSpan(Typeface.BOLD), 0, rightIndex-2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                //bold last row number for each iteration
                if(curIterationEndNumber == 1 && leftIndex < 0){
                    middleLines.setSpan(new ForegroundColorSpan(Color.BLACK),0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    middleLines.setSpan(new StyleSpan(Typeface.BOLD),0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                iterationsText.append(middleLines); //lines after the first line
                iterationsText.append("\n");

            }

            //add iteration number after every iteration
            String endLine = "Iteration "+ curIterationEndNumber +" Ended";
            curIterationEndNumber++;
            System.out.println(curIterationEndNumber);
            iterationsText.append(endLine +"\n\n");
            firstLine = true;
            sIndex++;
        }

        //show final sorted array in bold Red
        String finalLine = cleanLine(numbers);
        SpannableString lastContent = new SpannableString(finalLine);
        lastContent.setSpan(new ForegroundColorSpan(Color.RED),0,indexSize, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        lastContent.setSpan(new StyleSpan(Typeface.BOLD),0,indexSize, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        iterationsText.append("Sorted Array");
        iterationsText.append("\n");
        iterationsText.append(lastContent);
        return numbers;
        //System.out.println("Solution: %s", Arrays.toString(arr)); //bring to front mobile
    }

    //helper function to transform array to strings from ints
    public String cleanLine (int[] numbers) {
        String curString = Arrays.toString(numbers)
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
        return curString;
    }
}