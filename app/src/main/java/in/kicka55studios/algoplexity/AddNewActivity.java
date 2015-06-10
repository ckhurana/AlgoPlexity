package in.kicka55studios.algoplexity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddNewActivity extends AppCompatActivity {

    private EditText algoNameInput, algoDescInput, avgCaseInput, worstCaseInput;
    Algorithm algo;
    AlgoDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        algoNameInput = (EditText) findViewById(R.id.algoNameInput);
        algoDescInput = (EditText) findViewById(R.id.algoDescInput);
        avgCaseInput = (EditText) findViewById(R.id.avgCaseInput);
        worstCaseInput = (EditText) findViewById(R.id.worstCaseInput);
    }

    public void addAlgo(View view) {

        dbHandler = new AlgoDBHandler(this, null, null, 1);

        algo = new Algorithm(algoNameInput.getText().toString(),
                algoDescInput.getText().toString(),
                avgCaseInput.getText().toString(),
                worstCaseInput.getText().toString());
        dbHandler.addNewAlgo(algo);

        algoNameInput.setText("");
        algoDescInput.setText("");
        avgCaseInput.setText("");
        worstCaseInput.setText("");

        Toast.makeText(this, algo.get_algoname() + " added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }
}
