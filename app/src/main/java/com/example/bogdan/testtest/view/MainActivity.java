package com.example.bogdan.testtest.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.bogdan.testtest.App;
import com.example.bogdan.testtest.Constants;
import com.example.bogdan.testtest.utils.ImageUtils;
import com.example.bogdan.testtest.R;
import com.example.bogdan.testtest.di.module.MainPageModule;
import com.example.bogdan.testtest.presenter.MainPagePresenter;
import com.example.bogdan.testtest.utils.Resizer;
import com.example.bogdan.testtest.view.common.ResizebleImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 03.06.16
 */
public class MainActivity extends BaseActivity implements MainPageView, View.OnClickListener {
    private static final int LAYOUT = R.layout.main_layout;

    @BindView(R.id.mainBackground) RelativeLayout background;
    @BindView(R.id.mainContainer) RelativeLayout container;

    @BindView(R.id.mainScroll) ScrollView scroll;

    @BindView(R.id.start) ResizebleImageView start;
    @BindView(R.id.middle) ResizebleImageView middle;
    @BindView(R.id.end) ResizebleImageView end;
    @BindView(R.id.sticks) ImageView sticks;
    @BindView(R.id.pin) ImageView pin;
    @BindView(R.id.metroStick) ImageView metroStick;
    @BindView(R.id.lighter) ImageView lighter;
    @BindView(R.id.posterFirst) ImageView poster1;
    @BindView(R.id.posterSecond) ImageView poster2;
    @BindView(R.id.posterThird) ImageView poster3;
    @BindView(R.id.posterFourth) ImageView poster4;

    @Inject
    MainPagePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        ButterKnife.bind(this);
        OverScrollDecoratorHelper.setUpOverScroll(scroll);
        setupViews();
        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected void setupComponent() {
        App.getAppComponent().plus(new MainPageModule(MainActivity.this)).inject(this);
    }

    private void setupViews() {
        setupBackground();

        Resizer.configureView(sticks,
                Constants.MAIN.WIDTH.STICKS,
                Constants.MAIN.HEIGHT.STICKS);
        Resizer.setPosition(sticks,
                Constants.MAIN.L_MARGIN.STICKS,
                Constants.MAIN.T_MARGIN.STICKS);
        sticks.setImageBitmap(ImageUtils.decodeBitmap(this,
                R.drawable.main_sticks,
                Constants.MAIN.WIDTH.STICKS,
                Constants.MAIN.HEIGHT.STICKS));

        Resizer.configureView(pin,
                Constants.MAIN.WIDTH.PIN,
                Constants.MAIN.HEIGHT.PIN);
        Resizer.setPosition(pin,
                Constants.MAIN.L_MARGIN.PIN,
                Constants.MAIN.T_MARGIN.PIN);
        pin.setImageBitmap(ImageUtils.decodeBitmap(this,
                R.drawable.main_pin,
                Constants.MAIN.WIDTH.PIN,
                Constants.MAIN.HEIGHT.PIN));

        Resizer.configureView(poster1,
                Constants.MAIN.WIDTH.POSTER,
                Constants.MAIN.HEIGHT.POSTER);
        Resizer.setPosition(poster1,
                Constants.MAIN.L_MARGIN.POSTER_1,
                Constants.MAIN.T_MARGIN.POSTER_1);

        Resizer.configureView(poster2,
                Constants.MAIN.WIDTH.POSTER,
                Constants.MAIN.HEIGHT.POSTER);
        Resizer.setPosition(poster2,
                Constants.MAIN.L_MARGIN.POSTER_2,
                Constants.MAIN.T_MARGIN.POSTER_2);

        Resizer.configureView(poster3,
                Constants.MAIN.WIDTH.POSTER,
                Constants.MAIN.HEIGHT.POSTER);
        Resizer.setPosition(poster3,
                Constants.MAIN.L_MARGIN.POSTER_3,
                Constants.MAIN.T_MARGIN.POSTER_3);

        Resizer.configureView(poster4,
                Constants.MAIN.WIDTH.POSTER,
                Constants.MAIN.HEIGHT.POSTER);
        Resizer.setPosition(poster4,
                Constants.MAIN.L_MARGIN.POSTER_4,
                Constants.MAIN.T_MARGIN.POSTER_4);

        Resizer.configureView(metroStick,
                Constants.MAIN.WIDTH.METRO_STICK,
                Constants.MAIN.HEIGHT.METRO_STICK);
        Resizer.setPosition(metroStick,
                Constants.MAIN.L_MARGIN.METRO_STICK,
                Constants.MAIN.T_MARGIN.METRO_STICK);
        metroStick.setImageBitmap(ImageUtils.decodeBitmap(this,
                R.drawable.main_metro_stick,
                Constants.MAIN.WIDTH.METRO_STICK,
                Constants.MAIN.HEIGHT.METRO_STICK));
        metroStick.startAnimation(AnimationUtils.loadAnimation(this, R.anim.main_metro_anim));

        Resizer.configureView(lighter,
                Constants.MAIN.WIDTH.LIGHTER,
                Constants.MAIN.HEIGHT.LIGHTER);
        Resizer.setPosition(lighter,
                Constants.MAIN.L_MARGIN.LIGHTER,
                Constants.MAIN.T_MARGIN.LIGHTER);
        lighter.setImageBitmap(ImageUtils.decodeBitmap(this,
                R.drawable.main_light,
                Constants.MAIN.WIDTH.LIGHTER,
                Constants.MAIN.HEIGHT.LIGHTER));
        lighter.startAnimation(AnimationUtils.loadAnimation(this, R.anim.main_light_anim));

        View btnNews = new View(this);
        btnNews.setId(NEWS);
        Resizer.configureView(btnNews,
                Constants.MAIN.WIDTH.BTN_NEWS,
                Constants.MAIN.HEIGHT.BTN_NEWS);
        Resizer.setPosition(btnNews,
                Constants.MAIN.L_MARGIN.BTN_NEWS,
                Constants.MAIN.T_MARGIN.BTN_NEWS);
        container.addView(btnNews);

        btnNews.setOnClickListener(this);
        setupSocialButton(Constants.MAIN.L_MARGIN.SOCIAL_BTN_1, FACEBOOK);
        setupSocialButton(Constants.MAIN.L_MARGIN.SOCIAL_BTN_2, TWITTER);
        setupSocialButton(Constants.MAIN.L_MARGIN.SOCIAL_BTN_3, GOOGLE);
        setupSocialButton(Constants.MAIN.L_MARGIN.SOCIAL_BTN_4, INSTAGRAM);
    }

