package org.kamol.shopafter.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kamol.shopafter.BaseFragment;
import org.kamol.shopafter.R;

public class ListingFragment extends BaseFragment {
  public static ListingFragment newInstance() {
    return new ListingFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    return inflater.inflate(R.layout.gallery_listing_view, container, false);
  }

}
