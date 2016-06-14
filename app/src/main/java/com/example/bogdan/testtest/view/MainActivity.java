package com.example.bogdan.testtest.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.bogdan.testtest.App;
import com.example.bogdan.testtest.ImageUtils;
import com.example.bogdan.testtest.R;
import com.example.bogdan.testtest.di.MainPageModule;
import com.example.bogdan.testtest.presenter.MainPagePresenter;
import com.squareup.leakcanary.LeakCanary;

import java.util.List;

import javax.inject.Inject;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 03.06.16
 */
public class MainActivity extends AppCompatActivity implements MainPageView, View.OnClickListener {
    private final int LAYOUT = R.layout.main_layout;
    private final int FACEBOOK = 1;
    private final int TWITTER = 2;
    private final int GOOGLE = 3;
    private final int INSTAGRAM = 4;

    private ScrollView mScrollView;

    private ResizebleImageView start, mainLogo, partTwo, partThree, end, sticks, clip, poster1,
            poster2, poster3, poster4, lighter, metroStick, mainNews, mainMenu;

    private RelativeLayout background, mainContainer;

    @Inject
    MainPagePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().plus(new MainPageModule(MainActivity.this)).inject(this);
        setContentView(LAYOUT);
        mainContainer = (RelativeLayout) findViewById(R.id.mainContainer);
        mScrollView = (ScrollView) findViewById(R.id.mainScroll);
        mainLogo = (ResizebleImageView) findViewById(R.id.mainLogo);
        mainNews = (ResizebleImageView) findViewById(R.id.mainNews);
        mainMenu = (ResizebleImageView) findViewById(R.id.mainMenu);
        partTwo = (ResizebleImageView) findViewById(R.id.partTwo);
        partThree = (ResizebleImageView) findViewById(R.id.partThree);
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
        background = (RelativeLayout) findViewById(R.id.background);
        OverScrollDecoratorHelper.setUpOverScroll(mScrollView);
        setupComponents();
        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
    }

    private void setupComponents() {
        Resizer.into(this);
        background.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtils.decodeBitmap(this, R.drawable.main_background)));
        Resizer.setPosition(mScrollView, 0, -1000, 0, -1000);
        start.setImage(R.drawable.main_part_0);
        mainLogo.setImage(R.drawable.logo);
        mainNews.setImage(R.drawable.main_news);
        mainNews.setOnClickListener(this);
        mainMenu.setImage(R.drawable.menu);
        partTwo.setImage(R.drawable.main_part_2);
        partThree.setImage(R.drawable.main_part_3);
        end.setImage(R.drawable.main_part_4);
        sticks.configureView(459, 410, 106, 1610);
        sticks.setImage(R.drawable.main_sticks);
        clip.configureView(33, 44, 228, 3718);
        clip.setImage(R.drawable.main_pin);
        poster1.configureView(230, 318, 121, 1632);
        poster2.configureView(230, 318, 325, 1692);
        poster3.configureView(230, 318, 75, 4581);
        poster4.configureView(230, 318, 363, 4579);
        metroStick.configureView(200, 200, 148, 3650 + 50);
        metroStick.setImage(R.drawable.main_metro_stick);
        metroStick.startAnimation(AnimationUtils.loadAnimation(this, R.anim.main_metro_anim));
        lighter.configureView(100, 100, 394, 4035);
        lighter.setImage(R.drawable.main_light);
        lighter.startAnimation(AnimationUtils.loadAnimation(this, R.anim.main_light_anim));
        setupSocialButton(70, FACEBOOK);
        setupSocialButton(208, TWITTER);
        setupSocialButton(381, GOOGLE);
        setupSocialButton(554, INSTAGRAM);
    }

    private void setupSocialButton(int leftMargin, int id) {
        View btn = new View(this);
        btn.setId(id);
        Resizer.configureView(btn, 138, 138);
        Resizer.setPosition(btn, leftMargin, 5255, 0, 0);
        mainContainer.addView(btn);
        btn.setOnClickListener(this);
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
        ResizebleImageView[] images = {poster1, poster2, poster3, poster4};
        for (int i = 0; i < images.length; i++) {
            images[i].setImageBitmap(posters.get(i));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainNews:
                Intent intent = new Intent(this, NewsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();
                break;
            case FACEBOOK:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/BESTia2015")));
                break;
            case TWITTER:

                break;
            case GOOGLE:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/117373699485632124956/posts")));
                break;
            case INSTAGRAM:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/bestia2015/")));
                break;
        }
    }
}
