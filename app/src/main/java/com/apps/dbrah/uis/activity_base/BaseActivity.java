package com.apps.dbrah.uis.activity_base;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.apps.dbrah.databinding.ToolbarBinding;
import com.apps.dbrah.language.Language;
import com.apps.dbrah.model.UserModel;
import com.apps.dbrah.model.UserSettingsModel;
import com.apps.dbrah.preferences.Preferences;

import io.paperdb.Paper;


public class BaseActivity extends AppCompatActivity {

    public static final String READ_REQ = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String WRITE_REQ = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String CAM_REQ = Manifest.permission.CAMERA;
    public static final String fineLocPerm = Manifest.permission.ACCESS_FINE_LOCATION;


    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {


    }

    protected String getLang() {
        Paper.init(this);
        String lang = Paper.book().read("lang", "ar");
        return lang;
    }

    protected UserModel getUserModel() {
        Preferences preferences = Preferences.getInstance();
        return preferences.getUserData(this);
    }

    protected void setUserModel(UserModel userModel) {
        Preferences preferences = Preferences.getInstance();
        preferences.createUpdateUserData(this, userModel);
    }


    public void setUserSettings(UserSettingsModel userSettingsModel) {
        Preferences preferences = Preferences.getInstance();
        preferences.create_update_user_settings(this, userSettingsModel);
    }

    public UserSettingsModel getUserSettings() {
        Preferences preferences = Preferences.getInstance();
        return preferences.getUserSettings(this);
    }


    public View setUpToolbar(ToolbarBinding binding, String title, int background, int arrowTitleColor, int arrowBg,boolean action) {
        binding.setLang(getLang());
        binding.setTitle(title);
        binding.arrow.setColorFilter(ContextCompat.getColor(this, arrowTitleColor));
        binding.arrow.setBackgroundResource(arrowBg);
        binding.tvTitle.setTextColor(ContextCompat.getColor(this, arrowTitleColor));
        binding.toolbar.setBackgroundResource(background);
        if(action){
            binding.llBack.setOnClickListener(v -> finish());

        }
        return binding.llBack;
    }


}