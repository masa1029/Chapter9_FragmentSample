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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuListFragment extends Fragment {


    public MenuListFragment() {
        // Required empty public constructor
    }

    private Activity _parentActivity;
    private boolean _isLayoutXLarge = true;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        _parentActivity=getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_list,container,false);
        ListView lvMenu = view.findViewById(R.id.lvMenu);
        List<Map<String, String>> menuList = new ArrayList<>();
        Map<String, String> menu = new HashMap<>();
        menu.put("name", "から揚げ定食");
        menu.put("price", "800円");
        menuList.add(menu);

        menu=new HashMap<>();
        menu.put("name","ハンバーグ定食");
        menu.put("price","850円");
        menuList.add(menu);

        menu=new HashMap<>();
        menu.put("name","ハンバーグ定食");
        menu.put("price","850円");
        menuList.add(menu);

        menu=new HashMap<>();
        menu.put("name","生姜焼き定食");
        menu.put("price","850円");
        menuList.add(menu);

        menu=new HashMap<>();
        menu.put("name","ステーキ定食");
        menu.put("price","1000円");
        menuList.add(menu);

        menu=new HashMap<>();
        menu.put("name","とんかつ定食");
        menu.put("price","900円");
        menuList.add(menu);

        String[] from = {"name","price"};
        int[] to = {android.R.id.text1,android.R.id.text2};
        SimpleAdapter adapter = new SimpleAdapter(_parentActivity,menuList,android.R.layout.simple_list_item_2,from , to);

        lvMenu.setAdapter(adapter);
        return view;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View menuThanksFrame = _parentActivity.findViewById(R.id.menuThanksFrame);
        if(menuThanksFrame == null) {
            _isLayoutXLarge = false;
        }
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
            String menuName = item.get("name");
            String menuPrice = item.get("price");
            Bundle bundle = new Bundle();


            bundle.putString("menuName", menuName);
            bundle.putString("menuPrice", menuPrice);

           if(_isLayoutXLarge) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MenuThanksFragment menuThanksFragment = new MenuThanksFragment();
                menuThanksFragment.setArguments(bundle);
                transaction.replace(R.id.menuThanksFrame, menuThanksFragment);
                transaction.commit();
            }
            else {
                Intent intent = new Intent(_parentActivity, MenuThanksActivity.class);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        }
    }
}
