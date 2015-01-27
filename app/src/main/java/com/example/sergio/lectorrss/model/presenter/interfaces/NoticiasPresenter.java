package com.example.sergio.lectorrss.model.presenter.interfaces;

/**
 * Created by sergio on 21/01/15.
 */

public interface NoticiasPresenter {
    public void clickRefreshButton();
    public void onItemClicked(int position);
    public void refreshView();

}
