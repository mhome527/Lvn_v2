package teach.vietnam.asia.view.search;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.base.BaseActivity;
import teach.vietnam.asia.view.action.ICallback;

public class SearchWordsActivity extends BaseActivity<SearchWordsActivity> {

    private final int REQ_CODE_SPEECH_INPUT = 1000;

    @BindView(R.id.lstSearch)
    ListView lstSearch;

    @BindView(R.id.edtSearch)
    EditText edtSearch;

    private SearchAdapter adapter;
    private int position;
    private List<WordEntity> lstData;
    SearchPresenter presenter;
//    private String lang;


    @Override
    protected int getLayout() {
        return R.layout.search_layout;
    }

    @Override
    protected void initView() {
        presenter = new SearchPresenter(activity);
        setInitData();
        position = getIntent().getIntExtra(Constant.INTENT_POSITION, 0);
    }

    @OnClick(R.id.imgRecording)
    public void actionRecording() {
        Utility.promptSpeechInput(SearchWordsActivity.this, REQ_CODE_SPEECH_INPUT);
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtSearch.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void setInitData() {
//        hideMenuHome();

        edtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String text = edtSearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });

        lstSearch.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                Intent intentData = new Intent();
                intentData.putExtra(Constant.INTENT_POSITION, position);
                intentData.putExtra(Constant.INTENT_WORD, lstData.get(pos).getVi());
                SearchWordsActivity.this.setResult(RESULT_OK, intentData);
                SearchWordsActivity.this.finish();
            }
        });

        presenter.loadData(new ICallback<List<WordEntity>>() {
            @Override
            public void onCallback(List<WordEntity> data) {
                lstData = data;
                if (lstData != null && lstData.size() > 0) {
                    adapter = new SearchAdapter(SearchWordsActivity.this, lstData);
                    lstSearch.setAdapter(adapter);
                    // adapter.notifyDataSetChanged();
                } else {
                    finish();
                }
            }

        });
    }

}
