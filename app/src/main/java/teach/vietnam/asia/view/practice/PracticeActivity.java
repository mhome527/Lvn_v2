package teach.vietnam.asia.view.practice;

import android.content.Intent;

import butterknife.OnClick;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.BaseActivity;
import teach.vietnam.asia.view.practice.detail.PracticeDetailActivity;

public class PracticeActivity extends BaseActivity<PracticeActivity> {

    @Override
    protected int getLayout() {
        return R.layout.practice_layout;
    }

    @Override
    protected void initView() {
    }

    @OnClick(R.id.btnFruit)
    public void actionFruit() {
        Intent i = new Intent(PracticeActivity.this, PracticeDetailActivity.class);
        i.putExtra(Constant.INTENT_KIND, 1);
        startActivity(i);
    }

    @OnClick(R.id.btnVegetables)
    public void actionVegetables() {
        Intent i = new Intent(PracticeActivity.this, PracticeDetailActivity.class);
        i.putExtra(Constant.INTENT_KIND, 2);
        startActivity(i);
    }

    @OnClick(R.id.btnFood)
    public void actionFood() {
        Intent i = new Intent(PracticeActivity.this, PracticeDetailActivity.class);
        i.putExtra(Constant.INTENT_KIND, 3);
        startActivity(i);
    }

    @OnClick(R.id.btnAnimal)
    public void actionAnimal() {
        Intent i = new Intent(PracticeActivity.this, PracticeDetailActivity.class);
        i.putExtra(Constant.INTENT_KIND, 4);
        startActivity(i);
    }

    @OnClick(R.id.btnFurniture)
    public void actionFurniture() {
        Intent i = new Intent(PracticeActivity.this, PracticeDetailActivity.class);
        i.putExtra(Constant.INTENT_KIND, 5);
        startActivity(i);
    }

    @OnClick(R.id.btnOther)
    public void actionOther() {
        Intent i = new Intent(PracticeActivity.this, PracticeDetailActivity.class);
        i.putExtra(Constant.INTENT_KIND, 12);
        startActivity(i);
    }

}
