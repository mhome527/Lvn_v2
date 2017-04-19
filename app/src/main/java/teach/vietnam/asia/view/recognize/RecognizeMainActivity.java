//package teach.vietnam.asia.view.recognize;
//
//import android.content.Intent;
//import android.speech.RecognizerIntent;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import teach.vietnam.asia.Constant;
//import teach.vietnam.asia.R;
//import teach.vietnam.asia.utils.Log;
//import teach.vietnam.asia.view.BaseActivity;
//import teach.vietnam.asia.view.ICallback;
//import teach.vietnam.asia.view.custom.MainMenuLayout;
//import teach.vietnam.asia.view.recognize.learn.LearnRecoginzeFragment;
//import teach.vietnam.asia.view.recognize.test.TestRecognizeFragment;
//
//
//public class RecognizeMainActivity extends BaseActivity<RecognizeMainActivity> implements FragmentManager.OnBackStackChangedListener {
//
//    private final String TAG = "RecognizeMainActivity";
//    private boolean mShowingBack = false;
//
//    @BindView(R.id.menuLayout)
//    MainMenuLayout menuLayout;
//
//    @BindView(R.id.lstRecognize)
//    ListView lstRecognize;
//
//    private ArrayList<String> lstData;
//    private int amount = 0;
//    public RecognizePresenter presenter;
//
//    @Override
//    protected int getLayout() {
//        return R.layout.recognize_main_layout;
//    }
//
//    @Override
//    protected void initView() {
//        Log.i(RecognizeMainActivity.class, "initView");
//
//        // Monitor back stack changes to ensure the action bar shows the appropriate button (either "photo" or "info").
//        getSupportFragmentManager().addOnBackStackChangedListener(this);
//        presenter = new RecognizePresenter(activity);
//        /////
//        setInitData();
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (menuLayout != null && menuLayout.isMenuShown()) {
//            menuLayout.toggleMenu();
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//
//    private Fragment getFragment(Class cls, int currPage) {
//        try {
//            if (cls.isAssignableFrom(LearnRecoginzeFragment.class)) {
//                return LearnRecoginzeFragment.newInstance(amount, currPage);
//            } else {
//                return TestRecognizeFragment.newInstance(currPage);
//            }
//        } catch (Exception e) {
//            Log.e(RecognizeMainActivity.class, "getFragment Error:" + e.getMessage());
//        }
//        return null;
//    }
//
//    @Override
//    public void onBackStackChanged() {
//        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case Constant.REQ_CODE_SPEECH_INPUT:
//                if (resultCode == RESULT_OK && null != data) {
//
//                    Fragment fragment;
//                    fragment = getSupportFragmentManager().findFragmentById(R.id.container);
//                    if (fragment != null && fragment instanceof LearnRecoginzeFragment) {
//                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                        ((LearnRecoginzeFragment) fragment).setTextVoid(result.get(0));
//                    }
//                }
//                break;
//        }
//    }
//
//    public void showMenu() {
//        menuLayout.toggleMenu();
//    }
//
//    public void hideMenu() {
//        if (menuLayout != null && menuLayout.isMenuShown()) {
//            menuLayout.toggleMenu();
//        }
//    }
//
//    private void setInitData() {
//        lstData = new ArrayList<>();
//
//        String initData = pref.getStringValue("", Constant.JSON_RECOGNIZE_NAME);
//        Log.i(RecognizeMainActivity.class, "setInit: " + initData);
//
//        lstRecognize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                setPageLearn(pos);
//            }
//        });
//
//        presenter.loadGroup(new ICallback<List<String>>() {
//            @Override
//            public void onCallback(List<String> data) {
//                int num = data.size();
//                if (num > 0) {
//                    lstData.addAll(data);
//                    MenuRecognizeAdapter adapter = new MenuRecognizeAdapter(RecognizeMainActivity.this, lstData);
//                    lstRecognize.setAdapter(adapter);
//
//                    if (savedInstanceState == null) {
//                        Log.i(TAG, "Load data, num:" + num);
//                        int currPage = pref.getIntValue(0, Constant.PREF_PAGE);
//
//                        getSupportFragmentManager().beginTransaction().add(R.id.container, LearnRecoginzeFragment.newInstance(num, currPage)).commit();
//                    } else {
//                        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
//                    }
//
//                    amount = num;
//                } else {
//                    Log.i(TAG, "Load data error!!!, num= 0");
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
//    private void setPageLearn(int number) {
//        Fragment fragment;
//        fragment = getSupportFragmentManager().findFragmentById(R.id.container);
//        if (fragment != null && fragment instanceof LearnRecoginzeFragment) {
//            ((LearnRecoginzeFragment) fragment).setCurrentPage(number);
//        } else {
//            onFragmentInteraction(LearnRecoginzeFragment.class, number);
//            Log.i(RecognizeMainActivity.class, "Test: " + number);
//        }
//        hideMenu();
//    }
//
//    public void onFragmentInteraction(Class cls, int currPage) {
//
//        Fragment fr;
//        if (mShowingBack) {
//            getFragmentManager().popBackStack();
//            return;
//        }
//        // Flip to the back.
//        mShowingBack = true;
//        fr = getFragment(cls, currPage);
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
////        ft.setCustomAnimations(R.anim.card_flip_left_in, R.anim.card_flip_left_out, R.anim.card_flip_right_in, R.anim.card_flip_right_out);
//        ft.replace(R.id.container, fr);
//        // Add this transaction to the back stack, allowing users to press Back to get to the front of the card.
//        ft.addToBackStack(null);
//        ft.commit();
//    }
//
//}
