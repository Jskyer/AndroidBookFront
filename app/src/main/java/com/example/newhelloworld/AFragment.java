package com.example.newhelloworld;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.lang.reflect.Method;

public class AFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button jumpBFragment = view.findViewById(R.id.jumpBFragment);
        jumpBFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("content","How are you?");
                Navigation.findNavController(getView())
                        //导航
                        .navigate(R.id.action_afragment_to_bfragment,bundle);
            }
        });
    }


}


/*
public class AFragment extends AppCompatActivity {

    private SearchView.SearchAutoComplete sac_key; // 声明一个搜索自动完成的编辑框对象
    private String[] hintArray = {"iphone", "iphone8", "iphone8 plus", "iphone7", "iphone7 plus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_a);
        // 从布局文件中获取名叫tl_head的工具栏
        Toolbar tl_head = findViewById(R.id.tl_head);
        // 设置工具栏左边的导航图标
        tl_head.setNavigationIcon(R.mipmap.ic_back);
        // 设置工具栏的标题文本
        tl_head.setTitle("工具栏页面");
        // 设置工具栏的标题文字颜色
        tl_head.setTitleTextColor(Color.RED);
        tl_head.setTitleMargin(0,0,0,0);
        // 设置工具栏的背景
        tl_head.setBackgroundResource(R.color.blue_light);
        // 使用tl_head替换系统自带的ActionBar
        setSupportActionBar(tl_head);
        // 给tl_head设置导航图标的点击监听器
        // setNavigationOnClickListener必须放到setSupportActionBar之后，不然不起作用
        tl_head.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // 结束当前页面
            }
        });
    }

    // 根据菜单项初始化搜索框
    @SuppressLint("RestrictedApi")
    private void initSearchView(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        // 从菜单项中获取搜索框对象
        SearchView searchView = (SearchView) menuItem.getActionView();
        // 设置搜索框默认自动缩小为图标
        searchView.setIconifiedByDefault(getIntent().getBooleanExtra("collapse", true));
        // 设置是否显示搜索按钮。搜索按钮只显示一个箭头图标，Android暂不支持显示文本。
        // 查看Android源码，搜索按钮用的控件是ImageView，所以只能显示图标不能显示文字。
        searchView.setSubmitButtonEnabled(true);
        // 从系统服务中获取搜索管理器
        SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // 创建搜索结果页面的组件名称对象
        ComponentName cn = new ComponentName(this, SearchResultActvity.class);
        // 从结果页面注册的activity节点获取相关搜索信息，即searchable.xml定义的搜索控件
        SearchableInfo info = sm.getSearchableInfo(cn);
        if (info == null) {
            return;
        }
        // 设置搜索框的可搜索信息
        searchView.setSearchableInfo(info);
        // 从搜索框中获取名叫search_src_text的自动完成编辑框
        sac_key = searchView.findViewById(R.id.search_src_text);
        // 设置自动完成编辑框的文本颜色
        sac_key.setTextColor(Color.WHITE);
        // 设置自动完成编辑框的提示文本颜色
        sac_key.setHintTextColor(Color.WHITE);
        // 给搜索框设置文本变化监听器
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 搜索关键词完成输入
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 搜索关键词发生变化
            public boolean onQueryTextChange(String newText) {
                doSearch(newText);
                return true;
            }
        });
        Bundle bundle = new Bundle(); // 创建一个新包裹
        bundle.putString("hi", "hello"); // 往包裹中存放名叫hi的字符串
        // 设置搜索框的额外搜索数据
        searchView.setAppSearchData(bundle);
    }

    // 自动匹配相关的关键词列表
    private void doSearch(String text) {
        if (text.indexOf("i") == 0) {
            // 根据提示词数组构建一个数组适配器
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.search_list_auto, hintArray);
            // 设置自动完成编辑框的数组适配器
            sac_key.setAdapter(adapter);
            // 给自动完成编辑框设置列表项的点击监听器
            sac_key.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                // 一旦点击关键词匹配列表中的某一项，就触发点击监听器的onItemClick方法
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    sac_key.setText(((TextView) view).getText());
                }
            });
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // 显示菜单项左侧的图标
        setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 从menu_search.xml中构建菜单界面布局
        getMenuInflater().inflate(R.menu.menu_search, menu);
        // 初始化搜索框
        initSearchView(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) { // 点击了工具栏左边的返回箭头
            finish();
        } else if (id == R.id.menu_refresh) { // 点击了刷新图标
            Toast.makeText(this,"刷新了", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_about) { // 点击了关于菜单项
            Toast.makeText(this, "这个是工具栏的演示demo", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_quit) { // 点击了退出菜单项
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // 显示OverflowMenu的Icon
    public static void setOverflowIconVisible(int featureId, Menu menu) {
        // ActionBar的featureId是8，Toolbar的featureId是108
        if (featureId % 100 == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    // setOptionalIconsVisible是个隐藏方法，需要通过反射机制调用
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

<activity
            android:name=".SearchResultActvity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.SEARCH"/>
            </intent-filter>
            <meta-data android:name="android.app.searchable" android:resource="@xml/searchable"/>
        </activity>

 */

