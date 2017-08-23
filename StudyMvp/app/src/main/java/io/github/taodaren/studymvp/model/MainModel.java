package io.github.taodaren.studymvp.model;

import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import io.github.taodaren.studymvp.presenter.IMainPresenter;
import io.github.taodaren.studymvp.view.ui.MainActivity;

/**
 * 业务具体处理，包括负责存储、检索、操纵数据等
 */

public class MainModel {
    private static final String URL = "http://www.weather.com.cn/adat/sk/101010100.html";
    IMainPresenter mIMainPresenter;

    public MainModel(IMainPresenter iMainPresenter) {
        this.mIMainPresenter = iMainPresenter;
    }

    public void loadData() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    MainModelBean mainModelBean = new MainModelBean();
                    JSONObject weatherInfo = response.getJSONObject("weatherinfo");
                    mainModelBean.setCity(weatherInfo.getString("city"));
                    mainModelBean.setWd(weatherInfo.getString("WD"));
                    mainModelBean.setWs(weatherInfo.getString("WS"));
                    mainModelBean.setTime(weatherInfo.getString("time"));
                    mIMainPresenter.loadDataSuccess(mainModelBean);
                    Log.e("taodaren", "onSuccess: " + mainModelBean.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("taodaren", "onFailure: ");
                mIMainPresenter.loadDataFailure("请求失败");
            }
        });
    }

}
