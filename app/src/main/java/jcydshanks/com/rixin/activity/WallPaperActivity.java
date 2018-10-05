package jcydshanks.com.rixin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.gyf.barlibrary.ImmersionBar;

import fragment.RixinDelegate;
import jcydshanks.com.rixin.R;
import jcydshanks.com.rixin.interfaces.ISetRootDelegate;

public class WallPaperActivity extends BaseActivity  {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_paper);
        toolbar = findViewById(R.id.topview);
    }



    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.write).statusBarDarkFont(true).fitsSystemWindows(true).init();
    }

}
