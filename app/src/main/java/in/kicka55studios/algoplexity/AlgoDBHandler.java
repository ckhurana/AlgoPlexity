package in.kicka55studios.algoplexity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AlgoDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "algorithms.db";
    public static final String TABLE_AlGORITHMS = "algorithms";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ALGONAME = "algoname";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_WORSTCASE = "worstcase";
    public static final String COLUMN_AVGCASE = "avgcase";

    public AlgoDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_AlGORITHMS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ALGONAME + " TEXT, " +
                COLUMN_DESC + " TEXT, " +
                COLUMN_WORSTCASE + " TEXT, " +
                COLUMN_AVGCASE + " TEXT );";
        db.execSQL(query);
        populateTable(db);
    }

    private void populateTable(SQLiteDatabase db) {
        int number_of_algos = 9;

        Algorithm[] algo = new Algorithm[number_of_algos];
        algo[0] = new Algorithm("Linear Search", "For an array of \'n\' elements.", "Order(n)", "Order(n)");
        algo[1] = new Algorithm("Binary Search", "For a sorted array of \'n\' elements.", "Order(log(n))", "Order(log(n))");
        algo[2] = new Algorithm("Bubble Sort", "For an array of \'n\' elements.", "Order(n^2)", "Order(n^2)");
        algo[3] = new Algorithm("Insertion Sort", "For an array of \'n\' elements.", "Order(n^2)", "Order(n^2)");
        algo[4] = new Algorithm("Merge Sort", "For an array of \'n\' elements.", "Order(n*log(n))", "Order(n*log(n))");
        algo[5] = new Algorithm("Heap Sort", "For an array of \'n\' elements.", "Order(n*log(n))", "Order(n*log(n))");
        algo[6] = new Algorithm("Quick Sort", "For an array of \'n\' elements.", "Order(n*log(n))", "Order(n^2)");
        algo[7] = new Algorithm("Counting Sort", "For an array of \'n\' elements and values in the range of 0 to k.", "Order(n + k)", "Order(n + k)");
        algo[8] = new Algorithm("KMP String Matching", "For a target string of length \'m\' and pattern string of length \'n\'.", "Order(m + n)", "Order(m + n)");

        for (int i = 0; i < number_of_algos; i++) {

            ContentValues values = new ContentValues();

            values.put(COLUMN_ALGONAME, algo[i].get_algoname());
            values.put(COLUMN_DESC, algo[i].get_desc());
            values.put(COLUMN_AVGCASE, algo[i].get_avgcase());
            values.put(COLUMN_WORSTCASE, algo[i].get_worstcase());

            db.insert(TABLE_AlGORITHMS, null, values);
        }
    }

    public ArrayList<String> algoListGenerator() {
        ArrayList<String> algoList = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_ALGONAME + " FROM " + TABLE_AlGORITHMS + " WHERE 1;";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_ALGONAME)) != null) {
                algoList.add(c.getString(c.getColumnIndex(COLUMN_ALGONAME)));
            }
            c.moveToNext();
        }
        return algoList;
    }

    public void addNewAlgo(Algorithm algo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ALGONAME, algo.get_algoname());
        values.put(COLUMN_DESC, algo.get_desc());
        values.put(COLUMN_AVGCASE, algo.get_avgcase());
        values.put(COLUMN_WORSTCASE, algo.get_worstcase());
        db.insert(TABLE_AlGORITHMS, null, values);
        db.close();
    }

    public void resetDb() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + TABLE_AlGORITHMS + ";";
        db.execSQL(query);
        onCreate(db);
        db.close();
    }

    public Algorithm getInfo(String algoName) {
        SQLiteDatabase db = getWritableDatabase();
        Algorithm algoData = new Algorithm();
        String query = "SELECT * FROM " + TABLE_AlGORITHMS + " WHERE " + COLUMN_ALGONAME + " = \"" + algoName + "\";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if (c.getString(c.getColumnIndex(COLUMN_ALGONAME)) != null) {
            algoData = new Algorithm(c.getString(c.getColumnIndex(COLUMN_ALGONAME)),
                    c.getString(c.getColumnIndex(COLUMN_DESC)),
                    c.getString(c.getColumnIndex(COLUMN_AVGCASE)),
                    c.getString(c.getColumnIndex(COLUMN_WORSTCASE)));
        }
        db.close();
        return algoData;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTIS " + TABLE_AlGORITHMS + ";");
        onCreate(db);
    }

    public void deleteAlgo(String algorithmName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_AlGORITHMS + " WHERE " + COLUMN_ALGONAME + " = \"" + algorithmName + "\";");
        db.close();
    }
}
