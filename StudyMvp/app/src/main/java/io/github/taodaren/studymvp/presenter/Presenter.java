package io.github.taodaren.studymvp.presenter;

/**
 * Presenter 基类
 */

public interface Presenter<V> {

    /**
     * 绑定定义的 View
     *
     * @param view 你想把请求下来的数据实体类给哪个 View 就传入哪个 View
     */
    void attachView(V view);

    /**
     * 解除绑定 View
     */
    void detachView();

}
