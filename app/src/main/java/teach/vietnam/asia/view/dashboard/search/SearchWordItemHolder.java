//package teach.vietnam.asia.view.dashboard.search;
//
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import butterknife.BindView;
//import teach.vietnam.asia.R;
//import teach.vietnam.asia.view.BaseViewHolder;
//import teach.vietnam.asia.view.IClickListener;
//
//public class SearchWordItemHolder extends BaseViewHolder {
//
//    @BindView(R.id.tvTitle)
//    TextView tvTitle;
//
//    @BindView(R.id.imgIcon)
//    ImageView imgIcon;
//
//    public SearchWordItemHolder(View itemView, final IClickListener iClickListener) {
//        super(itemView);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                iClickListener.onClick(view, getAdapterPosition());
//            }
//        });
//    }
//
//    public void setData(SearchEntity entity) {
//        tvTitle.setText(entity.vietnamese);
//    }
//}
