package teach.vietnam.asia;


public class Constant {

    //    final static public boolean isMyDebug = true;
    // ////////
//    final static public boolean isPro = false;
    final static public int TRIAL = 4;

    final static public String PACKAGE_VOICE = "market://details?id=com.google.android.voicesearch";
    //    final static public String PACKAGE_PREMIUM = "market://details?id=learn.vietnamese.vn.pro";
//    final static public String PACKAGE_UPDATE = "market://details?id=" + BuildConfig.APPLICATION_ID;
//    final static public String API_MARKER = "https://androidquery.appspot.com/api/market?app=" + BuildConfig.APPLICATION_ID;
    final static public String PACKAGE_LJP = "market://details?id=vn.jp.language.ljp";

    // /////////
    // ========== Purchase ===========
    //lvn
    public static final String BASE_64_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuUqLwyRYe0YHJFbhVgiXF9mmV/7K4yHiBRHHV5t5AMmFeKbVa1cM+LGeA/9rlzAJbLPwtF3UHG+V0wtxvijETA3j7vQE6sNX9tH72j7SOYFaU2HLN9zjcd83RSzipcMdAUZjIY6FdRSj3FIq/4fmzr6OEOWJpAYT8Lgm0oeCvnyWdhSOQw6m6gnZrXFSlWV2npFV+3rZWYoWL8/YA+RZtV7ECds4kHJiwvwkk0gkfJ2jPWokVZGWZWRzTjEABJG6QYTRWjr0t7iIcybdjzFdqdgml4ZT0p4vms+dZcRcXp5jkccuA/9z+jGZHaugdTAbPr467wbW0jui1ymabTMQRQIDAQAB";
    ////
    public static final String SKU = "study.vn.lvn2";
    //        public static final String SKU = "android.test.purchased";
    public static final int PURCHASE_REQUEST_CODE = 1080;
    public static final boolean ITEM_PURCHASED = true;
    public static final String TRIAL_APP = "staging";
    /// ===========

    public static final String FIREBASE_BUCKET = "gs://learnvn-361d2.appspot.com";
    public static final String FIREBASE_PLACE_LINK = "place_link/area_%1$s/type_%2$s_id_%3$s.txt";
    public static final String FIREBASE_PLACE_PATH = "place/area_%1$s/type_%2$s/%3$s/";


    ////
//    final static public String MY_TEXT;

//    public static native String stringFromJNI();
//
//    static {
//        System.loadLibrary("native-lib");
//        MY_TEXT = stringFromJNI();
//    }

    //    public static final String DB_NAME = "VN.db";
    public static final String DB_NAME_V2 = "VN_V23.db";
    public static final int DATABASE_VERSION = 102;

    //    public static final String JSON_WORDS_NAME;
    public static final String JSON_RECOGNIZE_NAME;
    public static final String JSON_MAPNAME_NAME;
//    public static final String CREATE_DB = "DB72";

    public static final String TYPE_LANGUAGE = "type_language";
//    public static final String TYPE_EN = "type_en";
//    public static final String TYPE_JA = "type_ja";
//    public static final String TYPE_KO = "type_ko";
//    public static final String TYPE_FR = "type_fr";
//    public static final String TYPE_RU = "type_ru";

//    public static final String FILE_JA;
//    public static final String FILE_KO;
//    public static final String FILE_RU;
//    public static final String FILE_FR;
//    public static final String FILE_ES;
//    public static final String FILE_IT;
//    public static final String FILE_EN;
//    public static final String macAllow = "a";


    public static final String JA = "ja";
    public static final String KO = "ko";
    public static final String FR = "fr";
    public static final String RU = "ru";
    //    public static final String ES = "es";
//    public static final String IT = "it";
    public static final String EN = "en";
    public static final String AR = "ar";
    public static final String ZH = "zh";
    public static final String ES = "es";
    public static final String KM = "km-rKH";

//    public static final String REC_JA = "rec_ja.txt";
//    public static final String REC_KO = "rec_ko.txt";
//    public static final String REC_RU = "rec_ru.txt";
//    public static final String REC_FR = "rec_fr.txt";
//    public static final String REC_ES = "rec_es.txt";
//    public static final String REC_IT = "rec_it.txt";
//    public static final String REC_EN = "rec_en.txt";

    static {
//        if (isMyDebug) {
        JSON_RECOGNIZE_NAME = "recognize.txt";
        JSON_MAPNAME_NAME = "MapName.txt";

//        FILE_JA = "ja.txt";
//        FILE_KO = "ko.txt";
//        FILE_RU = "ru.txt";
//        FILE_FR = "fr.txt";
//        FILE_ES = "es.txt";
//        FILE_IT = "it.txt";
//        FILE_EN = "en.txt";

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
//        if (BuildConfig.APPLICATION_ID.equals("learn.vietnamese.vn.pro"))
//            isPro = true;
//        else
//            isPro = false;
    }

    public static final String KEY_UPDATE = "db_update_83"; // gia tri khac se delete database cu
    //    public static final String KEY_SOUND = "key_sound";
//    public static final String VALUE_SOUND = "sound_7"; //gia tri tang khi file Mapname.txt thay doi.
    //
//	// /////////
//	public static final String COLUMN_SOUND = tblMapNameDao.Properties.Sound.columnName;
    public static final String INTENT_KIND = "intent_kind";
    public static final String INTENT_POSITION = "intent_pos";



    public static final String INTENT_WORD = "intent_word";
    //	public static String GA_RECOGNIZE_LEARN_FRAGMENT = "LEARN RECOGNIZE";
//	public static String GA_RECOGNIZE_TEST_FRAGMENT = "TEST RECOGNIZE";
    public static final String PREF_BG_THEME = "bg_theme2";
    public static final int REQ_CODE_SPEECH_INPUT = 1000;
    public static boolean bLog = BuildConfig.DEBUG;
    public static String PREF_PAGE = "curr_page";

    public static int TYPE_DATA_FOOD = 3;
    public static int TYPE_DATA_DRINK = 31;
    public static int TYPE_DATA_ANIMAL = 4;
    public static int TYPE_DATA_FRUIT = 1;
    public static int TYPE_DATA_VEGETABLE = 2;
    public static int TYPE_DATA_PHRASE = 11;
    public static int TYPE_DATA_PLACE = 100;


    ////
    public static int SEARCH_DATA_FOOD = 1;
    public static int SEARCH_DATA_PHRASES = 2;
    public static int SEARCH_DATA_PLACE = 3;


    public enum TYPE_WORD {
        ANIMAL, FRUIT, OTHER
    }

}
