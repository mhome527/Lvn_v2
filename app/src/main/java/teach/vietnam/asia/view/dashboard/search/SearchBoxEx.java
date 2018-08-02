package teach.vietnam.asia.view.dashboard.search;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.util.AttributeSet;
import android.view.View;

import com.quinny898.library.persistentsearch.SearchBox;

import java.util.List;

import teach.vietnam.asia.utils.Log;

public class SearchBoxEx extends SearchBox {
    //        implements IClickListener {
    private final String TAG = "SearchBoxEx";
    public SearchAdapter adapter;
    IActionSearch iActionSearch;
    List<SearchGroupData> groups;

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

        //test only
//        setAdapter();
    }


    @Override
    public void updateResults() {
        Log.i(TAG, "updateResults...");
        String searchText = getSearchText();
        if (searchText.trim().equals("")) {
            if (adapter != null) {
                adapter.setListData(null);
//                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.GONE);
            }
        } else
            iActionSearch.loadData(searchText);
    }

    public void setAction(IActionSearch iActionSearch) {
        this.iActionSearch = iActionSearch;
    }

    public void setData(List<SearchGroupData> groups) {
        setAdapter(groups);
//        adapter.setListData(groups);
        if (groups.size() > 0) {
            adapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
        } else {
//            recyclerView.setVisibility(View.GONE);
        }
    }


    @Override
    public void setAdapter(Object object) {
        groups = (List<SearchGroupData>) object;

        adapter = new SearchAdapter(groups, iActionSearch);
        recyclerView.setAdapter(adapter);
        if (groups.size() > 0)
            adapter.toggleGroup(groups.size() - 1);
    }

    public void refresh() {
        adapter = new SearchAdapter(groups, iActionSearch);
        recyclerView.setAdapter(adapter);
    }

    public int getGroupSize() {
        if (groups == null)
            return 0;
        else
            return groups.size();
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
