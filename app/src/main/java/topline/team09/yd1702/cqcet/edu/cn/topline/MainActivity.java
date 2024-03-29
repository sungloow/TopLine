package topline.team09.yd1702.cqcet.edu.cn.topline;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv_main_title;
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RelativeLayout rl_title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("首页");
        rl_title_bar = (RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //RadioGroup选中状态改变监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        //setCurrentItem()方法中第二个参数控制页面切换动画，true：打开，false：关闭
                        viewPager.setCurrentItem(0,false);
                        break;
                    case R.id.rb_count:
                        viewPager.setCurrentItem(1,false);
                        break;
                    case R.id.rb_video:
                        viewPager.setCurrentItem(2,false);
                        break;
                    case R.id.rb_me:
                        viewPager.setCurrentItem(3,false);
                        break;
                }
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //ViewPager页面切换监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        radioGroup.check(R.id.rb_home);
                        tv_main_title.setText("首页");
                        rl_title_bar.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_count);
                        tv_main_title.setText("统计");
                        rl_title_bar.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_video);
                        tv_main_title.setText("视频");
                        rl_title_bar.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        radioGroup.check(R.id.rb_me);
                        rl_title_bar.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    protected long exitTime;    //记录第一次点击的时间
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis() -exitTime)>2000){
                Toast.makeText(MainActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else {
                MainActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}