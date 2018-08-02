package teach.vietnam.asia.view.dashboard.search;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.base.BaseHeaderExViewHolder;


public class SearchHeaderHolder extends BaseHeaderExViewHolder {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.imgIcon)
    ImageView imgIcon;

    IActionSearch iActionSearch;
    int pos = 0;

    public SearchHeaderHolder(View itemView, final IActionSearch iActionSearch) {
        super(itemView);
        this.iActionSearch = iActionSearch;
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                iActionSearch.onSearchClick(null);
//            }
//        });
    }

    @Override
    public ImageView getIconArrow() {
        return imgIcon;
    }

    @Override
    public void expand() {
        super.expand();
        iActionSearch.onSearchHeaderClick(true, pos);
    }

    @Override
    public void collapse() {
        super.collapse();
        iActionSearch.onSearchHeaderClick(false, pos);
    }

    public void bind(int pos, String title) {
        tvTitle.setText(title);
        this.pos = pos;
    }
}
