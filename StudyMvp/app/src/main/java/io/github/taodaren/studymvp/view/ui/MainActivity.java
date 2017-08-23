package io.github.taodaren.studymvp.view.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.github.taodaren.studymvp.R;
import io.github.taodaren.studymvp.model.MainModelBean;
import io.github.taodaren.studymvp.presenter.MainPresenter;
import io.github.taodaren.studymvp.view.MainView;

/**
 * 由 Activity / Fragment 实现 View 里方法，包含一个 Presenter 的引用
 */

public class MainActivity extends AppCompatActivity implements MainView {
    private ProgressBar mProgressBar;
    private TextView mTextView;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMainPresenter = new MainPresenter(this);
        //制造延迟效果
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainPresenter.loadData();
            }
        }, 2000);

        showData(new MainModelBean());
    }

    @Override
    public void showData(MainModelBean mainModelBean) {
        String showData = getResources().getString(R.string.city) + mainModelBean.getCity() + "\n"
                + getResources().getString(R.string.wd) + mainModelBean.getWd() + "\n"
                + getResources().getString(R.string.ws) + mainModelBean.getWs() + "\n"
                + getResources().getString(R.string.time) + mainModelBean.getTime();
        mTextView.setText(showData);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.detachView();
        super.onDestroy();
    }

}
