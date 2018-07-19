package teach.vietnam.asia.view.search;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.BaseActivity;
import teach.vietnam.asia.view.ICallback;

public class SearchAllActivity extends BaseActivity<SearchAllActivity> {

    private final String TAG = "SearchAllActivity";
    private final int REQ_CODE_SPEECH_INPUT = 1000;

    @BindView(R.id.lstSearch)
    ListView lstSearch;

    @BindView(R.id.edtSearch)
    EditText edtSearch;

    private SearchAllAdapter adapter;
    private AudioPlayer audio;
    private SearchAllPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.search_all_layout;
    }

    @Override
    protected void initView() {
        presenter = new SearchAllPresenter(activity);
        audio = new AudioPlayer(SearchAllActivity.this);
        setInitData();
    }

    @OnClick(R.id.imgRecording)
    public void actionRecording() {
        Utility.promptSpeechInput(SearchAllActivity.this, REQ_CODE_SPEECH_INPUT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (audio != null)
            audio.stopAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT:
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtSearch.setText(result.get(0));
                }
                break;
        }
    }


    private void setInitData() {
        edtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String text = edtSearch.getText().toString().toLowerCase();
                if (adapter != null)
                    adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });

//        lstSearch.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//                String phrases;
//                if (Constant.isPro) {
//                    phrases = String.format(adapter.getItem(position).getVi(), adapter.getItem(position).getDefault_word(), lang);
//                    speakPhrases(phrases);
//                } else {
//                    Log.i(TAG, "onItemClick NOT PREMIUM");
//                }
//            }
//        });

        presenter.loadDataAll(new ICallback<List<WordEntity>>() {
            @Override
            public void onCallback(List<WordEntity> data) {
                if (data != null && data.size() > 0) {
                    adapter = new SearchAllAdapter(SearchAllActivity.this, data);
                    lstSearch.setAdapter(adapter);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                             adapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    finish();
                }
            }

        });

    }

    private void speakPhrases(String phrases) {
        String soundName;

        try {
            soundName = phrases.replaceAll("!", "").replaceAll("\\?", "").replaceAll("[.]", "").replaceAll(",", "").toLowerCase();
            soundName = android.text.Html.fromHtml(soundName).toString();
            audio.speakWord(soundName);
        } catch (Exception e) {
            Log.e(SearchAllActivity.class, "speakPhrases Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
