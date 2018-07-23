package teach.vietnam.asia.view.body;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.R;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.BaseActivity;


public class BodyActivity extends BaseActivity<BodyActivity> {

    private final String TAG = "BodyActivity";

    @BindView(R.id.btnHead)
    Button btnHead;

    @BindView(R.id.tvViet)
    TextView tvViet;

    @BindView(R.id.tvOther)
    TextView tvOther;

    @BindView(R.id.btnHair)
    Button btnHair;

    @BindView(R.id.btnEyebrow)
    Button btnEyebrow;

    @BindView(R.id.btnEye)
    Button btnEye;

    @BindView(R.id.btnCheek)
    Button btnCheek;

    @BindView(R.id.btnNeck)
    Button btnNeck;

    @BindView(R.id.btnChest)
    Button btnChest;

    @BindView(R.id.btnArm)
    Button btnArm;

    @BindView(R.id.btnHand)
    Button btnHand;

    @BindView(R.id.btnknee)
    Button btnknee;

    @BindView(R.id.btnFoot)
    Button btnFoot;

    @BindView(R.id.btnForehead)
    Button btnForehead;

    @BindView(R.id.btnEar)
    Button btnEar;

    @BindView(R.id.btnNose)
    Button btnNose;

    @BindView(R.id.btnMouth)
    Button btnMouth;

    @BindView(R.id.btnShoulder)
    Button btnShoulder;

    @BindView(R.id.btnHip)
    Button btnHip;

    @BindView(R.id.btnFinger)
    Button btnFinger;


    @BindView(R.id.btnWrist)
    Button btnWrist;


    @BindView(R.id.btnHeel)
    Button btnHeel;


    @BindView(R.id.btnToes)
    Button btnToes;


    AudioPlayer audio;


    @Override
    protected int getLayout() {
        return R.layout.body_layout;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.title_body));
        initData();
        if (!BuildConfig.DEBUG)
        FirebaseCrash.logcat(Log.INFO, TAG, "initView");
    }

    @OnClick(R.id.imgSpeak)
    public void actionImgSpeak() {
        speakNumber();
    }

    @OnClick(R.id.btnHead)
    public void actionBtnHead() {
        tvViet.setText(btnHead.getText().toString());
        tvOther.setText(btnHead.getTag().toString());
        speakNumber();
    }

    @OnClick(R.id.btnHair)
    public void actionBtnHair() {
        tvOther.setText(btnHair.getTag().toString());
        tvViet.setText(btnHair.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnEyebrow)
    public void actionEyebrow() {
        tvOther.setText(btnEyebrow.getTag().toString());
        tvViet.setText(btnEyebrow.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnEye)
    public void actionEye() {
        tvOther.setText(btnEye.getTag().toString());
        tvViet.setText(btnEye.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnCheek)
    public void actionCheek() {
        tvOther.setText(btnCheek.getTag().toString());
        tvViet.setText(btnCheek.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnNeck)
    public void actionNeck() {
        tvOther.setText(btnNeck.getTag().toString());
        tvViet.setText(btnNeck.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnChest)
    public void actionChest() {
        tvOther.setText(btnChest.getTag().toString());
        tvViet.setText(btnChest.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnArm)
    public void actionArm() {
        tvOther.setText(btnArm.getTag().toString());
        tvViet.setText(btnArm.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnHand)
    public void actionHand() {
        tvOther.setText(btnHand.getTag().toString());
        tvViet.setText(btnHand.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnknee)
    public void actionKnee() {
        tvOther.setText(btnknee.getTag().toString());
        tvViet.setText(btnknee.getText().toString());
        speakNumber();
    }


    @OnClick(R.id.btnFoot)
    public void actionFoot() {
        tvOther.setText(btnFoot.getTag().toString());
        tvViet.setText(btnFoot.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnForehead)
    public void actionForehead() {
        tvOther.setText(btnForehead.getTag().toString());
        tvViet.setText(btnForehead.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnEar)
    public void actionEar() {
        tvOther.setText(btnEar.getTag().toString());
        tvViet.setText(btnEar.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnNose)
    public void actionNose() {
        tvOther.setText(btnNose.getTag().toString());
        tvViet.setText(btnNose.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnMouth)
    public void actionMouth() {
        tvOther.setText(btnMouth.getTag().toString());
        tvViet.setText(btnMouth.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnShoulder)
    public void actionShoulder() {
        tvOther.setText(btnShoulder.getTag().toString());
        tvViet.setText(btnShoulder.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnHip)
    public void actionHip() {
        tvOther.setText(btnHip.getTag().toString());
        tvViet.setText(btnHip.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnFinger)
    public void actionFinger() {
        tvOther.setText(btnFinger.getTag().toString());
        tvViet.setText(btnFinger.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnWrist)
    public void actionWrist() {
        tvOther.setText(btnWrist.getTag().toString());
        tvViet.setText(btnWrist.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnHeel)
    public void actionHeel() {
        tvOther.setText(btnHeel.getTag().toString());
        tvViet.setText(btnHeel.getText().toString());
        speakNumber();
    }

    @OnClick(R.id.btnToes)
    public void actionToes() {
        tvOther.setText(btnToes.getTag().toString());
        tvViet.setText(btnToes.getText().toString());
        speakNumber();
    }


    private void initData() {
        audio = new AudioPlayer(BodyActivity.this);
        setData();
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


    private void speakNumber() {
        audio.speakWord(tvViet.getText().toString());
    }

    private void setData() {
        Resources res = getResources();
        Configuration conf = res.getConfiguration();

        if (lang.equals("ja"))
            conf.locale = new Locale("ja");
        else if (lang.equals("ko"))
            conf.locale = new Locale("ko");
        else if (lang.equals("fr"))
            conf.locale = new Locale("fr");
        else if (lang.equals("ru"))
            conf.locale = new Locale("ru");
        else
            conf.locale = Locale.ENGLISH;

        res.updateConfiguration(conf, null); // second arg null means don't chan


        btnHead.setTag(res.getString(R.string.head));
        btnHair.setTag(res.getString(R.string.hair));
        btnEyebrow.setTag(res.getString(R.string.eyebrow));
        btnEye.setTag(res.getString(R.string.eye));
        btnCheek.setTag(res.getString(R.string.cheek));
        btnNeck.setTag(res.getString(R.string.neck));
        btnChest.setTag(res.getString(R.string.chest));
        btnArm.setTag(res.getString(R.string.arm));


        btnHand.setTag(res.getString(R.string.hand));
        btnknee.setTag(res.getString(R.string.knee));
        btnFoot.setTag(res.getString(R.string.foot));
        btnForehead.setTag(res.getString(R.string.forehead));
        btnEar.setTag(res.getString(R.string.ear));

        btnNose.setTag(res.getString(R.string.nose));
        btnMouth.setTag(res.getString(R.string.mouth));
        btnShoulder.setTag(res.getString(R.string.shoulder));
        btnHip.setTag(res.getString(R.string.hip));
        btnFinger.setTag(res.getString(R.string.fingers));
        btnWrist.setTag(res.getString(R.string.wrist));
        btnHeel.setTag(res.getString(R.string.heel));
        btnToes.setTag(res.getString(R.string.toes));

//		((Button)getViewChild(R.id.btnHead)).setText(res.getString(R.string.head_vn));
        tvOther.setText(btnHead.getTag().toString());
        tvViet.setText(btnHead.getText().toString());
    }


}
