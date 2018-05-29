package com.example.recycler222;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Update extends AppCompatActivity
{
    public ArrayList<String> ArrayOfId = new ArrayList<>();
    public ArrayList<String> ArrayOfName = new ArrayList<>();
    public ArrayList<String> ArrayOfNumber = new ArrayList<>();

    public ArrayList<String> ArrayOfURL = new ArrayList<>();
    public ArrayList<String> ArrayOfURLBack = new ArrayList<>();
    public ArrayList<String> ArrayOfExtra = new ArrayList<>();

    EditText editTextNameU;
    EditText editTextNumberU;
    EditText edtxtUrlU;
    EditText edtxtUrl2U;
    EditText editTextExtraU;

    TextView idTextViewValue;

    Button btnU;
    Button btnDelete;
    int idGLobal = 0;
    int hereId = 0;

    final DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        idTextViewValue = (TextView) findViewById(R.id.textViewIDItem);



        btnU = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.buttonDeleteU);

        editTextNameU = (EditText) findViewById(R.id.edittext_name_update);
        editTextNumberU = (EditText) findViewById(R.id.editTextNumNum_update);
        edtxtUrlU = (EditText) findViewById(R.id.edittext_url_update);
        edtxtUrl2U = (EditText) findViewById(R.id.edittext_back_url_update);
        editTextExtraU = (EditText) findViewById(R.id.edittext_extra_update);

        Bundle extras = getIntent().getExtras();
        String idValue = extras.getString("idValue");

        hereId = Integer.parseInt(idValue);



        ReadDataBase(hereId-1);

        btnU.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                SQLiteDatabase database = dbHelper.getWritableDatabase();



                String changeName = "UPDATE " + DBHelper.DATABASE_NAME+
                    " SET "+DBHelper.KEY_NAME+" = '"+editTextNameU.getText().toString()+"'" +
                    " WHERE _id = "+hereId+";";
                database.execSQL(changeName);

                String changeNumber = "UPDATE " + DBHelper.DATABASE_NAME+
                        " SET "+DBHelper.KEY_NUMBER+" = '"+editTextNumberU.getText().toString()+"'" +
                        " WHERE _id = "+hereId+";";
                database.execSQL(changeNumber);

                String changeUrl = "UPDATE " + DBHelper.DATABASE_NAME+
                        " SET "+DBHelper.KEY_URL+" = '"+edtxtUrlU.getText().toString()+"'" +
                        " WHERE _id = "+hereId+";";
                database.execSQL(changeUrl);

                String changeUrl2 = "UPDATE " + DBHelper.DATABASE_NAME+
                        " SET "+DBHelper.KEY_URL_BACK+" = '"+edtxtUrl2U.getText().toString()+"'" +
                        " WHERE _id = "+hereId+";";
                database.execSQL(changeUrl2);

                String changeExtra = "UPDATE " + DBHelper.DATABASE_NAME+
                        " SET "+DBHelper.KEY_EXTRA+" = '"+editTextExtraU.getText().toString()+"'" +
                        " WHERE _id = "+hereId+";";
                database.execSQL(changeExtra);




                OpenUpdatedRecyclerView();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SQLiteDatabase database = dbHelper.getWritableDatabase();


                String query2 = "SELECT _id from "+DBHelper.DATABASE_NAME+" order by _id DESC limit 1";


                long lastId = 0;
                Cursor c = database.rawQuery(query2, null);
                if (c != null && c.moveToFirst())
                {
                    lastId = c.getLong(0);
                }
                c.close();




                Toast toast = Toast.makeText(getApplicationContext(), "lastID : "+lastId, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                toast.show();

                String del = "DELETE FROM " + DBHelper.DATABASE_NAME +
                        " WHERE _id = "+hereId+";";
                database.execSQL(del);


                int j = 0;
                for (int i = hereId+1; i<=lastId ; i++)
                {
                    j= i-1;
                    String changeName = "UPDATE " + DBHelper.DATABASE_NAME+
                            " SET "+DBHelper.KEY_ID+" = '"+j+"'" +
                            " WHERE _id = '"+i+"';";
                    database.execSQL(changeName);
                    j=0;
                }

                OpenUpdatedRecyclerView();

            }
        });
    }

    public void ReadDataBase(int cursorValue)
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.DATABASE_NAME, null, null, null, null, null, null);



        int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
        int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
        int numberIndex = cursor.getColumnIndex(DBHelper.KEY_NUMBER);
        int urlIndex = cursor.getColumnIndex(DBHelper.KEY_URL);
        int url2Index = cursor.getColumnIndex(DBHelper.KEY_URL_BACK);
        int extraIndex = cursor.getColumnIndex(DBHelper.KEY_EXTRA);

        cursor.moveToPosition(cursorValue);

        if (null != cursor)
        {
            editTextNameU.setText(cursor.getString(nameIndex));
            editTextNumberU.setText(cursor.getString(numberIndex));
            edtxtUrlU.setText(cursor.getString(urlIndex));
            edtxtUrl2U.setText(cursor.getString(url2Index));
            editTextExtraU.setText(cursor.getString(extraIndex));
        }
        cursor.close();

        String real_id = "juiiu";


        Toast toast = Toast.makeText(getApplicationContext(), "Cursor: "+cursorValue +"\n"+ "ID:" , Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
        toast.show();


    }

    public void OpenUpdatedRecyclerView()
    {



        SQLiteDatabase database = dbHelper.getWritableDatabase();


        ArrayOfId.clear();
        ArrayOfName.clear();
        ArrayOfURL.clear();
        ArrayOfExtra.clear();
        ArrayOfURLBack.clear();


        Cursor cursor = database.query(DBHelper.DATABASE_NAME, null, null, null, null, null, null);


        if (cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int numberIndex = cursor.getColumnIndex(DBHelper.KEY_NUMBER);
            int urlIndex = cursor.getColumnIndex(DBHelper.KEY_URL);
            int url2Index = cursor.getColumnIndex(DBHelper.KEY_URL_BACK);
            int extraIndex = cursor.getColumnIndex(DBHelper.KEY_EXTRA);

            do
            {
                ArrayOfId.add(cursor.getString(idIndex));
                ArrayOfName.add(cursor.getString(nameIndex));
                ArrayOfNumber.add(cursor.getString(numberIndex));
                ArrayOfURL.add(cursor.getString(urlIndex));
                ArrayOfURLBack.add(cursor.getString(url2Index));
                ArrayOfExtra.add(cursor.getString(extraIndex));
            }
            while (cursor.moveToNext());
        }
        else
            Log.d("mLog","0 rows");

        cursor.close();

        Intent intent=new Intent(Update.this, MainActivity.class);

        intent.putExtra("listID", ArrayOfId);
        intent.putExtra("listURL", ArrayOfURL);
        intent.putExtra("listName", ArrayOfName);
        intent.putExtra("listExtra", ArrayOfExtra);
        intent.putExtra("listNumber", ArrayOfNumber);

        startActivity(intent);
    }
}