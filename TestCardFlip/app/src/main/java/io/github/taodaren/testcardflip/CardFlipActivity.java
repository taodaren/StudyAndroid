package io.github.taodaren.testcardflip;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

public class CardFlipActivity extends Activity implements FragmentManager.OnBackStackChangedListener {
    private Handler mHandler = new Handler();//一个 handler 对象，用于延迟 UI 操作
    private boolean mShowingBack = false;//是否显示卡片的背面（否则显示前面）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);

        if (savedInstanceState == null) {//如果没有保存实例状态，添加一个 fragment 表示前面的 activity
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardBeforeFragment())
                    .commit();
        } else {//如果有保存实例状态，这个 fragment 已经被添加到 activity 中
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

        //监控返回堆栈变化，以确保操作栏（action bar）显示适当的按钮（"photo" or "info"）
        getFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //添加一个 "photo" 或 "finish" 其中一个按钮到 action bar，取决于当前选中页面
        MenuItem item = menu.add(Menu.NONE, R.id.action_flip, Menu.NONE,
                mShowingBack
                        ? R.string.action_photo
                        : R.string.action_info);
        item.setIcon(mShowingBack
                ? R.drawable.ic_action_photo
                : R.drawable.ic_action_info);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                return true;
            case R.id.action_flip:
                flipCard();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }
        //向后翻转
        mShowingBack = true;
        //创建并提交一个新的 Fragment 事务，用于在卡片后面添加 Fragment。使用自定义动画，并且加入 Fragment 管理器回退栈
        getFragmentManager().beginTransaction()
                //用动画器资源呈现卡片自前向后的旋转效果替换默认的 Fragment 动画
                //当翻转到前面的时候动画器资源也可以呈现自后向前的旋转效果（例如按下系统返回键时）
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                //用一个 Fragment 替换任何当前在容器布局内的 Fragment 来呈现下一页（通过仅自增的变量 currentPage 来表示）
                .replace(R.id.container, new CardAfterFragment())
                .addToBackStack(null)//添加这个事务到回退栈，允许用户来按下返回按钮来回退到卡片正面
                .commit();//提交完成事务

        //延迟一个无效的 options menu（在当前设备，位于 action bar）
        //这不能立即完成，因为事务可能尚未提交
        //提交是异步的，因为它们发布了主线程的消息循环
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        //当返回栈改变时，选项菜单无效（action bar）
        invalidateOptionsMenu();
    }

}
