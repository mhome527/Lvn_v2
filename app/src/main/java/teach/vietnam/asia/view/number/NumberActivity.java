package teach.vietnam.asia.view.number;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.R;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.NumberToWord;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.BaseActivity;

public class NumberActivity extends BaseActivity<NumberActivity> {

    private final String TAG = "NumberActivity";

    @BindView(R.id.edtNumber)
    EditText edtNumber;

    @BindView((R.id.tvNumber))
    TextView tvNumber;

    @BindView((R.id.gridWords))
    GridView gridWords;


    private NumberAdapter adapter;
    private AudioPlayer audio;

    private final int REQ_CODE_SPEECH_INPUT = 1002;
    private int arrNumber[];

    @Override
    protected int getLayout() {
        return R.layout.number_layout;
    }

    @Override
    protected void initView() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(true); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(true); // remove the icon
            actionBar.setDisplayShowTitleEnabled(true); // remove title
//            toolbarTitle.setText(getString(R.string.title_alphabet));
            actionBar.setTitle(getString(R.string.title_button_number));
        }

        audio = new AudioPlayer(NumberActivity.this);
        arrNumber = getResources().getIntArray(R.array.number);
        adapter = new NumberAdapter(NumberActivity.this, arrNumber);
        initData();
    }

    @OnClick(R.id.imgSpeak)
    public void actionSpeak() {
        if (!tvNumber.getText().toString().trim().equals(""))
            audio.speakWord(tvNumber.getText().toString().trim());
    }

    @OnClick(R.id.imgRecording)
    public void actionRecording() {
        Utility.promptSpeechInput(NumberActivity.this, REQ_CODE_SPEECH_INPUT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (audio != null)
            audio.stopAll();
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String text;
        long number = -1;

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    try {
                        text = Utility.stripNonDigits(result.get(0).trim());
                        number = Long.parseLong(text);
                    } catch (Exception e) {
                        Toast.makeText(NumberActivity.this, NumberActivity.this.getString(R.string.talk_number), Toast.LENGTH_LONG).show();
                    }
                    if (number >= 0)
                        edtNumber.setText(number + "");
                }
                break;
            }

        }
    }

    private void initData() {
        try {
            gridWords.setAdapter(adapter);
            gridWords.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                    String word;
                    // String strAudio = "sound/" + lstNumberEx.get(position).getSound() + ".mp3";
                    //
                    edtNumber.setText(arrNumber[position] + "");
                    //
                    // ULog.i(NumberActivity.class, "audio:" + strAudio);
                    // audio.playSound(strAudio);
                    word = NumberToWord.getWordFromNumber(arrNumber[position]);
                    Log.i(TAG, "onItemClick number:" + word);
                    audio.speakWord(word);
                }
            });

            edtNumber.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String word;
                    try {
                        Log.i(TAG, "number:" + edtNumber.getText().toString());
                        word = NumberToWord.getWordFromNumber(edtNumber.getText().toString());
                        tvNumber.setText(word);
                    } catch (Exception e) {
                        Log.e(TAG, " Error: " + e.getMessage());
                    }
                    // ULog.i(NumberActivity.class, "word:" + edtNumber.getText().toString());

                }
            });

//			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//			imm.hideSoftInputFromWindow(edtNumber.getWindowToken(), 0);
//			hideKeyboard();
        } catch (Exception e) {
            Log.e(TAG, "initData Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

//	private void hideKeyboard() {
//	    InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//	    inputManager.hideSoftInputFromWindow(edtNumber.getWindowToken(), 0);
//	    // check if no view has focus:
//	    View view = this.getCurrentFocus();
//	    if (view != null) {
//	        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//	    }
//	}


//	private void speakNumber(String strNumber) {
//		int count = 0;
//		String soundName;
//
//		if (strNumber.equals(""))
//			return;
//
//		String[] strSound = strNumber.split(" ");
//
//		for (String name : strSound) {
//			soundName = Common.getNameSound(name);
//			ULog.i(NumberActivity.class, "speakNumber" + soundName);
//			if (!soundName.equals(""))
////				strSound[count] = "sound/" + "a1c" + ".mp3";
//			strSound[count] = "sound/" + soundName + ".mp3";
//
//			count++;
//		}
//		ULog.i(NumberActivity.class, "audioAll:" + strSound);
//
//		if (strSound.length > 0)
//			audio.playSound(strSound);
//	}

}
