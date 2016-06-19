package ru.maslov.sandbox;

/**
 * Created by Администратор on 20.06.2016.
 */
public class TestFragmentTwo extends BaseFragment<TFTwoPresenter> implements ITFTwo {
    @Override
    protected TFTwoPresenter getPresenter() {
        return new TFTwoPresenter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.tftwo_layout;
    }
}
