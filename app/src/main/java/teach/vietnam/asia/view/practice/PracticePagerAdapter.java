package teach.vietnam.asia.view.practice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.PracticeDetailEntity;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.practice.detail.PracticeDetailActivity;

public class PracticePagerAdapter extends PagerAdapter {

    public List<WordEntity> lstData;
    public ArrayList<PracticeDetailEntity> lstExceriese;
    private PracticeDetailActivity activity;

    public PracticePagerAdapter(PracticeDetailActivity activity, List<WordEntity> lstData, String lang) {
        lstExceriese = new ArrayList();
        this.lstData = lstData;
        this.activity = activity;
        // Log.i("HomeAdapter", "HomePagerAdapter...");
        createData();
    }

    @Override
    public int getCount() {
        return lstData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @SuppressLint("InflateParams")
    public Object instantiateItem(ViewGroup collection, final int position) {
        final PracticeDetailEntity entity;
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.practice_page_item, null);
        entity = lstExceriese.get(position);

        ImageView imgE1 = (ImageView) view.findViewById(R.id.imgE1);
        ImageView imgE2 = (ImageView) view.findViewById(R.id.imgE2);
        ImageView imgE3 = (ImageView) view.findViewById(R.id.imgE3);
        ImageView imgE4 = (ImageView) view.findViewById(R.id.imgE4);

        final TextView tvText1 = (TextView) view.findViewById(R.id.tvText1);
        final TextView tvText2 = (TextView) view.findViewById(R.id.tvText2);
        final TextView tvText3 = (TextView) view.findViewById(R.id.tvText3);
        final TextView tvText4 = (TextView) view.findViewById(R.id.tvText4);


        setViewImg(imgE1, lstData.get(entity.pos1).getImg(), entity.pos1);
        setViewImg(imgE2, lstData.get(entity.pos2).getImg(), entity.pos2);
        setViewImg(imgE3, lstData.get(entity.pos3).getImg(), entity.pos3);
        setViewImg(imgE4, lstData.get(entity.pos4).getImg(), entity.pos4);

        tvText1.setText(lstData.get(entity.pos1).getVi());
        tvText2.setText(lstData.get(entity.pos2).getVi());
        tvText3.setText(lstData.get(entity.pos3).getVi());
        tvText4.setText(lstData.get(entity.pos4).getVi());

        tvText1.setVisibility(View.INVISIBLE);
        tvText2.setVisibility(View.INVISIBLE);
        tvText3.setVisibility(View.INVISIBLE);
        tvText4.setVisibility(View.INVISIBLE);

        view.setTag(position);
        ((ViewPager) collection).addView(view, 0);

        imgE1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View img) {
                int pos = (Integer) img.getTag();
                Log.i(PracticePagerAdapter.class, "createDate v1:" + pos);
                if (pos == entity.ans) {
                    ((ImageView) view.findViewById(R.id.imgCheck1)).setVisibility(View.VISIBLE);
//                    activity.tvAns.setVisibility(View.VISIBLE);
//                    activity.tvAns.setText(lstData.get(position).getVi());
                } else
                    ((ImageView) view.findViewById(R.id.imgCheck1x)).setVisibility(View.VISIBLE);

                tvText1.setVisibility(View.VISIBLE);
            }
        });

        imgE2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View img) {
                int pos = (Integer) img.getTag();
                if (pos == entity.ans) {
                    ((ImageView) view.findViewById(R.id.imgCheck2)).setVisibility(View.VISIBLE);
//                    activity.tvAns.setVisibility(View.VISIBLE);
//                    activity.tvAns.setText(lstData.get(position).getVi());
                } else
                    ((ImageView) view.findViewById(R.id.imgCheck2x)).setVisibility(View.VISIBLE);

                tvText2.setVisibility(View.VISIBLE);
            }
        });

        imgE3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View img) {
                int pos = (Integer) img.getTag();
                if (pos == entity.ans) {
                    ((ImageView) view.findViewById(R.id.imgCheck3)).setVisibility(View.VISIBLE);
//                    activity.tvAns.setVisibility(View.VISIBLE);
//                    activity.tvAns.setText(lstData.get(position).getVi());
                } else
                    ((ImageView) view.findViewById(R.id.imgCheck3x)).setVisibility(View.VISIBLE);

                tvText3.setVisibility(View.VISIBLE);
            }
        });

        imgE4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View img) {
                int pos = (Integer) img.getTag();
                if (pos == entity.ans) {
                    ((ImageView) view.findViewById(R.id.imgCheck4)).setVisibility(View.VISIBLE);
//                    activity.tvAns.setVisibility(View.VISIBLE);
//                    activity.tvAns.setText(lstData.get(position).getVi());
                } else
                    ((ImageView) view.findViewById(R.id.imgCheck4x)).setVisibility(View.VISIBLE);

                tvText4.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    private void setViewImg(ImageView view, String img, int pos) {
        int resourceId = Utility.getResourcesID(activity, img);
        if (resourceId > 0)
            view.setBackgroundResource(resourceId);
        view.setTag(pos);
    }

    private void createData() {
        int value = 0;
        PracticeDetailEntity exceriese;
        for (int i = 0; i < lstData.size(); i++) {
            exceriese = new PracticeDetailEntity();

            value = Utility.randomPos(lstData.size(), -1);
            exceriese.pos1 = value;

            value = Utility.randomPos(lstData.size(), exceriese.pos1);
            exceriese.pos2 = value;

            value = Utility.randomPos(lstData.size(), exceriese.pos1, exceriese.pos2);
            exceriese.pos3 = value;

            value = Utility.randomPos(lstData.size(), exceriese.pos1, exceriese.pos2, exceriese.pos3);
            exceriese.pos4 = value;

            exceriese.ans = i;
            if (exceriese.pos1 != i && exceriese.pos2 != i && exceriese.pos3 != i && exceriese.pos4 != i) {
                value = Utility.randomPos(4, -1);
                switch (value) {
                    case 0:
                        exceriese.pos1 = i;
                        break;
                    case 1:
                        exceriese.pos2 = i;
                        break;
                    case 2:
                        exceriese.pos3 = i;
                        break;
                    case 3:
                        exceriese.pos4 = i;
                        break;
                }
            }
            lstExceriese.add(exceriese);
            // ULog.i(ExceriesePagerAdapter.class, "createData v1:" + exceriese.pos1 + "; v2:" + exceriese.pos2 + "; v3:" + exceriese.pos3
            // + "; v4:" + exceriese.pos4);
        }

    }

}
