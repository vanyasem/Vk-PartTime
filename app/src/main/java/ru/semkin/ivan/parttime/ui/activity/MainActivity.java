package ru.semkin.ivan.parttime.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.navigation.Navigation;
import ru.semkin.ivan.parttime.R;
import ru.semkin.ivan.parttime.api.sync.Sync;
import ru.semkin.ivan.parttime.ui.base.BaseDrawerActivity;
import ru.semkin.ivan.parttime.util.ActivityUtil;

public class MainActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sync sync = Sync.getInstance();
        sync.initSync(this);
        sync.startSync(this);

        // Broken Upstream
        //NavigationUI.setupWithNavController(navigationView,
        //        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment));

        ActivityUtil.setActionBar(this);
    }

    public static void openMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }

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
        if (id == R.id.action_profile) {
            Navigation.findNavController(
                    MainActivity.this, R.id.nav_host_fragment).navigate(R.id.profileActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
