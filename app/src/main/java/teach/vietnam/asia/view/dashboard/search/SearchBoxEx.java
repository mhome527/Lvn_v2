package teach.vietnam.asia.view.dashboard.search;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.util.AttributeSet;
import android.view.View;

import com.quinny898.library.persistentsearch.SearchBox;

import java.util.ArrayList;
import java.util.List;

import teach.vietnam.asia.utils.Log;

public class SearchBoxEx extends SearchBox{
//        implements IClickListener {
    private final String TAG = "SearchBoxEx";
    SearchAdapter adapter;
    IActionSearch iActionSearch;

    public SearchBoxEx(Context context) {
        super(context);
    }

    public SearchBoxEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchBoxEx(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        setAdapter();
    }


    @Override
    public void updateResults() {
        Log.i(TAG, "updateResults...");
        String searchText = getSearchText();
        if (searchText.trim().equals("")) {
            adapter.setListData(null);
            adapter.notifyDataSetChanged();
        } else
            iActionSearch.loadData(searchText);
    }

    public void setAction(IActionSearch iActionSearch) {
        this.iActionSearch = iActionSearch;
    }

    public void setData(List<SearchGroupData> groups) {
        setAdapter();
        adapter.setListData(groups);
        if (groups.size() > 0) {
            adapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }


    @Override
    public void setAdapter() {
        List<SearchGroupData> groups = new ArrayList<>();

        adapter = new SearchAdapter(groups, iActionSearch);
        recyclerView.setAdapter(adapter);
    }

    // ============== IClickListener ================
//    @Override
//    public void onClick(View view, int position) {
//        Log.i(TAG, "onClick " + position);
////        closeSearch();
//        iActionSearch.onSearchClick(adapter.getItem(position));
//    }

//    @Override
//    public void actionClick(View view, int position) {
//        iActionSearch.onSearchClick(adapter.getItem(position));
//    }
//
//    @Override
//    public void actionLongClick(View view, int position) {
//        Log.i(TAG, "actionLongClick... pos:" + position);
//
//    }
    // ===============
}
