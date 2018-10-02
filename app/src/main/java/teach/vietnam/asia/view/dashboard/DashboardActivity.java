package teach.vietnam.asia.view.dashboard;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.firebase.crash.FirebaseCrash;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.entity.DashboardEntity;
import teach.vietnam.asia.utils.Common;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.alphabet.AlphabetActivity;
import teach.vietnam.asia.view.base.BaseActivity;
import teach.vietnam.asia.view.body.BodyActivity;
import teach.vietnam.asia.view.dashboard.language.LanguageAdapter;
import teach.vietnam.asia.view.dashboard.language.OnItemClickListener;
import teach.vietnam.asia.view.dashboard.search.IActionSearch;
import teach.vietnam.asia.view.dashboard.search.SearchBoxEx;
import teach.vietnam.asia.view.dashboard.search.SearchEntity;
import teach.vietnam.asia.view.dashboard.search.SearchGroupData;
import teach.vietnam.asia.view.dashboard.search.SearchPresenter;
import teach.vietnam.asia.view.food_detail.FoodDetailActivity;
import teach.vietnam.asia.view.foods.FoodActivity;
import teach.vietnam.asia.view.grammar.detail.GrammarDetailActivity;
import teach.vietnam.asia.view.number.NumberActivity;
import teach.vietnam.asia.view.phrase.PhrasesActivity;
import teach.vietnam.asia.view.placedetail.PlaceDetailActivity;
import teach.vietnam.asia.view.places.PlaceActivity;
import teach.vietnam.asia.view.practice.PracticeActivity;
import teach.vietnam.asia.view.purchase.PurchaseActivity;
import teach.vietnam.asia.view.recognizes.RecognizeMainActivity;
import teach.vietnam.asia.view.translate.TranslateActivity;
import teach.vietnam.asia.view.word.WordActivity;


public class DashboardActivity extends PurchaseActivity<DashboardActivity> implements IDashboardAction, IActionSearch {

    private String TAG = "DashboardActivity";

    private final String STATE_KEY_SEARCH = "KEY_SEARCH";
    private final String STATE_SEARCH_OPENED = "SEARCH_OPENED";
    List<DashboardEntity> listData;


//    @BindView(R.id.coordinator)
//    CoordinatorLayout coordinator;
//
//    @BindView(R.id.appBar)
//    AppBarLayout appBar;
//
//    @BindView(R.id.toolbarTitle)
//    TextView toolbarTitle;
//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.searchBox)
    SearchBoxEx searchBox;

    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;

//    @BindView(R.id.gridView)
//    GridView gridView;

    MenuItem itemLanguage;

    LanguageAdapter adapterLanguage;
    Dialog dialogLanguage;

    DashboardAdapter adapter;

    SearchPresenter searchPresenter;

    String stateKeySearch = "";
    boolean isSearchOpen = false;

    @Override
    protected int getLayout() {
        return R.layout.dashboard_layout;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView text: ");
//        setTitle(getString(R.string.title_dashboard));


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
            actionBar.setDisplayShowTitleEnabled(false); // remove title

        } else
            Log.e(TAG, "initView actionBar NULL!!!!");

        searchPresenter = new SearchPresenter(this);

        dialogLanguage = new Dialog(this);
        dialogLanguage.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogLanguage.setContentView(R.layout.dialog_language2_layout);
        listData = new ArrayList<>();

        Utility.setLanguage(activity);
        createData();
        setupView();
        initViewSearch();

        if (savedInstanceState != null) {
            Log.i(TAG, "initview savedInstanceState NOT NULL");
//            stateKeySearch = savedInstanceState.getString(STATE_KEY_SEARCH);
//            if(stateKeySearch!=null && !stateKeySearch.trim().equals("")){
//                searchBox.populateEditText(stateKeySearch);
//            }
        } else {
            stateKeySearch = "";
        }

        if (!BuildConfig.DEBUG)
            FirebaseCrash.logcat(Log.INFO, TAG, "initView");
    }

    ///////////// xu ly truong hop activity tu huy khi qua man hinh khac

    // invoked when the activity may be temporarily destroyed, save the instance state here
