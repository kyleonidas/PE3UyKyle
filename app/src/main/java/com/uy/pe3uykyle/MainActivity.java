package com.uy.pe3uykyle;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    EditText CodeName, VersionNos, ReleaseDate, APILevel;
    DBHelper helper = new DBHelper(this);
    Cursor table;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table = helper.selectRecord();
        CodeName = findViewById(R.id.CodeName);
        VersionNos = findViewById(R.id.VersionNos);
        ReleaseDate = findViewById(R.id.ReleaseDate);
        APILevel = findViewById(R.id.APILevel);
    }

    public void addRecord(View v)
    {
        String cn = CodeName.getText().toString();
        String vn = VersionNos.getText().toString();
        String rd = ReleaseDate.getText().toString();
        String al = APILevel.getText().toString();
        boolean inserted = helper.insert(cn, vn, rd, al);
        if (inserted == true)
        {
            Toast.makeText(this, "Record inserted", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record not inserted", Toast.LENGTH_LONG).show();
    }

    public void first(View v)
    {
        table.moveToFirst();
        data();
    }

    public void previous(View v)
    {
        table.moveToPrevious();
        if (table.isBeforeFirst())
        {
            Toast.makeText(this, "Record is at first position", Toast.LENGTH_LONG).show();
            //table.moveToFirst();
        } else data();
    }

    public void next(View v)
    {
        table.moveToNext();
        if (table.isAfterLast())
        {
            Toast.makeText(this, "Record is at last position", Toast.LENGTH_LONG).show();
            //table.moveToLast();
        } else data();
    }

    public void last(View v)
    {
        table.moveToLast();
        data();
    }

    public void data()
    {
        CodeName.setText(table.getString(1));
        VersionNos.setText(table.getString(2));
        ReleaseDate.setText(table.getString(3));
        APILevel.setText(table.getString(4));
    }

    public void editRecord(View v)
    {
        String id = table.getString(0);
        String cn = CodeName.getText().toString();
        String vn = VersionNos.getText().toString();
        String rd = ReleaseDate.getText().toString();
        String al = APILevel.getText().toString();

        boolean updated = helper.update(id, cn, vn, rd, al);
        if (updated == true)
        {
            Toast.makeText(this, "Record updated", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record not updated", Toast.LENGTH_LONG).show();
    }

    public void deleteRecord(View v)
    {
        String id = table.getString(0);
        boolean deleted = helper.delete(id);
        if (deleted == true)
        {
            Toast.makeText(this, "Record deleted", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record not deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {

    }
}
