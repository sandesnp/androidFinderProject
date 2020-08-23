package com.example.andoird_finderproject.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoird_finderproject.R;
import com.example.andoird_finderproject.fragments.Inner_Fragment.fragmentUserCreate;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.httpRequests.requestUser;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Fragment_Profile extends Fragment implements View.OnClickListener {


    GoogleSignInClient mGoogleSignInClient;
    private SignInButton signInButton;
    private TextView tvFullName, tvEmail;
    private Button btnSignOut;
    private int RC_SIGN_IN = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__profile, container, false);
        signInButton = view.findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);
        tvFullName = view.findViewById(R.id.tvFullName);
        tvEmail = view.findViewById(R.id.tvEmail);
        btnSignOut = view.findViewById(R.id.sign_out_button);
        btnSignOut.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.sign_out_button:
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getContext(), "Successfully Signed out", Toast.LENGTH_SHORT).show();
                                global.token = "Bearer ";
                                updateUI(null);
                            }
                        });
                break;
        }
    }

    @Override
    public void onStart() { //Checking on start if the user is logged in.
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        updateUI(account);
    }

    //Step7
    public void updateUI(GoogleSignInAccount account) {
        //if the account is not null means user is currently logged in.
        if (account != null) {
            //redirecting to register google email if not registered
            if (!new requestUser(null).fetchBy(account.getEmail())) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragmentUserCreate())
                        .addToBackStack(null).commit();
                return;
            }
            signInButton.setVisibility(View.GONE);
            tvFullName.setVisibility(View.VISIBLE);
            tvEmail.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.VISIBLE);
            tvFullName.setText(account.getDisplayName());
            tvEmail.setText(account.getEmail());

        } else {
            signInButton.setVisibility(View.VISIBLE);
            tvFullName.setVisibility(View.GONE);
            tvEmail.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.GONE);
        }
    }

    private void signIn() {
        //Opens Google Custom sign in form
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    @Override
    public void onDetach() {
        //when the user presses back or the changes to new fragment

        super.onDetach();

    }

}