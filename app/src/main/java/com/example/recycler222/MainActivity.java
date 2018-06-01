package com.example.recycler222;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";

    private ArrayList<String> mExtra = new ArrayList<>();

    public ArrayList<String> tryList = new ArrayList<>();

    private ArrayList<String> mIDs = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNumbers = new ArrayList<>();


    public ArrayList<String> ArrayOfId = new ArrayList<>();
    public ArrayList<String> ArrayOfName = new ArrayList<>();
    public ArrayList<String> ArrayOfNumber = new ArrayList<>();

    public ArrayList<String> ArrayOfURL = new ArrayList<>();
    public ArrayList<String> ArrayOfURLBack = new ArrayList<>();
    public ArrayList<String> ArrayOfExtra = new ArrayList<>();

    final DBHelper dbHelper = new DBHelper(this);


    private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);


        mIDs = (ArrayList<String>) getIntent().getSerializableExtra("listID");
        mImageUrls = (ArrayList<String>) getIntent().getSerializableExtra("listURL");
        mNames = (ArrayList<String>) getIntent().getSerializableExtra("listName");
        mExtra = (ArrayList<String>) getIntent().getSerializableExtra("listExtra");
        mNumbers = (ArrayList<String>) getIntent().getSerializableExtra("listNumber");

        initImageBitmaps();
    }



    private void initImageBitmaps()
    {
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;

        initRecyclerView();
    }

    private void initRecyclerView()
    {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls, mExtra, mIDs, mNumbers);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}