package teach.vietnam.asia.view.dashboard;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.DashboardEntity;
import teach.vietnam.asia.utils.Common;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.BaseActivity;
import teach.vietnam.asia.view.alphabet.AlphabetActivity;
import teach.vietnam.asia.view.body.BodyActivity;
import teach.vietnam.asia.view.dashboard.language.LanguageAdapter;
import teach.vietnam.asia.view.dashboard.language.OnItemClickListener;
import teach.vietnam.asia.view.foods.FoodActivity;
import teach.vietnam.asia.view.grammar.detail.GrammarDetailActivity;
import teach.vietnam.asia.view.number.NumberActivity;
import teach.vietnam.asia.view.phrase.PhrasesActivity;
import teach.vietnam.asia.view.places.PlaceActivity;
import teach.vietnam.asia.view.practice.PracticeActivity;
import teach.vietnam.asia.view.recognizes.RecognizeMainActivity;
import teach.vietnam.asia.view.translate.TranslateActivity;
import teach.vietnam.asia.view.word.WordActivity;

import static teach.vietnam.asia.BaseApplication.mFirebaseAnalytics;


public class DashboardActivity extends BaseActivity<DashboardActivity> {

    private String TAG = "DashboardActivity";

    List<DashboardEntity> listData;

    @BindView(R.id.gridView)
    GridView gridView;

    MenuItem itemLanguage;

    LanguageAdapter adapterLanguage;
    Dialog dialogLanguage;

    DashboardAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.dashboard_layout;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView text: ");
        setTitle(getString(R.string.title_dashboard));
        dialogLanguage = new Dialog(this);
        dialogLanguage.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogLanguage.setContentView(R.layout.dialog_language2_layout);
        listData = new ArrayList<>();

        Utility.setLanguage(activity);
        createData();

        if (Common.isTablet(activity))
            gridView.setNumColumns(3);
        else
            gridView.setNumColumns(2);
        adapter = new DashboardAdapter(this, listData);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle params = new Bundle();
                String screen;

                switch (position) {
                    case 0:
                        startActivity2(AlphabetActivity.class);
                        screen = "AlphabetActivity";
                        break;
                    case 1:
                        startActivity2(NumberActivity.class);
                        screen = "NumberActivity";
                        break;
                    case 2:
                        startActivity2(BodyActivity.class);
                        screen = "BodyActivity";
                        break;
                    case 3:
                        startActivity2(RecognizeMainActivity.class);
                        screen = "RecognizeMainActivity";
                        break;

                    case 4:
                        startActivity2(WordActivity.class);
                        screen = "WordActivity";
                        break;
                    case 5:
                        startActivity2(PhrasesActivity.class);
                        screen = "FoodActivity";
                        break;
                    case 6:
                        startActivity2(GrammarDetailActivity.class);
                        screen = "GrammarDetailActivity";
                        break;
                    case 7:
                        startActivity2(FoodActivity.class);
                        screen = "PhrasesActivity";

                        break;
                    case 8:
                        startActivity2(PracticeActivity.class);
                        screen = "PracticeActivity";
                        break;
                    case 9:
                    default:
                        startActivity2(TranslateActivity.class);
                        screen = "TranslateActivity";
//                        if (BuildConfig.DEBUG) {
//                            FirebaseCrash.logcat(Log.ERROR, TAG, screen);
//                            FirebaseCrash.report(new Throwable("test lvn Crack...."));
//                        }
                        break;
                }

                if (!BuildConfig.DEBUG) {
                    // [START custom_event]
                    params.putString("Name", screen);
                    params.putString("Language", lang);
                    mFirebaseAnalytics.logEvent("SCREEN", params);
                }
            }
        });
        if (!BuildConfig.DEBUG)
            FirebaseCrash.logcat(Log.INFO, TAG, "initView");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        itemLanguage = menu.findItem(R.id.menuLang);

        setIconLanguage();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menuLang:
                showDialogLanguage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.imgPlace)
    public void actionPlace() {
        startActivity2(PlaceActivity.class);
    }

    @OnClick(R.id.llOtherApp)
    public void actionOtherApp() {
        Utility.installVnApp(activity);
    }

    ////// ======================================== /////////

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(String lang) {
            activity.lang = lang;
            BaseActivity.pref.putStringValue(lang, Constant.TYPE_LANGUAGE);
//            adapterLanguage.setLang(lang);
            setIconLanguage();
            Utility.setLanguage(activity);
            ///

            ///
            createData();

            adapter.notifyDataSetChanged();

            dialogLanguage.dismiss();
        }
    };
    ////==========

    private void createData() {
        listData.clear();
        listData.add(new DashboardEntity(R.drawable.ic_alphabet, getString(R.string.title_alphabet)));
        listData.add(new DashboardEntity(R.drawable.ic_number, getString(R.string.title_counter)));
        listData.add(new DashboardEntity(R.drawable.menu_body, getString(R.string.title_body)));
        listData.add(new DashboardEntity(R.drawable.menu_recognize, getString(R.string.title_recognize)));
        listData.add(new DashboardEntity(R.drawable.ic_animal, getString(R.string.title_word)));
        listData.add(new DashboardEntity(R.drawable.ic_phrase, getString(R.string.title_phrase)));
        listData.add(new DashboardEntity(R.drawable.ic_grammar, getString(R.string.title_grammar)));
        listData.add(new DashboardEntity(R.drawable.menu_food, getString(R.string.title_food)));
        listData.add(new DashboardEntity(R.drawable.menu_practice, getString(R.string.title_practice)));
        listData.add(new DashboardEntity(R.drawable.menu_translate, getString(R.string.title_translate)));
//        listData.add(new DashboardEntity(R.drawable.button_word_on, getString(R.string.title_coming_soon)));
    }

    private void setIconLanguage() {
        if (lang.equals(Constant.JA))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.japan));
        else if (lang.equals(Constant.KO))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.korea));
        else if (lang.equals(Constant.FR))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.france));
        else if (lang.equals(Constant.RU))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.russia));
        else if (lang.equals(Constant.AR))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.arabic));
        else if (lang.equals(Constant.ZH))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.china));
        else if (lang.equals(Constant.ES))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.spanish));
        else
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.english));
    }

    private void showDialogLanguage() {
        // custom dialog


//        dialog.setCancelable(false);
//        dialog.setTitle("Language");

        Button dialogButton = (Button) dialogLanguage.findViewById(R.id.btnChangeLang);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLanguage.dismiss();
            }
        });
        RecyclerView recyclerView = (RecyclerView) dialogLanguage.findViewById(R.id.recyclerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        // Disabled nested scrolling since Parent scrollview will scroll the content.
        recyclerView.setNestedScrollingEnabled(false);

        adapterLanguage = new LanguageAdapter(activity, lang, onItemClickListener);
        recyclerView.setAdapter(adapterLanguage);

        dialogLanguage.show();
    }

}
