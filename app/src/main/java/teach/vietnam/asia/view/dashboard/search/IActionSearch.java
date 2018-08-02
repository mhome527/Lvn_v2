package teach.vietnam.asia.view.dashboard.search;


public interface IActionSearch {

    //hien thi noi dung search
    void loadData(String keySearch);

    //khi click header
    void onSearchHeaderClick(boolean type, int pos); //true: expend; false: collapse

    //khi click vao danh sach dang hien thi
    void onSearchClick(SearchEntity entity);
}
