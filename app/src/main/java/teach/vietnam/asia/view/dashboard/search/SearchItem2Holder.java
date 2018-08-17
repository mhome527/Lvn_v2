package teach.vietnam.asia.view.dashboard.search;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.R;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.view.base.BaseChildExViewHolder;

public class SearchItem2Holder extends BaseChildExViewHolder {

    @BindView(R.id.tvVn)
    TextView tvVn;

    @BindView(R.id.tvOther)
    TextView tvOther;

    @BindView(R.id.imgSound)
    ImageView imgSound;

    private SearchEntity entity;
    boolean isPurchased;
    private AudioPlayer audio;

    public SearchItem2Holder(View itemView, final boolean isPurchased, final IActionSearch iActionSearch) {
        super(itemView);
        this.isPurchased = isPurchased;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPurchased) {
                    String str = entity.vn.replaceAll("\\?", "").replaceAll("\\.", "").replaceAll("!", "").replaceAll(",", "");
                    str = android.text.Html.fromHtml(str).toString();
                    audio = new AudioPlayer(BaseApplication.getInstance());
                    audio.speakWord(str);
                } else {
                    iActionSearch.onSearchClick(entity);
                }
            }
        });
    }

    public void bind(SearchEntity entity) {
        this.entity = entity;

        tvVn.setText(Html.fromHtml(entity.vn));
        tvOther.setText(Html.fromHtml(entity.ot));

        if (isPurchased) {
            imgSound.setImageResource(R.drawable.ic_speaker);
        } else
            imgSound.setImageResource(R.drawable.ic_lock);
    }
}
