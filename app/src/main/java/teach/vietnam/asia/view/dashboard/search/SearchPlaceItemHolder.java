//package teach.vietnam.asia.view.dashboard.search;
//
//import android.view.View;
//import android.widget.TextView;
//
//import com.pax.vietnameseapp.R;
//import com.pax.vietnameseapp.activity.base.BaseViewHolder;
//import com.pax.vietnameseapp.activity.callback.IClickListener;
//
//import butterknife.BindView;
//
//public class SearchPlaceItemHolder extends BaseViewHolder {
//
//    @BindView(R.id.tvVn)
//    TextView tvVn;
//
//    @BindView(R.id.tvTitle1)
//    TextView tvTitle1;
//
//    @BindView(R.id.tvTitle2)
//    TextView tvTitle2;
//
//    public SearchPlaceItemHolder(View itemView, final IClickListener iClickListener) {
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
//        tvVn.setText(entity.vietnamese);
//        tvTitle1.setText(entity.title1);
//        tvTitle2.setText(entity.title2);
//    }
//}
