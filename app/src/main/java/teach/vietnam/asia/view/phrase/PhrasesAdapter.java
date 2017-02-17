package teach.vietnam.asia.view.phrase;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.sound.IAudioPlayer;
import teach.vietnam.asia.utils.Common;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.NumberToWord;
import teach.vietnam.asia.utils.Utility;

public class PhrasesAdapter extends BaseAdapter implements SectionIndexer {

    public AudioPlayer audio;
    private PhrasesActivity activity;
    private List<WordEntity> listData;
    private List<WordEntity> listData2;
    private LayoutInflater layoutInflater;
    private String lang = "";
    private String[] alpha;

    boolean modify = false;
    boolean reLoad = false;
    private String currString = "";

    @SuppressLint("DefaultLocale")
    public PhrasesAdapter(PhrasesActivity activity, List<WordEntity> listData, IAudioPlayer iAudioPlayer) {
        int i = 0;
        String word;
        audio = new AudioPlayer(activity, iAudioPlayer);
//        this.iAudioPlayer = iAudioPlayer;
        Log.i(PhrasesAdapter.class, "PracticeAdapter locale:" + Locale.getDefault().getLanguage());
        this.activity = activity;
        this.listData = listData;
        listData2 = new ArrayList();
        listData2.addAll(listData);
        lang = activity.lang;
        layoutInflater = LayoutInflater.from(activity);

        alpha = null;
        alpha = new String[listData.size()];

        for (WordEntity entity : listData) {
            word = android.text.Html.fromHtml(entity.getO1()).toString();
            alpha[i++] = word.split(" ")[0];
        }
    }

    public int getCount() {
        return listData.size();
    }

    public WordEntity getItem(int position) {
        return listData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    private void resetAlphaSearch() {
        int i = 0;
        String word;
        alpha = null;
        alpha = new String[listData.size()];

        for (WordEntity entity : listData) {
            word = android.text.Html.fromHtml(entity.getO1()).toString();
            alpha[i++] = word.split(" ")[0];
        }

    }

    public View getView(final int position, View view, ViewGroup viewGroup) {
        String phrases;
        String word_default;
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.phrases_item, null);

            holder.llWord = (RelativeLayout) view.findViewById(R.id.llWord);
            holder.tvViet = (TextView) view.findViewById(R.id.tvViet);
            holder.tvOther = (TextView) view.findViewById(R.id.tvOther);
            holder.btnSpeak = (Button) view.findViewById(R.id.btnSpeak);
//            holder.imgSearch = (ImageView) view.findViewById(R.id.imgSearch);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (listData.size() <= position)
            return view;

        word_default = listData.get(position).getDefault_word();
        phrases = String.format(listData.get(position).getVi(), "<u><font color=\"blue\">"
                + word_default + " </font></u>");

        holder.tvViet.setText(Html.fromHtml(phrases));
        holder.tvOther.setText(Html.fromHtml(listData.get(position).getO1()));

        holder.btnSpeak.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String word;
                word = String.format(listData.get(position).getVi(), listData.get(position).getDefault_word());
                Log.i(PhrasesAdapter.class, "onClick word:" + word);
                if (Constant.isPro) {
                    word = android.text.Html.fromHtml(word).toString();
                    audio.speakWord(word.toLowerCase().replaceAll("\\?", "").replaceAll("\\.", "").replaceAll("!", "").replaceAll(",", ""));
                }
//                else
//                    Utility.installPremiumApp(activity);
            }
        });

        return view;
    }

    // ///filter
    @SuppressLint("DefaultLocale")
    public void filter(String charText) {

        if (!modify) {
            modify = true;
            new ResetAdapter(charText).execute();
        } else {
            reLoad = true;
            currString = charText;
        }

    }

    @Override
    public int getPositionForSection(int section) {
        return section;
    }

    @Override
    public int getSectionForPosition(int arg0) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        return alpha;
    }

    public void setSlowly(boolean b) {
        audio.isSlowly = b;
    }


    public class ViewHolder {
        TextView tvViet;
        TextView tvOther;
        Button btnSpeak;
        //        ImageView imgSearch;
        RelativeLayout llWord;
    }


    public class ResetAdapter extends AsyncTask<Void, Void, Boolean> {
        private String charText;

        public ResetAdapter(String charText) {
            this.charText = charText;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            String word1, word2, wordVN;
            long number;

            try {
//                if(modify)
//                    return false;
//                modify = true;

                charText = charText.toLowerCase(Locale.getDefault()).trim();
                Log.i(PhrasesAdapter.class, "key: " + charText);
                listData.clear();
                if (charText.length() == 0) {
                    Log.i(PhrasesAdapter.class, "add all word");
                    listData.addAll(listData2);
                } else {
                    for (WordEntity vi : listData2) {
                        wordVN = android.text.Html.fromHtml(vi.getVi()).toString().toLowerCase();
                        wordVN = Common.stripAccents(wordVN);

                        if (wordVN.contains(charText)
//                            || charText.contains(wordVN)
                                ) {
                            listData.add(vi);
                        } else {
                            word1 = android.text.Html.fromHtml(vi.getO1()).toString().toLowerCase();
                            if (word1.contains(charText)
//                                || charText.contains(word1)
                                    ) {
                                listData.add(vi);
                            } else {
                                word2 = android.text.Html.fromHtml(vi.getO2()).toString().toLowerCase();
                                if (!word2.equals("") && (word2.contains(charText)
//                                    || charText.contains(word2)
                                )) {
                                    listData.add(vi);
                                }
                            }
                        }
                    }
                }

                if (listData.size() == 0) {
                    if (!charText.equals("")) {
                        number = Utility.convertToLong(charText);
                        if (number > -1) {
                            WordEntity entity = new WordEntity(NumberToWord.getWordFromNumber(number), charText);
                            listData.add(new WordEntity(NumberToWord.getWordFromNumber(number), charText));
                        }
                    }
                }
//                else
                resetAlphaSearch();
//                    notifyDataSetChanged();
            } catch (Exception e) {
                Log.e(PhrasesAdapter.class, "filter error:" + e.getMessage());
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);
            try {

//                resetAlphaSearch();
                PhrasesAdapter.this.notifyDataSetChanged();

                if (reLoad) {
                    reLoad = false;
                    Log.i(PhrasesAdapter.class, "reload data...");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            new ResetAdapter(currString).execute();
                        }
                    }, 800);

                } else {
//                    resetAlphaSearch();
//                    PhrasesAdapter.this.notifyDataSetChanged();
                    modify = false;
                }

            } catch (Exception e) {
                Log.e("SearchAllAdapter", "notify Error:" + e.getMessage());
            }
        }
    }
}