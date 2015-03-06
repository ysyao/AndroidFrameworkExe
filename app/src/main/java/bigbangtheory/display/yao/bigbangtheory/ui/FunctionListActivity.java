package bigbangtheory.display.yao.bigbangtheory.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import bigbangtheory.display.yao.bigbangtheory.R;
import bigbangtheory.display.yao.bigbangtheory.ui.appinformation.ILifeAppListActivity;
import bigbangtheory.display.yao.bigbangtheory.ui.film.FilmsListActivity;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;

public class FunctionListActivity extends RoboActionBarActivity {
    @InjectView(R.id.function_list)
    private ListView mLV;
    private String[] functions = {
      "豆瓣电影", "已安装列表"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_list);
        mLV.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, functions));
        mLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch(i) {
                    case 0:
                        intent = new Intent(FunctionListActivity.this, FilmsListActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(FunctionListActivity.this, ILifeAppListActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        intent = new Intent(FunctionListActivity.this, FilmsListActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_function_list, menu);
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