//this method will be called before onstop
    @Override
    public void onSaveInstanceState(Bundle outState) {
//        outState.putString(STATE_KEY, activityState);
        Log.i(TAG, "onSaveInstanceState");
//        outState.putString(STATE_KEY_SEARCH, searchBox.getSearchText());
//        if (searchBox.getSearchOpen())
        boolean searchState = searchBox.getSearchOpen();
        outState.putBoolean(STATE_SEARCH_OPENED, searchState);
        outState.putString(STATE_KEY_SEARCH, searchBox.getSearchText());
//            stateKeySearch = searchBox.getSearchText();


        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        mTextView.setText(savedInstanceState.getString(STATE_KEY));
        Log.i(TAG, "onRestoreInstanceState");
        stateKeySearch = savedInstanceState.getString(STATE_KEY_SEARCH);

//        if (stateKeySearch != null && !stateKeySearch.trim().equals("")) {
//        searchBox.setSearchString(stateKeySearch);
        boolean searchState = savedInstanceState.getBoolean(STATE_SEARCH_OPENED);
        if (searchState)
            searchBox.populateEditText(stateKeySearch);
//        }
//        stateKeySearch = "";
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy!!!!!");
    }


    public void showOtherApp() {
        Utility.installVnApp(activity);
    }


    @Override
    public void onItemClick(int pos) {
//        String screen;

        switch (pos) {

            case 0:
                startActivity2(PlaceActivity.class);
//                startActivity2(MapActivity.class);
                break;
            case 1:
                startActivity2(FoodActivity.class);
//                screen = "FoodActivity";
                break;

            case 2:
                startActivity2(AlphabetActivity.class);
//                screen = "AlphabetActivity";
                break;

            case 3:
                startActivity2(NumberActivity.class);
//                screen = "NumberActivity";
                break;

            case 4:
                startActivity2(BodyActivity.class);
//                screen = "BodyActivity";
                break;
            case 5:
                startActivity2(RecognizeMainActivity.class);
//                screen = "RecognizeMainActivity";
                break;

            case 6:
                startActivity2(WordActivity.class);
//                screen = "WordActivity";
                break;
            case 7:
                startActivity2(PhrasesActivity.class);
//                screen = "FoodActivity";
                break;
            case 8:
                startActivity2(GrammarDetailActivity.class);
//                screen = "GrammarDetailActivity";
                break;
            case 9:
                startActivity2(TranslateActivity.class);
//                screen = "PracticeActivity";
                break;
            case 10:
                startActivity2(PracticeActivity.class);
                break;
            case 11:
                //Privacy policy
                showPrivacyPolicy();
                break;
            case 12:
//                if (BuildConfig.DEBUG) {
//                    startActivity2(AndroidDatabaseManager.class);
//                } else {
                showOtherApp();
//                }
                break;
            default:
//                screen = "TranslateActivity";
                break;
        }
    }
    ////// ======================================== /////////

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(String lang) {
            activity.lang = lang;

            BaseActivity.pref.putStringValue(lang, Constant.TYPE_LANGUAGE);
//            adapterLanguage.setLang(lang);
//            setIconLanguage();

            Utility.setLanguage(activity);
            ///

            searchPresenter = new SearchPresenter(activity);
            searchBox.setLogoText(getString(R.string.app_name));
            searchBox.setHint(activity.getString(R.string.hint_search));

            ///
            createData();

            adapter.notifyDataSetChanged();

            dialogLanguage.dismiss();
        }
    };
    ////==========
    //======================== Start Purchase =========================

    @Override
    protected void dealWithIabSetupSuccess() {
        if (getItemPurchased() == Constant.ITEM_PURCHASED) {
            Log.i(TAG, "WithIabSetupSuccess...item purchased");
            isPurchased = true;
            if (adapter == null)
                return;


//            adapter.setPurchased(isPurchased);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (searchBox != null && searchBox.adapter != null) {
                        searchBox.adapter.isPurchased = isPurchased;
                        searchBox.adapter.notifyDataSetChanged();
                    }
                }
            });

            /// Test only
