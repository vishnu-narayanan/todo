package com.pepalo.todo;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	ArrayList<String> items;
	ArrayAdapter<String> itemsadapter;
	ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list = (ListView) findViewById(R.id.listView1);
		items = new ArrayList<String>();
		readItems();
		itemsadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		
		list.setAdapter(itemsadapter);
	    setupListViewListener();
	    
	}

	private void setupListViewListener(){
		list.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long rowId){
				items.remove(position);
				itemsadapter.notifyDataSetChanged();
				saveItems();
				return true;
			}
		});
	}
	
	public void addToDoItem(View V) {
		EditText edit = (EditText) findViewById(R.id.editText1);
		itemsadapter.add(edit.getText().toString());
		edit.setText("");
		saveItems();
    }
	
	private void readItems(){
		File filesDir = getFilesDir();
		File todofile = new File(filesDir,"todo.txt");
		try {
			items = new ArrayList<String>(FileUtils.readLines(todofile));
		} catch (Exception e) {
			// TODO: handle exception
			items = new ArrayList<String>();
			
		}
	}
	
	private void saveItems() {
		File filesDir = getFilesDir();
		File todofile = new File(filesDir,"todo.txt");
		try {
			FileUtils.writeLines(todofile, items);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
