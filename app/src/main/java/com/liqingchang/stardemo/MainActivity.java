package com.liqingchang.stardemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    private EditText url;
    private LinearLayout container;
    private Button add;
    private Button post;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        inflater = LayoutInflater.from(this);
        url = (EditText) findViewById(R.id.url);
        container = (LinearLayout) findViewById(R.id.container);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(click);
        post = (Button) findViewById(R.id.post);
        post.setOnClickListener(click);
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.add:
                    // TODO: 继续懒
                    View view = inflater.inflate(R.layout.item_data, null);
                    container.addView(view);
                    break;
                case R.id.post:
                    Map<String, String> data = new HashMap<>();
                    for(int i = 0 ; i < container.getChildCount(); i++) {
                        LinearLayout child = (LinearLayout)container.getChildAt(i);
                        EditText key = (EditText) child.findViewById(R.id.key);
                        EditText value = (EditText) child.findViewById(R.id.value);
                        data.put(key.getText().toString(), value.getText().toString());
                    }
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