//            if (BuildConfig.DEBUG)
//                clearPurchaseTest();

        } else {
            Log.i(TAG, "WithIabSetupSuccess item not purchase");
            isPurchased = false;
        }
    }

    @Override
    protected void dealWithIabSetupFailure() {
        Log.e(TAG, "dealWithIabSetupFailure ====================== ERROR ==================");
    }
    //========================END  Purchase =========================

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //lay text tu voice
        if (requestCode == SearchBoxEx.VOICE_RECOGNITION_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            searchBox.populateEditText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void createData() {
        listData.clear();
        listData.add(new DashboardEntity(R.drawable.place_1_1_cho_ben_thanh, getString(R.string.place_vietnam_travel)));
        listData.add(new DashboardEntity(R.drawable.menu_food, getString(R.string.title_food2)));
        listData.add(new DashboardEntity(R.drawable.ic_alphabet, getString(R.string.title_alphabet)));
        listData.add(new DashboardEntity(R.drawable.ic_number, getString(R.string.title_counter)));
        listData.add(new DashboardEntity(R.drawable.menu_body, getString(R.string.title_body)));
        listData.add(new DashboardEntity(R.drawable.menu_recognize, getString(R.string.title_recognize)));
        listData.add(new DashboardEntity(R.drawable.ic_animal, getString(R.string.title_word)));
        listData.add(new DashboardEntity(R.drawable.ic_phrase, getString(R.string.title_phrase)));
        listData.add(new DashboardEntity(R.drawable.ic_grammar, getString(R.string.title_grammar)));
        listData.add(new DashboardEntity(R.drawable.menu_translate, getString(R.string.title_translate)));
        listData.add(new DashboardEntity(R.drawable.menu_practice, getString(R.string.title_practice)));
//        listData.add(new DashboardEntity(R.drawable.button_word_on, getString(R.string.title_coming_soon)));
    }

    private void setupView() {
        Log.i(TAG, "setupView");
        GridLayoutManager lLayout;

        if (Common.isTablet(activity))
            lLayout = new GridLayoutManager(activity, 8);
        else
            lLayout = new GridLayoutManager(activity, 6);

        lLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == 1) {
                    if (Common.isTablet(activity))
                        return 4; //merge 4 cot lai
                    else
                        return 3; //merge 3 cot lai

//                    return 1;
                } else if (position == listData.size() || position == listData.size() + 1) {
                    if (Common.isTablet(activity))
                        return 8; //merge 4 cot lai
                    else
                        return 6; //merge 3 cot lai

//                    return 1;

//                } else if (position == listData.size() + 1) {
//                    if (Common.isTablet(activity))
//                        return 4;
//                    else
//                        return 3;
                } else {
                    return 2;
                }
            }
        });

        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(lLayout);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        adapter = new DashboardAdapter(listData, this);
        recyclerView2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void showDialogLanguage() {
        // custom dialog

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


    // ============= START IActionSearch ==============
    @Override
    public void loadData(String keySearch) {
        searchPresenter.getData(keySearch, new ICallback<List<SearchGroupData>>() {
            @Override
            public void onComplete(final List<SearchGroupData> data) {
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
                searchBox.setData(data, isPurchased);
//                    }
//                });
            }
        });
    }

    @Override
    public void onSearchHeaderClick(final boolean type, final int pos) {

        if (type == false) {
            // collapse group

            boolean isCloseAll = false;
            for (int i = 0; i < searchBox.getGroupSize(); i++) {
                if (searchBox.adapter.isGroupExpanded(i) == true) { //extended
                    isCloseAll = true;
                    break;
                }
            }

            //closed all group
            if (isCloseAll == false) {
                searchBox.refresh(); // goi ham nay muc dich de resize lai kich thuoc cua list
            }

        } else {
            // extend
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    searchBox.adapter.notifyDataSetChanged();
//                    Log.i(TAG, "header click: " + pos + "=" + searchBox.adapter.isGroupExpanded(pos));
                }
            }, 100);
        }
    }


    @Override
    public void onSearchClick(SearchEntity entity) {
        Log.i(TAG, "IActionSearch  onSearchClick:" + entity.vn);

        if (entity.kind == Constant.SEARCH_DATA_FOOD) {
            Intent intent = new Intent(activity, FoodDetailActivity.class);
            intent.putExtra(BaseTable.COL_ID, entity.id);
            intent.putExtra(BaseTable.COL_TYPE, entity.type);
            intent.putExtra(BaseTable.COL_AREA, entity.area);
            startActivity(intent);
        } else if (entity.kind == Constant.SEARCH_DATA_PLACE) {
            Intent intent = new Intent(activity, PlaceDetailActivity.class);
            intent.putExtra(BaseTable.COL_ID, entity.id);
            intent.putExtra(BaseTable.COL_TYPE, entity.type);
            intent.putExtra(BaseTable.COL_AREA, entity.area);
            startActivity(intent);
        } else if (entity.kind == Constant.SEARCH_DATA_PHRASES) {
            purchaseItem();
        }

    }
    // ============= END ================

    private void initViewSearch() {
        searchBox.setLogoText(getString(R.string.app_name));
        searchBox.setHint(activity.getString(R.string.hint_search));
        searchBox.enableVoiceRecognition(this);

        searchBox.setMenuListener(new SearchBox.MenuListener() {

            @Override
            public void onMenuClick() {

                showDialogLanguage();
                //Hamburger has been clicked
//                Toast.makeText(activity, "Menu click", Toast.LENGTH_LONG).show();
//                if (searchBox.getSearchOpen())
//                    activity.finish();
//                onBackPressed();

//                Snackbar.make(searchBox, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }

        });

        searchBox.setSearchListener(new SearchBox.SearchListener() {

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }

            @Override
            public void onSearchTermChanged(String term) {
                //React to the search term changing
                //Called after it has updated results
            }

            @Override
            public void onSearch(String searchTerm) {
//                Toast.makeText(activity, searchTerm + " Searched", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResultClick(SearchResult result) {
                //React to a result being clicked
//                Toast.makeText(activity, result.title + " (title)", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSearchCleared() {
                //Called when the clear button is clicked
            }

        });

        //duoc goi khi user go text vao o search hoac click vao noi dung da search
        searchBox.setAction(this);
//        searchBox.toggleSearch();

    }

    private void showPrivacyPolicy() {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        WebView wv = new WebView(this);
//        wv.loadUrl("https://sites.google.com/view/learnvietnamesevoice/home");
        wv.loadUrl("file:///android_asset/policy.html");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
