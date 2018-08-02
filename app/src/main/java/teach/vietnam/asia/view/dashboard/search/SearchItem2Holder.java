package teach.vietnam.asia.view.dashboard.search;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.base.BaseChildExViewHolder;

public class SearchItem2Holder extends BaseChildExViewHolder {

    @BindView(R.id.tvVn)
    TextView tvVn;

    @BindView(R.id.tvOther)
    TextView tvOther;

//    @BindView(R.id.imgSpeak)
//    ImageView imgSpeak;

    private SearchEntity entity;

    public SearchItem2Holder(View itemView, final IActionSearch iActionSearch) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iActionSearch.onSearchClick(entity);
            }
        });
    }

    public void bind(SearchEntity entity) {
        this.entity = entity;
        tvVn.setText(Html.fromHtml(entity.vn));
        tvOther.setText(Html.fromHtml(entity.ot));


    }
}
