package org.kamol.shopafter.ui.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;

import com.squareup.picasso.Picasso;

import org.kamol.shopafter.ShopAfterApp;
import org.kamol.shopafter.R;
import org.kamol.shopafter.data.ListingDatabase;
import org.kamol.shopafter.data.api.model.Image;
import org.kamol.shopafter.data.rx.EndlessObserver;
import org.kamol.shopafter.ui.misc.BetterViewAnimator;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscription;

public class ListingView extends BetterViewAnimator {
  @InjectView(R.id.gallery_grid) AbsListView galleryView;
  @Inject Picasso picasso;
  @Inject ListingDatabase listingDatabase;
  private int category = 0; // 0 is all categories :TODO move to conf or store in SharedPreferences
  private Subscription request;
  private final ListingAdapter adapter;

  public ListingView(Context context, AttributeSet attrs) {
    super(context, attrs);
    ShopAfterApp.get(context).inject(this);

    adapter = new ListingAdapter(context, picasso);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);

    galleryView.setAdapter(adapter);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();

    request = listingDatabase.loadGallery(category, new EndlessObserver<List<Image>>() {
      @Override public void onNext(List<Image> images) {
        adapter.replaceWith(images);
        setDisplayedChildId(R.id.gallery_grid);
      }
    });
  }

  @Override protected void onDetachedFromWindow() {
    request.unsubscribe();
    super.onDetachedFromWindow();
  }
}

