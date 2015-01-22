package com.example.sergio.lectorrss.android.presenter;

import com.example.sergio.lectorrss.android.view.NoticiasView;

/**
 * Created by sergio on 21/01/15.
 */
public class NoticiasPresenterImpl implements NoticiasPresenter {
    private NoticiasView noticiasView;

    public NoticiasPresenterImpl(NoticiasView noticiasView) {
        this.noticiasView = noticiasView;
    }
    @Override
    public void clickRefreshButton() {
        this.noticiasView.refreshView();
    }
}
