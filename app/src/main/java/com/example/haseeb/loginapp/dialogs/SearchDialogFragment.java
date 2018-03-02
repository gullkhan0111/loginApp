package com.example.haseeb.loginapp.dialogs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.haseeb.loginapp.R;
import com.example.haseeb.loginapp.Listeners.SearchListener;

public class SearchDialogFragment extends DialogFragment {

    EditText SearchbyUserName, SearchbyCityName, SearchbyAge;
    Button btnSearch;
    SearchListener searchListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_signin, container,
                false);
        SearchbyUserName = rootView.findViewById(R.id.SearchUserName);
        SearchbyCityName = rootView.findViewById(R.id.SearchCityName);
        SearchbyAge = rootView.findViewById(R.id.SearchAge);
        btnSearch = rootView.findViewById(R.id.srchButton);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = SearchbyUserName.getText().toString();
                String cityName = SearchbyCityName.getText().toString();
                String age = SearchbyAge.getText().toString().toString();


                if(userName.equals("")){
                    userName="";
                }else if(cityName.equals("")){
                    cityName="";
                }else if(age.equals("")){
                    age="";
                }

                searchListener.getDataFromDialog(userName,cityName,age);
                dismiss();

            }
        });


        return rootView;
    }

    @SuppressLint("LongLogTag")
    public void onAttach(Context context) {
        try {
            searchListener = (SearchListener) getTargetFragment();
        } catch (ClassCastException ex) {
            Log.d("on Attch class casT Exception:", ex.getMessage());
        }
        super.onAttach(context);
    }


}
