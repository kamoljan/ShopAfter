package org.kamol.shopafter;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    ((BaseActivity) getActivity()).inject(this);
  }
}
