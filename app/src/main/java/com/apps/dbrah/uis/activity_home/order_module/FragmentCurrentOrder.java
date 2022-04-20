package com.apps.dbrah.uis.activity_home.order_module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.apps.dbrah.R;
import com.apps.dbrah.adapter.OrderAdapter;
import com.apps.dbrah.databinding.FragmentCurrentOrderBinding;
import com.apps.dbrah.mvvm.FragmentCurrentOrderMvvm;
import com.apps.dbrah.mvvm.GeneralMvvm;
import com.apps.dbrah.uis.activity_base.BaseFragment;
import com.apps.dbrah.uis.activity_current_order_details.CurrentOrderDetailsActivity;
import com.apps.dbrah.uis.activity_home.HomeActivity;

import java.util.ArrayList;


public class FragmentCurrentOrder extends BaseFragment {
    private GeneralMvvm generalMvvm;
    private FragmentCurrentOrderMvvm mvvm;
    private HomeActivity activity;
    private FragmentCurrentOrderBinding binding;
    private OrderAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (HomeActivity) context;
    }

    public static FragmentCurrentOrder newInstance() {
        return new FragmentCurrentOrder();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_current_order, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    private void initView() {
        generalMvvm = ViewModelProviders.of(activity).get(GeneralMvvm.class);
        mvvm = ViewModelProviders.of(activity).get(FragmentCurrentOrderMvvm.class);
        adapter = new OrderAdapter( activity, this);
        binding.recViewLayout.recView.setLayoutManager(new LinearLayoutManager(activity));
        binding.recViewLayout.recView.setAdapter(adapter);

    }


    public void navigateToDetails() {
        Intent intent = new Intent(activity, CurrentOrderDetailsActivity.class);
        startActivity(intent);
    }
}