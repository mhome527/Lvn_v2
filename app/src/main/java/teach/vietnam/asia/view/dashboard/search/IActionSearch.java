package teach.vietnam.asia.view.dashboard.search;


public interface IActionSearch {

    //hien thi noi dung search
    void loadData(String keySearch);

    //khi click vao danh sach dang hien thi
    void onSearchClick(SearchEntity entity);
}
