package com.example.game4sell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    String userid, userName, userNumber, userAddress, userPassword;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        final NavigationActivity activity = (NavigationActivity)getActivity();

        Bundle results = activity.getUserID();
        userid = results.getString("userID");

        return view;


    }
}
