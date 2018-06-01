package com.example.recycler222;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;

public class Import extends AppCompatActivity
{
    public ArrayList<String> ArrayOfId = new ArrayList<>();
    public ArrayList<String> ArrayOfName = new ArrayList<>();
    public ArrayList<String> ArrayOfNumber = new ArrayList<>();

    public ArrayList<String> ArrayOfURL = new ArrayList<>();
    public ArrayList<String> ArrayOfURLBack = new ArrayList<>();
    public ArrayList<String> ArrayOfExtra = new ArrayList<>();

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    EditText editTextName;
    EditText editTextNumber;
    EditText edtxtUrl;
    EditText edtxtUrl2;
    EditText editTextExtra;

    Button btnWrite;
    Button btnRead;
    Button btnReadFromCamera;

    String description = "dffgfdgfddgfdg";
    final DBHelper dbHelper = new DBHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        btnWrite = (Button) findViewById(R.id.button);
        btnRead = (Button) findViewById(R.id.btnReadFromDB);
        btnReadFromCamera = (Button) findViewById(R.id.btnReadFromCamera);

        editTextName = (EditText) findViewById(R.id.edittext_name);
        editTextNumber = (EditText) findViewById(R.id.editTextNumNum);
        edtxtUrl = (EditText) findViewById(R.id.edittext_url);
        edtxtUrl2 = (EditText) findViewById(R.id.edittext_back_url);
        editTextExtra = (EditText) findViewById(R.id.edittext_extra);



        btnWrite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = "";
                String number = "";
                String url = "";
                String url2 = "";
                String extra = "";

                name = editTextName.getText().toString();
                number = editTextNumber.getText().toString();

                url = edtxtUrl.getText().toString();
                url2 = edtxtUrl2.getText().toString();
                extra =  editTextExtra.getText().toString();

                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_NUMBER, number);
                contentValues.put(DBHelper.KEY_URL, url);
                contentValues.put(DBHelper.KEY_URL_BACK, url2);
                contentValues.put(DBHelper.KEY_EXTRA, extra);

                database.insert(DBHelper.DATABASE_NAME, null, contentValues);
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FillRecyclerVIew();
            }
        });
    }



    public  void FillRecyclerVIew()
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();


        ArrayOfId.clear();
        ArrayOfName.clear();
        ArrayOfURL.clear();
        ArrayOfExtra.clear();
        ArrayOfURLBack.clear();


        Cursor cursor = database.query(DBHelper.DATABASE_NAME, null, null, null, null, null, null);


        String listString = "";

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

        Intent intent=new Intent(Import.this, MainActivity.class);

        intent.putExtra("listID", ArrayOfId);
        intent.putExtra("listURL", ArrayOfURL);
        intent.putExtra("listName", ArrayOfName);
        intent.putExtra("listExtra", ArrayOfExtra);
        intent.putExtra("listNumber", ArrayOfNumber);


        startActivity(intent);
    }
}


