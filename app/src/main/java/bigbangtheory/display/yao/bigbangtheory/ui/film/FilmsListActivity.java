package bigbangtheory.display.yao.bigbangtheory.ui.film;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;

import org.apache.http.Header;

import bigbangtheory.display.yao.bigbangtheory.R;
import bigbangtheory.display.yao.bigbangtheory.authorication.AuthorTokenResource;
import bigbangtheory.display.yao.bigbangtheory.login.User;
import bigbangtheory.display.yao.bigbangtheory.authorication.AuthorTokenResponseHanlder;
import bigbangtheory.display.yao.bigbangtheory.film.FilmService;
import bigbangtheory.display.yao.bigbangtheory.ui.login.LoginActivity;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;

public class FilmsListActivity extends RoboActionBarActivity {
    private static final int LOGIN_REQUEST_CODE = 0x009;

    @InjectView(R.id.autorization_code)
    private TextView tv;
    @Inject
    private FilmService filmService;
    @Inject private AuthorTokenResource tokenResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_list);
        getPersonalInformation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_info_list, menu);
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
            getPersonalInformation();
            return true;
        } else if(id == R.id.action_exit) {
            //在sharedpreference中清除token
            tokenResource.deleteAccessToken();

            //在service中清除token
            filmService.removeAuthorizationHeader();

            //启用登录activity
            Intent i = new Intent(this, LoginActivity.class);
            startActivityForResult(i, LOGIN_REQUEST_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {
            getPersonalInformation();
        }
    }

    /**
     * 获取用户信息
     */
    private void getPersonalInformation() {
        filmService.getPersonalInformation(new AuthorTokenResponseHanlder(this) {

            @Override
            public void onRequestSuccess(Object object) {
                User user = (User) object;
                Toast.makeText(getApplicationContext(), user.getAvatar(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
