package com.example.andoird_finderproject.fragments.Inner_Fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoird_finderproject.R;
import com.example.andoird_finderproject.fragments.Fragment_Profile;
import com.example.andoird_finderproject.httpRequests.requestUser;
import com.example.andoird_finderproject.models.user;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class fragmentUserCreate extends Fragment {

    EditText etPassword, etPasswordCheck;
    GoogleSignInAccount acct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_create, container, false);

        TextView tvFullName = view.findViewById(R.id.tvPostUserName);
        TextView tvEmail = view.findViewById(R.id.tvPostEmail);
        etPassword = view.findViewById(R.id.etPostPassword);
        etPasswordCheck = view.findViewById(R.id.etPostPasswordCheck);
        CircleImageView ivProfileImage = view.findViewById(R.id.ivPostProfile);
        Button btnPost = view.findViewById(R.id.btnPostUser);

        acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            tvFullName.setText(acct.getDisplayName());
            tvEmail.setText(acct.getEmail());
            Uri PersonPhoto= acct.getPhotoUrl();
            Picasso.get().load(PersonPhoto).into(ivProfileImage);
            btnPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validationCheck();
                }
            });
        }
        return view;
    }

    public void validationCheck() {
        if (TextUtils.isEmpty(etPassword.getText())) {
            etPassword.setError("Please don't leave the field empty.");
            etPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPasswordCheck.getText())) {
            etPasswordCheck.setError("Please don't leave the field empty.");
            etPasswordCheck.requestFocus();
            return;
        }
        if (!etPassword.getText().toString().equals(etPasswordCheck.getText().toString())) {
            etPasswordCheck.setError("Password Doesn't Match.");
            etPasswordCheck.requestFocus();
            return;
        }

        user user = new user();
        user.setUseremail(acct.getEmail());
        user.setUserfullname(acct.getDisplayName());
        user.setPassword(etPassword.getText().toString());

        if (new requestUser(user).post()) {
            //Posting User here
            getActivity().getSupportFragmentManager().popBackStack();

//                    .beginTransaction().replace(R.id.fragment_container, new Fragment_Profile())
//                    .commit();
            Toast.makeText(getActivity(), "Successfully Registered", Toast.LENGTH_SHORT).show();
        }
    }

}