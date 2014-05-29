package org.kamol.shopafter;

import android.app.Application;
import android.content.Context;

import java.util.Arrays;
import java.util.List;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.PushService;

import org.kamol.shopafter.ui.activity.ViewActivity;

import dagger.ObjectGraph;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public class ShopAfterApp extends Application {
  private ObjectGraph applicationGraph;

  @Override public void onCreate() {
    super.onCreate();

    Parse.initialize(this, "ZT4hK5Z1U58TSxg2g5OjFjvX6tZiByYRVNRfLN5t", "Gx49K57B1FkE3BtjEPP2lrX47oL0ZrTdT5iltrRm");
    ParseFacebookUtils.initialize(getString(R.string.app_id));
    PushService.setDefaultPushCallback(this, ViewActivity.class);
    ParseInstallation.getCurrentInstallation().saveInBackground();

    if (BuildConfig.DEBUG) {
      Timber.plant(new DebugTree());
    } else {
      // TODO Crashlytics.start(this);
      // TODO Timber.plant(new CrashlyticsTree());
    }

    applicationGraph = ObjectGraph.create(getModules().toArray());
  }

  /**
   * A list of modules to use for the application graph.
   * Subclasses can override this method to
   * provide additional modules provided they call {@code super.getModules()}.
   */
  protected List<Object> getModules() {
    return Arrays.<Object>asList(new AndroidModule(this));
  }

  ObjectGraph getApplicationGraph() {
    return applicationGraph;
  }

  //TODO: Added from U2020, is it correct?
  public void inject(Object o) {
    applicationGraph.inject(o);
  }

  //TODO: Added from U2020, is it correct?
  public static ShopAfterApp get(Context context) {
    return (ShopAfterApp) context.getApplicationContext();
  }

}
