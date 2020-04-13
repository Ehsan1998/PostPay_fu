package com.postpay.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.PostProcessor;
import android.net.Uri;
import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.postpay.Fragmens.AddFragment;
import com.postpay.Fragmens.OrderFragment;
import com.postpay.Fragmens.PaymentFragment;
import com.postpay.Fragmens.PostFragment;
import com.postpay.Fragmens.ProfileFragment;
import com.postpay.R;

public class HomePageActivity extends AppCompatActivity{

    private final int ID_PROFILE = 1;
    private final int ID_ORDER = 2;
    private final int ID_ADD = 3;
    private final int ID_POST = 4;
    private final int ID_PAYMENT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        MeowBottomNavigation bottomNavigation = (MeowBottomNavigation) findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_PROFILE,R.drawable.ic_person_black_24dp));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ORDER,R.drawable.ic_sync_black_24dp));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ADD,R.drawable.ic_add_black_24dp));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_POST,R.drawable.ic_map_black_24dp));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_PAYMENT,R.drawable.ic_attach_money_black_24dp));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

                Fragment selectedFragment = null;
                switch (item.getId()){
                    case ID_PROFILE:
                        selectedFragment = new ProfileFragment();
                        break;

                    case ID_ORDER:
                        selectedFragment = new OrderFragment();
                        break;

                    case ID_ADD:
                        selectedFragment = new AddFragment();
                        break;

                    case ID_POST:
                        selectedFragment = new PostFragment();
                        break;

                    case ID_PAYMENT:
                        selectedFragment = new PaymentFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedFragment).commit();

            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment selectedFragment = null;
                switch (item.getId()){
                    case ID_PROFILE:
                        selectedFragment = new ProfileFragment();
                        break;

                    case ID_ORDER:
                        selectedFragment = new OrderFragment();
                        break;

                    case ID_ADD:
                        selectedFragment = new AddFragment();
                        break;

                    case ID_POST:
                        selectedFragment = new PostFragment();
                        break;

                    case ID_PAYMENT:
                        selectedFragment = new PaymentFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedFragment).commit();

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {

            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });

        bottomNavigation.show(ID_PROFILE,false);
    }

}
