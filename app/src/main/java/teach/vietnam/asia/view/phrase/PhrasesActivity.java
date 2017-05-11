//package teach.vietnam.asia.view.phrase;
//
//import android.content.Intent;
//import android.os.Handler;
//import android.speech.RecognizerIntent;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import teach.vietnam.asia.Constant;
//import teach.vietnam.asia.R;
//import teach.vietnam.asia.entity.WordEntity;
//import teach.vietnam.asia.sound.AudioPlayer;
//import teach.vietnam.asia.sound.IAudioPlayer;
//import teach.vietnam.asia.utils.Log;
//import teach.vietnam.asia.utils.Utility;
//import teach.vietnam.asia.view.BaseActivity;
//import teach.vietnam.asia.view.ICallback;
//
//
//public class PhrasesActivity extends BaseActivity<PhrasesActivity> implements IAudioPlayer {
//    public static final int REQUEST_CODE_SEARCH = 500;
//    private final int REQUEST_CODE_SPEECH_INPUT = 1000;
//
//    @BindView(R.id.edtSearch)
//    EditText edtSearch;
//
//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.ckbSpeed)
//    CheckBox ckbSpeed;
//
//    @BindView(R.id.tvHint)
//    TextView tvhint;
//
//    public boolean isClick = false;
//
//    public boolean isSlowly = false;
//    private PhrasesAdapter2 adapter;
//
//    private List<WordEntity> lstData;
//
//    private AudioPlayer audio;
//    private PhrasesPresenter presenter;
//
//    @Override
//    protected int getLayout() {
//        return R.layout.phrases_layout;
//    }
//
//    @Override
//    protected void initView() {
//
//        presenter = new PhrasesPresenter(activity);
//        edtSearch.clearFocus();
//        // tvText1.setText(Html
//        // .fromHtml("<p>Description here dsf sdf sd <u>underline   </u>f asfd sadf <font size=\"3\" color=\"red\">This is some text!</font>dfas fsa fsd sdf asdf sdf sdf sdaf sdfsd</p>"));
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//
//        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
//        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(mLayoutManager);
//
//        // Disabled nested scrolling since Parent scrollview will scroll the content.
//        recyclerView.setNestedScrollingEnabled(false);
//
//        initData();
//    }
//
//    @OnClick(R.id.imgRecording)
//    public void actionRecording(){
//        Utility.promptSpeechInput(PhrasesActivity.this, REQUEST_CODE_SPEECH_INPUT);
//    }
//
//    @OnClick(R.id.llSpeed)
//    public void actionSpeed(){
////        if (isSlowly) {
////            isSlowly = false;
////            ckbSpeed.setChecked(false);
////            adapter.setSlowly(false);
////        } else {
////            isSlowly = true;
////            ckbSpeed.setChecked(true);
////            adapter.setSlowly(true);
////        }
//    }
//
//   @OnClick(R.id.ckbSpeed)
//    public void actionCkbSpeed(){
////       if (ckbSpeed.isChecked()) {
////           isSlowly = true;
////           adapter.setSlowly(true);
////       } else {
////           isSlowly = false;
////           adapter.setSlowly(false);
////       }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//            isClick = false;
//            if (requestCode == REQUEST_CODE_SEARCH && resultCode == RESULT_OK) {
//                int pos = data.getIntExtra(Constant.INTENT_POSITION, -1);
//                String word = data.getStringExtra(Constant.INTENT_WORD);
//                if (pos > -1) {
//                    lstData.get(pos).setDefault_word(word);
//                    adapter.notifyDataSetChanged();
//                } else
//                    Log.i(PhrasesActivity.class, "dont get word");
//            } else if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK) {
//                if (data != null) {
//                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                    edtSearch.setText(result.get(0));
//                }
//            }
//        } catch (Exception e) {
//            Log.e(PhrasesActivity.class, "onActivityResult Error: " + e.getMessage());
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
////        if (adapter != null && adapter.audio != null)
////            adapter.audio.stopAll();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        isClick = false;
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//    private void initData() {
//        audio = new AudioPlayer(PhrasesActivity.this);
//
////        edtSearch.addTextChangedListener(new TextWatcher() {
////
////            @Override
////            public void afterTextChanged(Editable arg0) {
////                String text = edtSearch.getText().toString().toLowerCase();
////                if (text.equals("")) {
////                    Log.e(PhrasesActivity.class, "text empty");
////                    return;
////                }
////                adapter.filter(text);
////            }
////
////            @Override
////            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
////            }
////
////            @Override
////            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
////            }
////        });
//
////        lstPhrases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////
////            @Override
////            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
////                String phrases;
////                if (Constant.isPro) {
////                    phrases = String.format(adapter.getItem(position).getVi(), adapter.getItem(position).getDefault_word());
////                    speakPhrases(phrases);
////                }
////
////            }
////        });
//
//        presenter.loadData(new ICallback<List<WordEntity>>() {
//            @Override
//            public void onCallback(List<WordEntity> data) {
//                if (isFinishing()) {
//                    return;
//                }
//                lstData = data;
//                if (lstData != null && lstData.size() > 0) {
//                    Log.i(PhrasesActivity.class, "load data size:" + lstData.size());
////                    adapter = new PhrasesAdapter(PhrasesActivity.this, lstData, PhrasesActivity.this);
//                    adapter = new PhrasesAdapter2(lstData);
//                    recyclerView.setAdapter(adapter);
//                } else {
//                    Log.e(PhrasesActivity.class, "Load data Error");
//                    finish();
//                }
//            }
//
//            @Override
//            public void onFail(String err) {
//
//            }
//        });
//    }
//
//    @Override
//    public void showWord(String word, boolean visible) {
//        if (!isFinishing()) {
//            if (visible)
//                tvhint.setVisibility(View.VISIBLE);
//            else {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        tvhint.setVisibility(View.GONE);
//                    }
//                }, 1000);
//            }
//
//            tvhint.setText(word);
//        }
//    }
//
//    private void speakPhrases(String phrases) {
//        String soundName;
//
//        try {
//            soundName = phrases.replaceAll("!", "").replaceAll("\\?", "").replaceAll("[.]", "").replaceAll(",", "").toLowerCase();
//            soundName = android.text.Html.fromHtml(soundName).toString();
//            audio.speakWord(soundName);
//        } catch (Exception e) {
//            Log.e(PhrasesActivity.class, "speakPhrases Error: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}
