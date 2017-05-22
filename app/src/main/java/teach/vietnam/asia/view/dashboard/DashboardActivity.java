package teach.vietnam.asia.view.dashboard;

import android.app.Dialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

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
import teach.vietnam.asia.view.body.BodyActivity;
import teach.vietnam.asia.view.foods.FoodActivity;
import teach.vietnam.asia.view.grammar.detail.GrammarDetailActivity;
import teach.vietnam.asia.view.number.NumberActivity;
import teach.vietnam.asia.view.phrase.Phrases2Activity;
import teach.vietnam.asia.view.practice.PracticeActivity;
import teach.vietnam.asia.view.recognizes.RecognizeMainActivity;
import teach.vietnam.asia.view.translate.TranslateActivity;
import teach.vietnam.asia.view.word.WordActivity;


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
                    case 1:
                        startActivity2(NumberActivity.class);
                        break;
                    case 2:
                        startActivity2(BodyActivity.class);
                        break;
                    case 3:
//                        startActivity2(RecognizeMainActivity.class);
                        startActivity2(RecognizeMainActivity.class);
                        break;

                    case 4:
                        startActivity2(WordActivity.class);
                        break;
                    case 5:
                        startActivity2(FoodActivity.class);
                        break;
                    case 6:
                        startActivity2(GrammarDetailActivity.class);

                        break;
                    case 7:
                        startActivity2(Phrases2Activity.class);

                        break;
                    case 8:
                        startActivity2(PracticeActivity.class);
                        break;
                    case 9:
                    default:
                        startActivity2(TranslateActivity.class);
                        break;
//                    case 10:
//                    default:
//                        showDialogLanguage();
//                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
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

    @OnClick(R.id.llOtherApp)
    public void actionOtherApp() {
        Utility.installVnApp(activity);
    }

    ////// ======================================== /////////

    private void createData() {
        listData = new ArrayList<>();
        listData.add(new DashboardEntity(R.drawable.ic_alphabet, getString(R.string.title_alphabet)));
        listData.add(new DashboardEntity(R.drawable.ic_number, getString(R.string.title_counter)));
        listData.add(new DashboardEntity(R.drawable.menu_body, getString(R.string.title_body)));
        listData.add(new DashboardEntity(R.drawable.menu_recognize, getString(R.string.title_recognize)));
        listData.add(new DashboardEntity(R.drawable.ic_animal, getString(R.string.title_word)));
        listData.add(new DashboardEntity(R.drawable.menu_food, getString(R.string.title_food)));
        listData.add(new DashboardEntity(R.drawable.ic_grammar, getString(R.string.title_grammar)));
        listData.add(new DashboardEntity(R.drawable.ic_phrase, getString(R.string.title_phrase)));
        listData.add(new DashboardEntity(R.drawable.menu_practice, getString(R.string.title_practice)));
        listData.add(new DashboardEntity(R.drawable.menu_translate, getString(R.string.title_translate)));
//        listData.add(new DashboardEntity(R.drawable.button_word_on, getString(R.string.title_coming_soon)));
    }


    private void showDialogLanguage() {
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_language_layout);

//        dialog.setCancelable(false);
//        dialog.setTitle("Language");

        Button dialogButton = (Button) dialog.findViewById(R.id.btnChangeLang);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        LinearLayout llVietEnglish = (LinearLayout) dialog.findViewById(R.id.llVietEnglish);
        LinearLayout llVietJapan = (LinearLayout) dialog.findViewById(R.id.llVietJapan);
        LinearLayout llVietKorean = (LinearLayout) dialog.findViewById(R.id.llVietKorean);
        LinearLayout llVietFrance = (LinearLayout) dialog.findViewById(R.id.llVietFrance);
        LinearLayout llVietRussia = (LinearLayout) dialog.findViewById(R.id.llVietRussia);

        llVietEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.pref.putStringValue(Constant.EN, Constant.TYPE_LANGUAGE);
                dialog.dismiss();
            }
        });

        llVietJapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.pref.putStringValue(Constant.JA, Constant.TYPE_LANGUAGE);
                dialog.dismiss();
            }
        });

        llVietKorean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.pref.putStringValue(Constant.KO, Constant.TYPE_LANGUAGE);
                dialog.dismiss();
            }
        });

        llVietFrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.pref.putStringValue(Constant.FR, Constant.TYPE_LANGUAGE);
                dialog.dismiss();
            }
        });

        llVietRussia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.pref.putStringValue(Constant.RU, Constant.TYPE_LANGUAGE);
                dialog.dismiss();
            }
        });

        if (lang.equals("ja")) {
            (dialog.findViewById(R.id.imgCheckEn)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckJa)).setVisibility(View.VISIBLE);
            (dialog.findViewById(R.id.imgCheckKo)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckFr)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckRu)).setVisibility(View.INVISIBLE);
        } else if (lang.equals("ko")) {
            (dialog.findViewById(R.id.imgCheckEn)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckJa)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckKo)).setVisibility(View.VISIBLE);
            (dialog.findViewById(R.id.imgCheckFr)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckRu)).setVisibility(View.INVISIBLE);
        } else if (lang.equals("fr")) {
            (dialog.findViewById(R.id.imgCheckEn)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckJa)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckKo)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckFr)).setVisibility(View.VISIBLE);
            (dialog.findViewById(R.id.imgCheckRu)).setVisibility(View.INVISIBLE);
        } else if (lang.equals("ru")) {
            (dialog.findViewById(R.id.imgCheckEn)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckJa)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckKo)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckFr)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckRu)).setVisibility(View.VISIBLE);
        } else {
            (dialog.findViewById(R.id.imgCheckEn)).setVisibility(View.VISIBLE);
            (dialog.findViewById(R.id.imgCheckJa)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckKo)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckFr)).setVisibility(View.INVISIBLE);
            (dialog.findViewById(R.id.imgCheckRu)).setVisibility(View.INVISIBLE);
        }

        dialog.show();
    }


}
