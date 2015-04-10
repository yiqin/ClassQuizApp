package info.yiqin.proclassicsquiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class QuizActivity extends ActionBarActivity {

    // TODO - Three different buttons.
    private Button mExitButton, mStartButton;
    private String mName;
    private EditText mNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mNameEditText = (EditText) findViewById(R.id.editName);

        //exit button
        mExitButton = (Button) findViewById(R.id.exitButton);
        mExitButton.setOnClickListener(new View.OnClickListener() {
            // This view is mExitButton......
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        //start button
        mStartButton = (Button) findViewById(R.id.startButton);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMe();
            }
        });
    }

    private void startMe() {
        mName = mNameEditText.getText().toString();
        QuizTracker.getInstance().setName(mName);
        askQuestion(1);
    }

    private void askQuestion(int number) {
        QuizTracker.getInstance().setQuestionNum(number);
        Intent intent = new Intent(QuizActivity.this, QuestionActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.menuExit:
                finish();
                return true;

            case R.id.menuStart:
                //
                return true;

            default:
                return false;
        }
    }
}
