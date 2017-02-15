package teach.vietnam.asia.view.dashboard;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.DashboardEntity;
import teach.vietnam.asia.utils.Common;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.BaseActivity;
import teach.vietnam.asia.view.alphabet.AlphabetActivity;


public class DashboardActivity extends BaseActivity<DashboardActivity> {

    private String TAG = "DashboardActivity";

    List<DashboardEntity> listData;

    @BindView(R.id.gridView)
    GridView gridView;

    @Override
    protected int getLayout() {
        return R.layout.dashboard_layout;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView text: " + Constant.MY_TEXT);
        setTitle(getString(R.string.title_dashboard));
        createData();

        if (Common.isTablet(activity))
            gridView.setNumColumns(3);
        else
            gridView.setNumColumns(2);

        gridView.setAdapter(new DashboardAdapter(this, listData));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity2(AlphabetActivity.class);
                        break;
//                    case 1:
//                        startActivity2(DateActivity.class);
//                        break;
//                    case 2:
//                        startActivity2(WordActivity.class);
//                        break;
//                    case 3:
//                        startActivity2(PhraseActivity.class);
//                        break;
//                    case 4:
//                        startActivity2(KanjiActivity.class);
//                        break;
//                    case 5:
//                        startActivity2(GrammarActivity.class);
//                        break;
//                    case 6:
//                        startActivity2(NumberActivity.class);
//                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick(R.id.llOtherApp)
    public void actionOtherApp() {
        Utility.installVnApp(activity);
    }

    private void createData() {
        listData = new ArrayList<>();
        listData.add(new DashboardEntity(R.drawable.ic_alphabet, getString(R.string.title_alphabet)));
        listData.add(new DashboardEntity(R.drawable.ic_dates, getString(R.string.title_date)));
        listData.add(new DashboardEntity(R.drawable.ic_animal, getString(R.string.title_word)));
        listData.add(new DashboardEntity(R.drawable.ic_phrase, getString(R.string.title_phrase)));
        listData.add(new DashboardEntity(R.drawable.ic_kanji, getString(R.string.title_kanji)));
        listData.add(new DashboardEntity(R.drawable.ic_grammar, getString(R.string.title_grammar)));
        listData.add(new DashboardEntity(R.drawable.ic_number, getString(R.string.title_counter)));
//        listData.add(new DashboardEntity(R.drawable.button_word_on, getString(R.string.title_coming_soon)));
    }

}
