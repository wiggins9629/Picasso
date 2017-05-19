package com.wiggins.picasso;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wiggins.picasso.base.BaseActivity;
import com.wiggins.picasso.utils.Constant;
import com.wiggins.picasso.utils.PicassoUtil;
import com.wiggins.picasso.utils.UIUtils;
import com.wiggins.picasso.widget.TitleView;

/**
 * @Description Picasso使用详解
 * @Author 一花一世界
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private MainActivity mActivity = null;
    private TitleView titleView;
    private Button mBtnImageLoading;
    private ImageView mIvPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;

        initView();
        setListener();
    }

    private void initView() {
        titleView = (TitleView) findViewById(R.id.titleView);
        titleView.setAppTitle(UIUtils.getString(R.string.title));
        titleView.setLeftImageVisibility(View.GONE);
        mIvPic = (ImageView) findViewById(R.id.iv_pic);
        mBtnImageLoading = (Button) findViewById(R.id.btn_image_loading);
    }

    private void setListener() {
        mBtnImageLoading.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_image_loading:
                PicassoUtil.loadImage(mActivity, Constant.picUrl, mIvPic, true);
                break;
        }
    }
}