    private void setupBackground() {

        background.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtils.decodeBitmap(this,
                R.drawable.main_background,
                Constants.MAIN.WIDTH.BACKGROUND,
                Constants.MAIN.HEIGHT.BACKGROUND)));

        Resizer.setPosition(scroll,
                Constants.MAIN.L_MARGIN.SCROLL,
                Constants.MAIN.T_MARGIN.SCROLL,
                Constants.MAIN.R_MARGIN.SCROLL,
                Constants.MAIN.B_MARGIN.SCROLL);

        start.setImageBitmap(ImageUtils.decodeBitmap(this,
                R.drawable.start,
                Resizer.resize(Constants.MAIN.WIDTH.START),
                Resizer.resize(Constants.MAIN.HEIGHT.START)));
        middle.setImageBitmap(ImageUtils.decodeBitmap(this,
                R.drawable.middle,
                Resizer.resize(Constants.MAIN.WIDTH.MIDDLE),
                Resizer.resize(Constants.MAIN.HEIGHT.MIDDLE)));
        end.setImageBitmap(ImageUtils.decodeBitmap(this,
                R.drawable.end,
                Resizer.resize(Constants.MAIN.WIDTH.END),
                Resizer.resize(Constants.MAIN.HEIGHT.END)));
    }

    private void setupSocialButton(int leftMargin, int id) {
        View btn = new View(this);
        btn.setId(id);
        Resizer.configureView(btn,
                Constants.MAIN.WIDTH.SOCIAL_BTN,
                Constants.MAIN.HEIGHT.SOCIAL_BTN);
        Resizer.setPosition(btn,
                leftMargin,
                Constants.MAIN.T_MARGIN.SOCIAL_BTN);
        container.addView(btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void showError(String message) {
        error(message);
    }

    @Override
    public void showPoster(List<Bitmap> posters) {
        ImageView[] images = {poster1, poster2, poster3, poster4};
        for (int i = 0; i < images.length; i++) {
            Resizer.into(this);
            images[i].setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.main_poster_frame,
                    null));
            Resizer.setPadding(images[i], 9, 9, 9, 9);
            images[i].setImageBitmap(posters.get(i));
        }
    }

    @Override
    public void showNews() {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        finish();
    }

    @Override
    public void openSocialPage(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case NEWS:
                presenter.onNewsClick();
                break;
            case FACEBOOK:
                presenter.onSocialPageClick(MainPageView.FACEBOOK);
                break;
            case TWITTER:
                // TODO: 15.06.16 set intent to start twitter page
                break;
            case GOOGLE:
                presenter.onSocialPageClick(MainPageView.GOOGLE);
                break;
            case INSTAGRAM:
                presenter.onSocialPageClick(MainPageView.INSTAGRAM);
                break;
        }
    }
}
