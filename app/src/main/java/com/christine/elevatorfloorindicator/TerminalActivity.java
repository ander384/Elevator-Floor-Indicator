package com.christine.elevatorfloorindicator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class TerminalActivity extends AppCompatActivity {

    MediaPlayer correctEntry2;

    TextView inputLetterTextView;

    TextView filePathTextView;

    TextView mainMenuTextView;
    TextView A1TextView;
    TextView A2TextView;
    TextView A3TextView;
    TextView B1TextView;

    TextView incorrectSelectionTextView;
    TextView grossRecipeTextView;
    TextView franksDiaryTextView;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal);


        mainMenuTextView = findViewById(R.id.tv_main_menu);
        inputLetterTextView = findViewById(R.id.tv_input_letter);

        filePathTextView=findViewById(R.id.tv_file_path);

        A1TextView =findViewById(R.id.tv_A1);
        A2TextView = findViewById(R.id.tv_A2);
        A3TextView = findViewById(R.id.tv_A3);
        B1TextView= findViewById(R.id.tv_B1);

        incorrectSelectionTextView = findViewById(R.id.tv_incorrect_entry);
        grossRecipeTextView = findViewById(R.id.tv_gross_recipe);
        franksDiaryTextView = findViewById(R.id.tv_franks_diary);

        correctEntry2 = MediaPlayer.create(this,R.raw.success_sound);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent intent = getIntent();
        b = intent.getExtras();
;    }

    public void onClickLetter(View view) {
        Button button = (Button) view;
        String letterPressed = (String) button.getText();
        inputLetterTextView.setText(letterPressed);
    }

    public void onClickEnter(View view) {
        String letterSelected = (String) inputLetterTextView.getText();
        inputLetterTextView.setText("");
        if(mainMenuTextView.getVisibility()==View.VISIBLE){
            switch (letterSelected) {
                case "A": //if they select A, make A1 visible
                    mainMenuTextView.setVisibility(View.INVISIBLE);
                    A1TextView.setVisibility(View.VISIBLE);
                    filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\Model_Number\\>");
                    break;
                case "B":
                    mainMenuTextView.setVisibility(View.INVISIBLE);
                    B1TextView.setVisibility(View.VISIBLE);
                    filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\Favorite_Recipes\\>");
                    break;
                case "C":
                    mainMenuTextView.setVisibility(View.INVISIBLE);
                    franksDiaryTextView.setVisibility(View.VISIBLE);
                    filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\Nice_Try\\>");
                    break;
                case "D": //EXIT button has been selected
                    this.finish();
                    break;
            }
        }

        //BEGIN A SEQUENCE
        else if(A1TextView.getVisibility()==View.VISIBLE){
            if(letterSelected.equals("G")){//GO BACK has been selected
                A1TextView.setVisibility(View.INVISIBLE);
                mainMenuTextView.setVisibility(View.VISIBLE);
                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\>");
            }
            else if(letterSelected.equals("D")){//Correct Answer is D, so move on to A2
                A1TextView.setVisibility(View.INVISIBLE);
                A2TextView.setVisibility(View.VISIBLE);
                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\Model_Number\\Floors_Serviced\\>");
            }
            else{//incorrect answer
                A1TextView.setVisibility(View.INVISIBLE);
                incorrectSelectionTextView.setVisibility(View.VISIBLE);
                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\Model_Number\\Floors_Serviced\\Incorrect_Selection>");
            }
        }

        else if(A2TextView.getVisibility()==View.VISIBLE){
            if(letterSelected.equals("G")){//GO BACK has been selected, go back to A1
                A2TextView.setVisibility(View.INVISIBLE);
                A1TextView.setVisibility(View.VISIBLE);
                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\Model_Number\\>");
            }
            else if(letterSelected.equals("E")){//Correct Answer is F, so move on to A3
                A2TextView.setVisibility(View.INVISIBLE);
                A3TextView.setVisibility(View.VISIBLE);
                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\Model_Number\\Floors_Serviced\\Grandmas_Recipe>");
            }
            else{//incorrect answer
                A2TextView.setVisibility(View.INVISIBLE);
                incorrectSelectionTextView.setVisibility(View.VISIBLE);
                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\Model_Number\\Floors_Serviced\\Grandmas_Recipe\\Incorrect_Selection\\>");
            }
        }

        else if(A3TextView.getVisibility()==View.VISIBLE){
            if(letterSelected.equals("G")){//GO BACK has been selected, go back to A2
                A3TextView.setVisibility(View.INVISIBLE);
                A2TextView.setVisibility(View.VISIBLE);

                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\Model_Number\\Floors_Serviced\\>");
            }
            else if(letterSelected.equals("C")){
                correctEntry2.start();
                Intent i = new Intent(TerminalActivity.this, ControllingActivity.class);
                i.putExtras(b);
                this.startActivity(i);
            }
        }

        else if(incorrectSelectionTextView.getVisibility()==View.VISIBLE){
            if(!letterSelected.equals("")){//as long as they selected a letter, go to the main menu
                incorrectSelectionTextView.setVisibility(View.INVISIBLE);
                mainMenuTextView.setVisibility(View.VISIBLE);
                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\>");
            }
        }

        //END A SEQUENCE

        //BEGIN B SEQUENCE
        else if(B1TextView.getVisibility()==View.VISIBLE){
            if(letterSelected.equals("G")){
                mainMenuTextView.setVisibility(View.VISIBLE);
                B1TextView.setVisibility(View.INVISIBLE);
                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\>");
            }
            else if(letterSelected.equals("")){
                //do nothing
            }
            else{
                grossRecipeTextView.setVisibility(View.VISIBLE);
                B1TextView.setVisibility(View.INVISIBLE);
                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\Favorite_Recipes\\>");
            }
        }

        else if(grossRecipeTextView.getVisibility()==View.VISIBLE){
            if(!letterSelected.equals("")){//as long as they selected a letter, go to the main menu
                grossRecipeTextView.setVisibility(View.INVISIBLE);
                mainMenuTextView.setVisibility(View.VISIBLE);
                inputLetterTextView.setText("");
                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\>");
            }
        }
        //END B SEQUENCEE

        //BEGIN C SEQUENCE
        else if(franksDiaryTextView.getVisibility()==View.VISIBLE){
            if(!letterSelected.equals("")){//as long as they selected a letter, go to the main menu
                franksDiaryTextView.setVisibility(View.INVISIBLE);
                mainMenuTextView.setVisibility(View.VISIBLE);
                inputLetterTextView.setText("");
                filePathTextView.setText("C:\\Users\\Praevado_Hotel_Staff\\Maintenance_Team\\Frank_Robinson\\>");
            }
        }
        //END C SEQUENCE


    }

    public void onClickClear(View view) {
        inputLetterTextView.setText("");
    }
}
