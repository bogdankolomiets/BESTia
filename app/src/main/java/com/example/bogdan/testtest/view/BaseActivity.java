package com.example.bogdan.testtest.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 16.06.16
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent();
    }

    protected abstract void setupComponent();

    protected void showLoading() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setTitle("Завантаження...");
        mProgressDialog.setMessage("Зачекайте");
        mProgressDialog.show();
    }

    protected void hideLoading() {
        mProgressDialog.dismiss();
    }

    protected void error(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
