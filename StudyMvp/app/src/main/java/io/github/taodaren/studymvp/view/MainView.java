package io.github.taodaren.studymvp.view;

import io.github.taodaren.studymvp.model.MainModelBean;

/**
 * 处理业务需要哪些方法都写在该接口
 */

public interface MainView {

    void showData(MainModelBean mainModelBean);

    void showProgress();

    void hideProgress();

}
