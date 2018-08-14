package teach.vietnam.asia.view.phrase;

import java.util.List;

import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.sound.IAudioPlayer;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.base.BasePresenter;
import teach.vietnam.asia.view.action.ICallback;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class PhrasesPresenter extends BasePresenter<PhrasesActivity> {

    private final String TAG = "Phrases2Presenter";

    public AudioPlayer audio;

    public PhrasesPresenter(PhrasesActivity activity) {
        super(activity);
        audio = new AudioPlayer(activity);
    }

    public void loadData(ICallback<List<WordEntity>> callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public List<WordEntity> onBackground() {
                        return PhrasesDao.getListData(activity, 11);
                    }
                }
        );
    }

    public void speakWord(String word, IAudioPlayer iAudioPlayer) {
        String str = word.replaceAll("\\?", "").replaceAll("\\.", "").replaceAll("!", "").replaceAll(",", "");
        str = android.text.Html.fromHtml(str).toString();
        audio.isSlowly = activity.isSlowly;
        audio.speakWord(str, iAudioPlayer);
        Log.i(TAG, "word: str:" + str);
    }

}
