package teach.vietnam.asia.view.grammar;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.crash.FirebaseCrash;

import butterknife.BindView;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.base.BaseActivity;
import teach.vietnam.asia.view.grammar.detail.GrammarDetailActivity;

public class GrammarActivity extends BaseActivity<GrammarActivity> {

    private final String TAG = "GrammarActivity";
    @BindView(R.id.lstGrammar)
    ListView lstGrammar;

    @Override
    protected int getLayout() {
        return R.layout.activity_grammar;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.title_grammar));
        initData();
    }


    private void initData() {
//        String [] arrData = getResources().getStringArray(R.array.arr_grammar);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.arr_grammar, android.R.layout.simple_list_item_1);
        lstGrammar.setAdapter(adapter);
        lstGrammar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(GrammarActivity.this, GrammarDetailActivity.class);
                i.putExtra(Constant.INTENT_POSITION, position);
                GrammarActivity.this.startActivity(i);
            }
        });

        if (!BuildConfig.DEBUG)
            FirebaseCrash.logcat(Log.INFO, TAG, "initView");

    }

}
