package com.masaki.fragmentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MenuThanksFragment extends Fragment {

    private Activity _parentActivity;
    private boolean _isLayoutXLarge = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _parentActivity = getActivity();

        FragmentManager manager = getFragmentManager();
        MenuListFragment menuListFragment = (MenuListFragment) manager.findFragmentById(R.id.fragmentMenuList);
        if(menuListFragment == null) {
            _isLayoutXLarge = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_thanks, container, false);

        Bundle extras;
        if(_isLayoutXLarge) {
            extras = getArguments();
        }
        else {
            Intent intent = _parentActivity.getIntent();
            extras = intent.getExtras();
        }

        String menuName = "";
        String menuPrice = "";
        if(extras != null) {
            menuName = extras.getString("menuName");
            menuPrice = extras.getString("menuPrice");
        }
        TextView tvMenuName = view.findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = view.findViewById(R.id.tvMenuPrice);
        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);

        Button btBackButton = view.findViewById(R.id.btBackButton);
        btBackButton.setOnClickListener(new ButtonClickListener());

        return view;
    }


    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if(_isLayoutXLarge) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.remove(MenuThanksFragment.this);
                transaction.commit();
            }
            else {
                _parentActivity.finish();
            }
        }
    }
}

