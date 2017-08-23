package io.github.taodaren.studymvp.presenter;

import io.github.taodaren.studymvp.model.MainModelBean;

/**
 * 此接口作用是连接 Model
 */

public interface IMainPresenter {

    //加载请求数据成功，传入对应的实体类
    void loadDataSuccess(MainModelBean mainModelBean);

    //加载请求数据失败，就会传入失败信息
    void loadDataFailure(String result);

}
