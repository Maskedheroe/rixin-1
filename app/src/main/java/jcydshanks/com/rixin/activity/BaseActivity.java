package jcydshanks.com.rixin.activity;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

import fragment.RixinDelegate;
import jcydshanks.com.rixin.R;
import jcydshanks.com.rixin.fragment.NewsFragment;
import jcydshanks.com.rixin.interfaces.ISetRootDelegate;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity extends SupportActivity {


    private ISetRootDelegate setRootDelegate;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
        initImmersionBar();
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);

        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null&&setRootDelegate!=null){
            loadRootFragment(R.id.delegate_container,setRootDelegate.setRootDelegate());
        }
    }

    private void loadRootFragment(int delegate_container, RixinDelegate rixinDelegate) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }

    public void initImmersionBar(){
        ImmersionBar.with(this).statusBarDarkFont(true).transparentStatusBar() .init();
    }
}
