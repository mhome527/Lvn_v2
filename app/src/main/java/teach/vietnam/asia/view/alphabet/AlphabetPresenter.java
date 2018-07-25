package teach.vietnam.asia.view.alphabet;

import java.util.ArrayList;
import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.TblAlphabetEx;
import teach.vietnam.asia.view.base.BasePresenter;
import teach.vietnam.asia.view.action.ICallback;

/**
 * Created by HuynhTD on 12/21/2016.
 */

public class AlphabetPresenter extends BasePresenter<AlphabetActivity> {
    public AlphabetPresenter(AlphabetActivity activity) {
        super(activity);
    }

    public void loadData(ICallback callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public Object onBackground() {
                        return getData();
                    }
                }
        );
    }

    public List<TblAlphabetEx> getData() {
        List<TblAlphabetEx> items = new ArrayList<>();

        items.add(new TblAlphabetEx("a", "Tr<font color='red'>á</font>i me", R.drawable.a_tamarind));
        items.add(new TblAlphabetEx("ă", "C<font color='red'>ă</font>n nhà", R.drawable.a_house));
        items.add(new TblAlphabetEx("â", "Cái <font color='red'>ấ</font>m", R.drawable.a_kettle));
        items.add(new TblAlphabetEx("b", "Con <font color='red'>b</font>ò", R.drawable.a_cow));
        items.add(new TblAlphabetEx("c", "<font color='red'>C</font>ây kéo", R.drawable.a_scissors));
        items.add(new TblAlphabetEx("ch", "<font color='red'>Ch</font>iếc thuyền", R.drawable.a_ship));
        items.add(new TblAlphabetEx("d", "Con <font color='red'>d</font>ê", R.drawable.a_goat));
        items.add(new TblAlphabetEx("đ", "Xe <font color='red'>đ</font>ạp", R.drawable.a_bike));
        items.add(new TblAlphabetEx("e", "Trái m<font color='red'>e</font>", R.drawable.a_tamarind));
        items.add(new TblAlphabetEx("ê", "Con <font color='red'>ế</font>ch", R.drawable.a_frog));
        items.add(new TblAlphabetEx("g", "Con <font color='red'>g</font>à", R.drawable.a_chicken));
        items.add(new TblAlphabetEx("gi", "Cái <font color='red'>gi</font>ếng", R.drawable.a_wells));
        items.add(new TblAlphabetEx("h", "Trái dưa <font color='red'>h</font>ấu", R.drawable.a_watermelon));
        items.add(new TblAlphabetEx("i", "Trá<font color='red'>i</font> me", R.drawable.a_tamarind));
        items.add(new TblAlphabetEx("k", "Cây <font color='red'>k</font>éo", R.drawable.a_scissors));
        items.add(new TblAlphabetEx("kh", "Trái <font color='red'>kh</font>ế", R.drawable.a_star_fruit));
        items.add(new TblAlphabetEx("l", "Cái <font color='red'>l</font>y", R.drawable.a_glass));
        items.add(new TblAlphabetEx("m", "Trái <font color='red'>m</font>e", R.drawable.a_tamarind));
        items.add(new TblAlphabetEx("n", "Cái <font color='red'>n</font>ồi", R.drawable.a_pot));
        items.add(new TblAlphabetEx("ng", "Con o<font color='red'>ng</font>", R.drawable.a_bee));
        items.add(new TblAlphabetEx("nh", "Căn <font color='red'>nh</font>à", R.drawable.a_house));
        items.add(new TblAlphabetEx("o", "Con <font color='red'>o</font>ng", R.drawable.a_bee));
        items.add(new TblAlphabetEx("ô", "Con <font color='red'>ố</font>c", R.drawable.a_snail));
        items.add(new TblAlphabetEx("ơ", "Tô Ph<font color='red'>ở</font>", R.drawable.a_pho));
        items.add(new TblAlphabetEx("p", "xe đạ<font color='red'>p</font>", R.drawable.a_bike));
        items.add(new TblAlphabetEx("ph", "Tô <font color='red'>Ph</font>ở", R.drawable.a_pho));
        items.add(new TblAlphabetEx("q", "Cái <font color='red'>q</font>uần", R.drawable.a_trousers));
        items.add(new TblAlphabetEx("r", "Con <font color='red'>r</font>ắn", R.drawable.a_snake));
        items.add(new TblAlphabetEx("s", "Con <font color='red'>s</font>óc", R.drawable.a_squirrel));
        items.add(new TblAlphabetEx("t", "<font color='red'>T</font>ô Phở", R.drawable.a_pho));
        items.add(new TblAlphabetEx("th", "Chiếc <font color='red'>th</font>uyền", R.drawable.a_fish));
        items.add(new TblAlphabetEx("tr", "<font color='red'>Tr</font>ái me", R.drawable.a_tamarind));
        items.add(new TblAlphabetEx("u", "Bàn <font color='red'>ủ</font>i", R.drawable.a_iron));
        items.add(new TblAlphabetEx("ư", "Trái d<font color='red'>ư</font>a hấu", R.drawable.a_watermelon));
        items.add(new TblAlphabetEx("v", "Cái <font color='red'>v</font>õng", R.drawable.a_hammock));
        items.add(new TblAlphabetEx("x", "<font color='red'>X</font>e đạp", R.drawable.a_bike));
        items.add(new TblAlphabetEx("y", "<font color='red'>y</font> tá", R.drawable.a_nurse));

        return items;
    }
}
