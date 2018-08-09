package project.jsht.mx.org.bamx.jshtablet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import project.jsht.mx.org.bamx.jshtablet.Utils.Constants;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

public class tabsFragment extends Fragment   {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public tabsFragment()
    {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_tabs, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPagerContainer);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Utils.currentTab = tab.getText().toString();
                Toast toast = Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT);
                toast.show();
                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getContext());
                Intent i = new Intent("TAG_REFRESH");
                lbm.sendBroadcast(i);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        tabsFragment.ViewPagerAdapter adapter = new tabsFragment.ViewPagerAdapter(getChildFragmentManager());

        switch (Utils.opcionActual) {
            case Inicio:
                break;
            case Contactos:
                adapter.addFragment(new ContactsFragment(), Constants.HEADER_BANCOS_DE_ALIMENTOS);
                adapter.addFragment(new ContactsFragment(), Constants.HEADER_CENTROS_COMUNITARIOS);
                break;
            case Mapa:
                adapter.addFragment(new ContactsFragment(), Constants.HEADER_BANCOS_DE_ALIMENTOS);
                adapter.addFragment(new ContactsFragment(), Constants.HEADER_CENTROS_COMUNITARIOS);
                break;
            case Acopio:
                
            default:
                break;
        }


        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
