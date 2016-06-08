package com.example.bogdan.testtest.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.bogdan.testtest.App;
import com.example.bogdan.testtest.R;
import com.example.bogdan.testtest.di.MainPageModule;
import com.example.bogdan.testtest.presenter.MainPagePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 03.06.16
 */
public class MainActivity extends AppCompatActivity implements MainPageView {
    private final static int LAYOUT = R.layout.main_layout;

    private ResizebleImageView start, end, sticks, clip, poster1, poster2, poster3, poster4, lighter, metroStick;

    @Inject
    MainPagePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().plus(new MainPageModule(MainActivity.this)).inject(this);
        setContentView(LAYOUT);
        sticks = (ResizebleImageView) findViewById(R.id.sticks);
        clip = (ResizebleImageView) findViewById(R.id.clip);
        poster1 = (ResizebleImageView) findViewById(R.id.posterFirst);
        poster2 = (ResizebleImageView) findViewById(R.id.posterSecond);
        poster3 = (ResizebleImageView) findViewById(R.id.posterThird);
        poster4 = (ResizebleImageView) findViewById(R.id.posterFourth);
        metroStick = (ResizebleImageView) findViewById(R.id.metroStick);
        lighter = (ResizebleImageView) findViewById(R.id.lighter);
        start = (ResizebleImageView) findViewById(R.id.partZero);
        end = (ResizebleImageView) findViewById(R.id.partFour);
        setupComponents();
        presenter.onCreate(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        System.out.println("CLipper width + height " + clip.getWidth() + "  " + clip.getHeight());
    }

    private void setupComponents() {
        sticks.configureView(459, 410, 106, 1610);
        clip.configureView(33, 44, 228, 3718);
        poster1.configureView(230, 318, 121, 1632);
        poster2.configureView(230, 318, 325, 1692);
        poster3.configureView(230, 318, 75, 4581);
        poster4.configureView(230, 318, 363, 4579);
//        start.configureView(0, 1023, 0, -1000);
        metroStick.configureView(200, 200, 148, 3650 + 50);
        metroStick.startAnimation(AnimationUtils.loadAnimation(this, R.anim.main_metro_anim));
        lighter.configureView(100, 100, 394, 4035);
        lighter.startAnimation(AnimationUtils.loadAnimation(this, R.anim.main_light_anim));
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showPoster(List<Bitmap> posters) {
        ImageView[] images = {poster1, poster2, poster3, poster4};
        for (int i = 0; i < images.length; i++) {
            images[i].setImageBitmap(posters.get(i));
        }
    }
}
