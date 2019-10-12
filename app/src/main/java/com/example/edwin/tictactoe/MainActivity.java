package com.example.edwin.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button[] btns;
    private int counter;
    private int color;
    private int color2;
    private int numClicks;
    private static final String NAME = "name";
    private static final String NAME2 = "name2";
    private String player1;
    private String player2;
    private TextView tv_player1;
    private TextView tv_player2;
    final static String TEXT_COLOR = "Text Color";
    final static String CLICKABLE = "Clickable";
    final static String NUM_CLICKS = "Num Clicks";
    final static String TEXT = "Text";
    final static String COLOR = "color";
    final static String COLOR2 = "color2";
    final static String COUNTER = "counter";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent intent = getIntent();

        player1 = intent.getStringExtra(NAME);
        player2 = intent.getStringExtra(NAME2);

        tv_player1 = (TextView) findViewById(R.id.player1);
        tv_player2 = (TextView) findViewById(R.id.player2);

        tv_player1.setText(player1 + " It's your turn");
        tv_player2.setText(player2);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btns = new Button[9];
        int id = R.id.btn1;
        for (int i = 0; i < btns.length; i++) {
            btns[i] = (Button) findViewById(id);
            id++;
        }
        color = Color.BLACK;
        color2 = Color.BLACK;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem spinnerItem = menu.findItem(R.id.color_spinner);
        Spinner spinner = (Spinner) spinnerItem.getActionView();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //X Has to do his turn the change the color for the player to change it.
                /*
                This code here gets the position clicked by the user in the spinner
                it will then change all the x's to the color chosen.
                 */
                if (counter == 1) {
                    if (position == 0) {
                        color = Color.BLACK;
                    } else if (position == 1) {
                        color = Color.MAGENTA;
                    } else if (position == 2) {
                        color = Color.CYAN;
                    }
                    for (int i = 0; i < btns.length; i++) {
                        if (btns[i].getText().equals("X")) {
                            btns[i].setTextColor(color);
                        }
                    }
                }
                //O player has to already done his turn to be able to change his color.
                //He has to place his O down then change color.
                //This code does the same as above however it will be for the O player
                if (counter == 0) {
                    if (position == 0) {
                        color2 = Color.BLACK;
                    } else if (position == 1) {
                        color2 = Color.MAGENTA;
                    } else if (position == 2) {
                        color2 = Color.CYAN;
                    }
                    for (int i = 0; i < btns.length; i++) {
                        if (btns[i].getText().equals("O")) {
                            btns[i].setTextColor(color2);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
            //If the reset button is clicked in the action bar call reset method!
        } else if (id == R.id.action_reset) {
            reset();
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    @method: Reset()
    @Description: This will make every button empty with no text re-make it clickable set the counter back to 0
    and as well sets the text color back to black.
    @return: Return type is void
     */
    public void reset() {
        counter = 0;
        numClicks = 0;
        color = Color.BLACK;
        color2 = Color.BLACK;
        tv_player1.setText(player1 + " It's your turn");
        tv_player2.setText(player2);
        for (int i = 0; i < btns.length; i++) {
            btns[i].setClickable(true);
            btns[i].setText(" ");
            btns[i].setTextColor(Color.BLACK);
        }
    }

    /*
    @method Onclick(View view)
    @description: Whenever a player clicks this method is activated. It first finds the id of the button clicked it then
    starts a counter and makes the button unclickable again. It will then mark the button with an X or O depending on the counter
    it will also call our end game method after 4 clicks as that's when we enter the possability of winning a game
     @return: Return type is void
     */
    public void onClick(View view) {
        int id = view.getId();
        int index = id - R.id.btn1;
        counter++;
        numClicks++;
        btns[index].setClickable(false);



        if (counter == 1) {
            btns[index].setText("X");
            btns[index].setTextColor(color);
            tv_player1.setText(player1);
            tv_player2.setText(player2 + " It's your turn");


        } else if (counter == 2) {
            btns[index].setText("O");
            btns[index].setTextColor(color2);
            counter = 0;
            tv_player2.setText(player2);
            tv_player1.setText(player1 + " It's your turn");
        }
        if (numClicks > 4) {
            endGame();
        }
    }

    /*
    @method endGame
    Description: This First checks who's turn it is first and make a string for it later in the toast.
    It then goes through multiple if statements to check each possability of a winner. It also makes sure
    that the button was clicked if not it would count the space within the button as a letter and it would
    instantly find a match since there would be a lot of null spots. It then makes all other buttons unclickable
    @return: Return type is void
     */
    public void endGame() {
        String winner = " ";
        boolean finish = false;
        if (counter == 1) {
            winner = player1;
        } else if (counter == 0) {
            winner = player2;
        }
        if (btns[0].getText().equals(btns[1].getText()) && btns[1].getText().equals(btns[2].getText())
                && !btns[0].isClickable()) {
            finish = true;
        } else if (btns[3].getText().equals(btns[4].getText()) && btns[4].getText().equals(btns[5].getText())
                && !btns[3].isClickable()) {
            finish = true;
        } else if (btns[6].getText().equals(btns[7].getText()) && btns[7].getText().equals(btns[8].getText())
                && !btns[6].isClickable()) {
            finish = true;
        } else if (btns[0].getText().equals(btns[4].getText()) && btns[4].getText().equals(btns[8].getText())
                && !btns[0].isClickable()) {
            finish = true;
        } else if (btns[2].getText().equals(btns[4].getText()) && btns[4].getText().equals(btns[6].getText())
                && !btns[2].isClickable()) {
            finish = true;
        } else if (btns[0].getText().equals(btns[3].getText()) && btns[3].getText().equals(btns[6].getText())
                && !btns[0].isClickable()) {
            finish = true;
        } else if (btns[2].getText().equals(btns[5].getText()) && btns[5].getText().equals(btns[8].getText())
                && !btns[2].isClickable()) {
            finish = true;
        } else if ((btns[1].getText().equals(btns[4].getText()) && btns[4].getText().equals(btns[7].getText())
                && !btns[1].isClickable())) {
            finish = true;
        } else if (numClicks == btns.length && finish == false) {
            Toast.makeText(getApplicationContext(), "This Game is a DRAW!", Toast.LENGTH_LONG).show();
        }
        if (finish) {
            Toast.makeText(getApplicationContext(), winner + " WON THE GAME!", Toast.LENGTH_LONG).show();
            for (Button btn : btns) {
                btn.setClickable(false);
            }
            //Here is the alertdialog that happens once the program has finished to ask the user if
            //they want to play again or exit out.
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Do You Want To Play Again?....");

            String[] choices = {"Yes", "No"};

            builder.setItems(choices, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        counter = 0;
                        numClicks = 0;
                        tv_player1.setText(player1 + " It's your turn");
                        tv_player2.setText(player2);
                        for (int i = 0; i < btns.length; i++) {
                            btns[i].setClickable(true);
                            btns[i].setText(" ");
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Thank you for playing " + player1 + " and " + player2, Toast.LENGTH_LONG).show();

                    }
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }


    }

    /*
    @method onSaveInstanceState
    @return void
    @Description: The onSaveInstanceState method stores and saves all of the current state of the app
    such as text color if it's been clicked, what was in the buttons, and more.
     */
    public void onSaveInstanceState(Bundle outState) {
        int tColor, tColor2, dCounter;
        int[] textColor = new int[btns.length];
        String[] text = new String[btns.length];
        boolean[] clicked = new boolean[btns.length];
        tColor = color;
        tColor2 = color2;
        dCounter = counter;

        for (int i = 0; i < btns.length; i++) {
            textColor[i] = btns[i].getCurrentTextColor();
            text[i] = btns[i].getText().toString();
            clicked[i] = btns[i].isClickable();
        }
        outState.putInt(COUNTER, dCounter);
        outState.putInt(COLOR, tColor);
        outState.putInt(COLOR2, tColor2);
        outState.putIntArray(TEXT_COLOR, textColor);
        outState.putInt(NUM_CLICKS, numClicks);
        outState.putBooleanArray(CLICKABLE, clicked);
        outState.putStringArray(TEXT, text);
        super.onSaveInstanceState(outState);

    }
    /*
    @method onRestoreInstanceState
    @return type: void
    @description: The onRestoreInstanceState retrieves what we saved in the above method
    and places it back into the app to make sure everything is returned back to as it was before turning it.
     */
    public void onRestoreInstanceState(Bundle inState) {

        int[] textColor;
        boolean[] clicked;
        String[] text;

        super.onRestoreInstanceState(inState);
        counter = inState.getInt(COUNTER);
        color = inState.getInt(COLOR);
        color2 = inState.getInt(COLOR2);
        textColor = inState.getIntArray(TEXT_COLOR);
        numClicks = inState.getInt(NUM_CLICKS);
        clicked = inState.getBooleanArray(CLICKABLE);
        text = inState.getStringArray(TEXT);
        for (int i = 0; i < btns.length; i++) {
            btns[i].setTextColor(textColor[i]);
            btns[i].setClickable(clicked[i]);
            btns[i].setText(text[i]);
        }

    }


}