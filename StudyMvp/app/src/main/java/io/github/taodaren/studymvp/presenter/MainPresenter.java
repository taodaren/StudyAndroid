package io.github.taodaren.studymvp.presenter;

import io.github.taodaren.studymvp.model.MainModel;
import io.github.taodaren.studymvp.model.MainModelBean;
import io.github.taodaren.studymvp.view.MainView;

/**
 * View 和 Model 的桥梁，它从 Model 层检索数据后，返回给 View 层
 */

public class MainPresenter implements Presenter<MainView>, IMainPresenter {
    private MainView mMainView;
    private MainModel mMainModel;

    public MainPresenter(MainView view) {
        attachView(view);
        mMainModel = new MainModel(this);
    }

    public void loadData() {
        mMainView.showProgress();
        mMainModel.loadData();
    }

    @Override
    public void loadDataSuccess(MainModelBean mainModelBean) {
        mMainView.showData(mainModelBean);
        mMainView.hideProgress();
    }

    @Override
    public void loadDataFailure(String result) {
        mMainView.hideProgress();
    }

    @Override
    public void attachView(MainView view) {
        this.mMainView = view;
    }

    @Override
    public void detachView() {
        this.mMainView = null;
    }

}
