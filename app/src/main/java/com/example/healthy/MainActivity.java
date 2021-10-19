package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthy.BD.DBManager;
import com.example.healthy.adapter.MainAdater;

public class MainActivity extends AppCompatActivity {
private DBManager dbManager;
private RecyclerView recyclerView;
private MainAdater mainAdater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    protected void init(){

        dbManager = new DBManager(this);
        mainAdater = new MainAdater(this);
        recyclerView = findViewById(R.id.recview);
        // ucazivaem polozenie blocov recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getItemTouchHelper().attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mainAdater);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager.DBOpen();
        mainAdater.ubdateAdapter(dbManager.DBGetNote());



    }



    public void OnClick(View view){
//dbManager.DBInsert(edTitle.getText().toString(), edTitle2.getText().toString());
        // создание перехода между активити
        Intent intent = new Intent(MainActivity.this, NotesActivity.class);
        startActivity(intent);
    }

    public ItemTouchHelper getItemTouchHelper(){

return new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        mainAdater.DeleteItem(viewHolder.getAdapterPosition(), dbManager);
    }
});

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.DBClose();
    }
}