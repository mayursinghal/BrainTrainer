package com.example.mayursinghal.braintrainer;

import android.opengl.Visibility;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button myButton1;
    Button myButton2;
    Button myButton3;
    Button myButton4;
    Button playButton;
    TextView someTextView;
    TextView correctTextView;
    TextView scoreTextView;
    TextView timerTextView;
    RelativeLayout RelativeGameLayout;
    int locationCorrectAnswer;
    int score=0;
    int noOfQuestion=0;
    CountDownTimer countDownTimer;
    ArrayList<Integer> answer=new ArrayList<Integer>();

    public void generateQuestion(){
        Random random=new Random();
        int a=random.nextInt(40)+9;
        int b =random.nextInt(40)+9;
        someTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationCorrectAnswer=random.nextInt(4);
            answer.clear();
        int incorrectAnswer;
        for(int i=0;i<4;i++) {
            if (locationCorrectAnswer == i) {
                answer.add(a + b);
            } else {
                incorrectAnswer = random.nextInt(80)+18;
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = random.nextInt(80)+18;
                }
                answer.add(incorrectAnswer);
            }

        }
        myButton1.setText(Integer.toString(answer.get(0)));
        myButton2.setText(Integer.toString(answer.get(1)));
        myButton3.setText(Integer.toString(answer.get(2)));
        myButton4.setText(Integer.toString(answer.get(3)));
    }


    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationCorrectAnswer))){
            score++;
            correctTextView.setText("Correct!!");
        }
        else {
            correctTextView.setText("Wrong!!");
        }
        noOfQuestion++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestion));
        generateQuestion();
    }

    public void start(View view){
        startButton.setVisibility(view.INVISIBLE);
        RelativeGameLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playButton));

    }

    public void playAgain(View view){
        score=0;
        noOfQuestion=0;
        timerTextView.setText("30s");
        correctTextView.setText("");
        scoreTextView.setText("0/0");
        playButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        myButton1.setEnabled(true);
        myButton2.setEnabled(true);
        myButton3.setEnabled(true);
        myButton4.setEnabled(true);
        correctTextView.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                playButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                correctTextView.setText("Your Score is :"+Integer.toString(score)+"/"+Integer.toString(noOfQuestion));
                correctTextView.setVisibility(View.VISIBLE);
                myButton1.setEnabled(false);
                myButton2.setEnabled(false);
                myButton3.setEnabled(false);
                myButton4.setEnabled(false);
            }
        }.start();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.startButton);

        someTextView =(TextView)findViewById(R.id.someTextView);
        correctTextView =(TextView)findViewById(R.id.correctTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        myButton1=(Button)findViewById(R.id.myButton1);
        myButton2=(Button)findViewById(R.id.myButton2);
        myButton3=(Button)findViewById(R.id.myButton3);
        myButton4=(Button)findViewById(R.id.myButton4);
        playButton=(Button)findViewById(R.id.playButton);
        RelativeGameLayout=(RelativeLayout)findViewById(R.id.RelativeGameLayout);




    }
}
