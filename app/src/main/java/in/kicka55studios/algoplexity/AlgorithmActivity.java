package in.kicka55studios.algoplexity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class AlgorithmActivity extends AppCompatActivity {

    TextView algoNameText, descText, avgText, worstText;
    Algorithm algo;
    AlgoDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_algorithm);

        Intent intent = getIntent();
        dbHandler = new AlgoDBHandler(this, null, null, 1);
        algo = dbHandler.getInfo(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));

        algoNameText = (TextView) findViewById(R.id.algoNameText);
        descText = (TextView) findViewById(R.id.descText);
        avgText = (TextView) findViewById(R.id.avgText);
        worstText = (TextView) findViewById(R.id.worstText);

        algoNameText.setText(algo.get_algoname());
        descText.setText(algo.get_desc());
        avgText.setText(algo.get_avgcase());
        worstText.setText(algo.get_worstcase());
    }

    /*

     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_algorithm, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_algo:
                AlgoDBHandler dbHandler = new AlgoDBHandler(this, null, null, 1);
                dbHandler.deleteAlgo(algo.get_algoname());
                onNavigateUp();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
