package teach.vietnam.asia.view.base;

import android.view.View;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import butterknife.ButterKnife;

public class BaseChildExViewHolder extends ChildViewHolder {

    public BaseChildExViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
