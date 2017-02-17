package teach.vietnam.asia;


public class Constant {

    //    final static public boolean isMyDebug = true;
    // ////////
    final static public boolean isPro;
    final static public int TRIAL = 4;

    final static public String PACKAGE_VOICE = "market://details?id=com.google.android.voicesearch";
    //    final static public String PACKAGE_PREMIUM = "market://details?id=learn.vietnamese.vn.pro";
//    final static public String PACKAGE_UPDATE = "market://details?id=" + BuildConfig.APPLICATION_ID;
    final static public String API_MARKER = "https://androidquery.appspot.com/api/market?app=" + BuildConfig.APPLICATION_ID;
    final static public String PACKAGE_LJP = "market://details?id=vn.jp.language.ljp";

    // /////////
    // ========== Purchase ===========
    public static final String BASE_64_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxUMxIhJ+tM1Ljj9x6eqL52tUVOpKsihGfgSD0GJ2tB5vTiS5E2+xA0iqfsFgTX3atsSNKefIY8gET//T0czPNCdF7bmnQJC0xDYPRXzebh0KbxAWqkRWr7eUb0fGafOc68v9YcjgCpM4q/S0T1lkvDJlax4CBLgEd4N/GEKaxHospLyxVbzoQ4yn0b2K4XZUGB5Dvo6XwHxI6w+D668uZPW/oz/zZW7KgoXpvtNYVwTFUBCMvSPhqFu8Ek4+OBo7feYIsPUyXf1fk4M8b+6nMybBxUnyzr3osM8kDltGg64ZqVD8BXVxvbJ7RWCZPIL8hL/IV+WFesso/MLktXPpSQIDAQAB";
    public static final String SKU = "study.vn.jp";
    //    public static final String SKU = "android.test.purchased";
    public static final int PURCHASE_REQUEST_CODE = 1080;
    public static final boolean ITEM_PURCHASED = true;
    /// ===========

    ////
    final static public String MY_TEXT;

    public static native String stringFromJNI();

    static {
        System.loadLibrary("native-lib");
        MY_TEXT = stringFromJNI();
    }

    //    public static final String DB_NAME = "VN.db";
    public static final String DB_NAME_V2 = "VN_V2.db";
    public static final int DATABASE_VERSION = 1;

    //    public static final String JSON_WORDS_NAME;
    public static final String JSON_RECOGNIZE_NAME;
    public static final String JSON_MAPNAME_NAME;
//    public static final String CREATE_DB = "DB72";

    public static final String FILE_JA;
    public static final String FILE_KO;
    public static final String FILE_RU;
    public static final String FILE_FR;
    public static final String FILE_ES;
    public static final String FILE_IT;
    public static final String FILE_EN;
//    public static final String macAllow = "a";


    public static final String JA = "ja";
    public static final String KO = "ko";
    public static final String RU = "ru";
    public static final String FR = "fr";
    public static final String ES = "es";
    public static final String IT = "it";
    public static final String EN = "en";

    public static final String REC_JA = "rec_ja.txt";
    public static final String REC_KO = "rec_ko.txt";
    public static final String REC_RU = "rec_ru.txt";
    public static final String REC_FR = "rec_fr.txt";
    public static final String REC_ES = "rec_es.txt";
    public static final String REC_IT = "rec_it.txt";
    public static final String REC_EN = "rec_en.txt";

    static {
//        if (isMyDebug) {
        JSON_RECOGNIZE_NAME = "recognize.txt";
        JSON_MAPNAME_NAME = "MapName.txt";

        FILE_JA = "ja.txt";
        FILE_KO = "ko.txt";
        FILE_RU = "ru.txt";
        FILE_FR = "fr.txt";
        FILE_ES = "es.txt";
        FILE_IT = "it.txt";
        FILE_EN = "en.txt";

//        } else {
//            JSON_RECOGNIZE_NAME = "recognize.txt";
//            JSON_MAPNAME_NAME = "MapName.data";
//
//            FILE_JA = "ja.data";
//            FILE_KO = "ko.data";
//            FILE_RU = "ru.data";
//            FILE_FR = "fr.data";
//            FILE_ES = "es.data";
//            FILE_IT = "it.data";
//            FILE_EN = "en.data";
//        }


//        if (Utility.getMacAddress(MyApplication.getInstance()).equals(macAllow))
//            isPro = true;
//        else
        if (BuildConfig.APPLICATION_ID.equals("learn.vietnamese.vn.pro"))
            isPro = true;
        else
            isPro = false;
    }

    public static final String KEY_UPDATE = "db_update_2"; // gia tri khac se delete database cu
    public static final String KEY_SOUND = "key_sound";
    public static final String VALUE_SOUND = "sound_7"; //gia tri tang khi file Mapname.txt thay doi.
    //
//	// /////////
//	public static final String COLUMN_SOUND = tblMapNameDao.Properties.Sound.columnName;
    public static final String INTENT_KIND = "intent_kind";
    public static final String INTENT_POSITION = "intent_pos";

    //	public static String LIST_STREET_HCM = "listStreetHCM";
//	public static String LIST_STREET_HN = "listStreetHN";
//
//	public static String KEY_CITY = "city";
//	public static final String PREF_MODIFY_AD = "date_modify_ad";
    //
    // /////// dropbox
//	static public String FOLDER_NAME;
//	static {
//		if (BuildConfig.DEBUG)
//			FOLDER_NAME = "/Bus_debug/";
//		else
//			FOLDER_NAME = "/Bus/";
//	}

    public static final String INTENT_WORD = "intent_word";
    //	public static String GA_RECOGNIZE_LEARN_FRAGMENT = "LEARN RECOGNIZE";
//	public static String GA_RECOGNIZE_TEST_FRAGMENT = "TEST RECOGNIZE";
    public static final String PREF_BG_THEME = "bg_theme2";
    public static final int REQ_CODE_SPEECH_INPUT = 1000;
    public static boolean bLog = BuildConfig.DEBUG;
    public static String PREF_PAGE = "curr_page";


    public enum TYPE_WORD {
        ANIMAL, FRUIT, OTHER
    }


}
