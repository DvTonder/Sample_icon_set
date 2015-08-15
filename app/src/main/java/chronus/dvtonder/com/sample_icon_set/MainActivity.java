package chronus.dvtonder.com.sample_icon_set;

import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.dvtonder.chronus.sample_icon_pack.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ComponentName componentName = new ComponentName(this, chronus.dvtonder.com.sample_icon_set.MainActivity.class);
        final PackageManager packageManager = getApplicationContext().getPackageManager();

        try {
            packageManager.getPackageInfo("com.dvtonder.chronus", PackageManager.GET_ACTIVITIES);
            new MaterialDialog.Builder(this)
                    .title(getString(R.string.hide_app_title))
                    .content(String.format(getString(R.string.hide_app_description), getString(R.string.app_name)))
                    .negativeText(getString(R.string.hide_app))
                    .cancelable(false)
                    .dismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                    PackageManager.DONT_KILL_APP);
                            finish();
                        }
                    })
                    .show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            new MaterialDialog.Builder(this)
                    .title(getString(R.string.play_store_title))
                    .content(String.format(getString(R.string.play_store_description), getString(R.string.app_name)))
                    .negativeText(getString(R.string.play_store))
                    .cancelable(false)
                    .dismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            Intent download = new Intent(Intent.ACTION_VIEW);
                            download.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.dvtonder.chronus"));
                            startActivity(download);
                            finish();
                        }
                    })
                    .show();
        }
    }
}
