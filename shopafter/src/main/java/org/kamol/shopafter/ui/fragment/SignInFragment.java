package org.kamol.shopafter.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.kamol.shopafter.R;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import timber.log.Timber;

public class SignInFragment extends Fragment {
  static final String TAG = "SplashFragment";
  @InjectView(R.id.b_login) Button btnLogin;
  @InjectView(R.id.b_signin) Button btnSignin;
  @InjectView(R.id.et_name) EditText etName;
  @InjectView(R.id.et_email) EditText etEmail;
  @InjectView(R.id.et_password) EditText etPassword;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_signin, container, false);
    ButterKnife.inject(this, view);
    return view;
  }

  @OnClick(R.id.b_login) public void onClickBtnLogin() {
    List<String> permissions = Arrays.asList("email");
    ParseFacebookUtils.logIn(permissions, getActivity(), new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException err) {
        if (user == null) {
          Timber.d("Uh oh. The user cancelled the Facebook login.");
        } else if (user.isNew()) {
          Timber.d("User signed up and logged in through Facebook!");
        } else {
          Timber.d("User logged in through Facebook!");
        }
      }
    });
  }

  private void destroyFragment() {
    getChildFragmentManager().beginTransaction().hide(this).commit();
  }

  @OnClick(R.id.b_signin) public void onClickBtnSignin() {
    boolean isOk = true;
    if (etName.getText() == null || etName.getText().length() == 0) {
      etName.setError("What is your name?");
      isOk = false;
    }
    if (etEmail.getText() == null || etEmail.getText().length() == 0) {
      etEmail.setError("What is your email?");
      isOk = false;
    }
    if (etPassword.getText() == null || etPassword.getText().length() == 0) {
      etPassword.setError("Please enter your password");
      isOk = false;
    }
    if (isOk) {
      final ParseUser user = new ParseUser();
      user.setUsername(etName.getText().toString());
      user.setEmail(etEmail.getText().toString());
      user.setPassword(etPassword.getText().toString());
      user.signUpInBackground(new SignUpCallback() {
        public void done(ParseException e) {
          if (e == null) {
            Timber.i("A new account created :) ");
            Toast.makeText(getActivity(), "A new account is created :) ", Toast.LENGTH_LONG).show();
          } else {
            Timber.e(e, "Sign up didn't succeed. Look at ParseException for details");
            Toast.makeText(getActivity(), "Email account is ", Toast.LENGTH_LONG).show();
          }
        }
      });
    }
  }

  private void logInInBackground(String username, String password) {
      ParseUser.logInInBackground(username, password, new LogInCallback() {
          public void done(ParseUser user, ParseException e) {
              if (user != null) {
                  Timber.i("Hooray! The user is logged in.");
              } else {
                  Timber.i("Signup failed. Look at the ParseException to see what happened.");
              }
          }
      });
  }
}
