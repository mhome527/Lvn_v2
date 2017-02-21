package teach.vietnam.asia.view.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.entity.tblVietEN;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.NumberToWord;
import teach.vietnam.asia.utils.Utility;


public class SearchAdapter extends BaseAdapter implements SectionIndexer {

    private Context context;
    private List<WordEntity> listData;
    private List<WordEntity> listData2;
    private LayoutInflater layoutInflater;
    private String lang = "";
    private String[] alpha;

    public SearchAdapter(Context context, List<WordEntity> listData) {
        int i = 0;
        this.context = context;
        this.listData = listData;
        listData2 = new ArrayList();
        try {
            listData2.addAll(listData);
            layoutInflater = LayoutInflater.from(context);
//            lang = context.getString(R.string.language);

            alpha = null;
            alpha = new String[listData.size()];

//            for (tblVietEN viet : listData) {
//                alpha[i++] = viet.getO1().toString().replaceAll("<u>", "").replaceAll("</u>", "").split(" ")[0];
//            }

            for (WordEntity entity : listData) {
                alpha[i++] = entity.getO1().replaceAll("<u>", "").replaceAll("</u>", "").split(" ")[0];
            }

        } catch (Exception e) {
            Log.e(SearchAdapter.class, "SearchAllAdapter Error: " + e.getMessage());
        }

    }

    // public LearnAdapter(Context context) {
    // mContext = context;
    // }

    public int getCount() {
        return listData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    public View getView(int position, View view, ViewGroup viewGroup) {
        int resourceId;
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.search_item, null);
            holder.tvOther = (TextView) view.findViewById(R.id.tvOther);
            holder.tvVn = (TextView) view.findViewById(R.id.tvVn);
            holder.imgWord = (ImageView) view.findViewById(R.id.imgWord);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (listData.get(position).getO1() != null && !listData.get(position).getO1().equals(""))
            holder.tvOther.setText(listData.get(position).getO1());

        holder.tvVn.setText(listData.get(position).getVi());

        // img.setScaleType(ImageView.ScaleType.FIT_XY);
        resourceId = Utility.getResourcesID(context, listData.get(position).getImg());
        if (resourceId > 0) {
            holder.imgWord.setImageResource(resourceId);
            // holder.imgWord.setTag(resourceId);
        } else {
            holder.imgWord.setImageResource(0);
            Log.i(SearchAdapter.class, "don't image load");
        }
        return view;
    }

    private void resetAlphaSearch() {
        int i = 0;
        alpha = null;
        alpha = new String[listData.size()];

//        for (tblVietEN viet : listData) {
//            alpha[i++] = viet.getO1().toString().replaceAll("<u>", "").replaceAll("</u>", "").split(" ")[0];
//        }
        for (WordEntity entity : listData) {
            alpha[i++] = entity.getO1().replaceAll("<u>", "").replaceAll("</u>", "").split(" ")[0];
        }

    }

    @SuppressLint("DefaultLocale")
    public void filter(String charText) {
        String word1 = "", word2 = "";
        long number;
        tblVietEN tmp;
        try {
            charText = charText.toLowerCase(Locale.getDefault()).trim();
            Log.i(SearchAdapter.class, "key: " + charText);
            listData.clear();
            if (charText.length() == 0) {
                listData.addAll(listData2);
            } else {
                for (WordEntity entity : listData2) {
                    word1 = android.text.Html.fromHtml(entity.getO1()).toString().toLowerCase();
                    word2 = android.text.Html.fromHtml(entity.getO2()).toString().toLowerCase();
                    if (word1.contains(charText) || charText.contains(word1)) {
                        listData.add(entity);
                    } else if (!word2.equals("") && (word2.contains(charText) || charText.contains(word2))) {
                        listData.add(entity);
                    }
                }
            }

            if (listData.size() == 0) {
                if (!charText.equals("")) {
                    number = Utility.convertToLong(charText);
                    if (number > -1)
                        listData.add(new WordEntity(NumberToWord.getWordFromNumber(number), charText));
//                        listData.add(Utility.getDataObject(lang, NumberToWord.getWordFromNumber(number), charText));
                }
            }
            resetAlphaSearch();
            notifyDataSetChanged();
        } catch (Exception e) {
            Log.e(SearchAdapter.class, "filter error:" + e.getMessage());
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

    public class ViewHolder {
        TextView tvOther;
        TextView tvVn;
        ImageView imgWord;
    }
}