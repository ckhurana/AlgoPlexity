package in.kicka55studios.algoplexity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    AlgoDBHandler dbHandler;
    public final static String EXTRA_MESSAGE = "in.kicka55studios.algoplexity.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new AlgoDBHandler(this, null, null, 1);

        ArrayList<String> algoList = dbHandler.algoListGenerator();

        ListAdapter algoListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, algoList);
        ListView algoListView = (ListView) findViewById(R.id.algoListView);
        algoListView.setAdapter(algoListAdapter);

        algoListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String algoName = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(MainActivity.this, AlgorithmActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, algoName);
                        //Toast.makeText(MainActivity.this, "Nailed It", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }
        );
    }
}
