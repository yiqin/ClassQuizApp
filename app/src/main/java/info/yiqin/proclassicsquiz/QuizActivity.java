package info.yiqin.proclassicsquiz;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class QuizActivity extends ActionBarActivity {

    // TODO
    private Button mExitButton, m;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //exit button
        mExitButton = (Button) findViewById(R.id.exitButton);
        mExitButton.setOnClickListener(new View.OnClickListener() {

            // This view is mExitButton......
            @Override
            public void onClick(View view) {
                finish();

            }
        });




    }

    private void startMe() {




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
