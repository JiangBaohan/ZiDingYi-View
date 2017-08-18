package com.example.demo18__;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * popuwidow下拉列表,属于组合式控件
 * 1.添加ButterKnife的依赖,取消ActionBar,使用ToolBar代替
 * 2.完成整体的布局,初始化控件,设置点击事件
 * 3.初始化popuwindow所要显示的数据(添加数据)
 * 4.初始化popuwindow控件的设置
 * 5.popuwindow与Listview相关联
 * 6.三个popuwindow所依附的LinearLayout,添加点击事件,做对应逻辑处理
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.supplier_list_product_tv)
    TextView mProductTv;  // 可以修改名称
    @Bind(R.id.supplier_list_product)
    LinearLayout mProduct;
    @Bind(R.id.supplier_list_sort_tv)
    TextView mSortTv;     // 可以修改名称
    @Bind(R.id.supplier_list_sort)
    LinearLayout mSort;
    @Bind(R.id.supplier_list_activity_tv)
    TextView mActivityTv;   // 可以修改名称
    @Bind(R.id.supplier_list_activity)
    LinearLayout mActivity;
    @Bind(R.id.supplier_list_lv)
    ListView mSupplierListLv;

    private ArrayList<Map<String, String>> menuData1, menuData2, menuData3;
    private PopupWindow mpopupMenu;
    private ListView mPopListView;
    private SimpleAdapter mMenuAdapter1, mMenuAdapter2, mMenuAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化popuwindow所要显示的数据
        initData();
        //初始化popuwindow控件
        initPopMenu();
    }


    private void initPopMenu() {
//把包裹listview布局的xml文件转换为view对象
        View popview = LayoutInflater.from(this).inflate(R.layout.popwin_supplier, null);
        //创建popuwindow对象,参数1.popuwindow要显示的布局,参数2.3:定义宽和高
        mpopupMenu = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //设置popuwidow外部可以点击
        mpopupMenu.setOutsideTouchable(true);
        //设置popuwidow里面的listview有焦点
        mpopupMenu.setFocusable(true);
        //如果想让popuwindow有动画效果,就必须有这段代码
        mpopupMenu.setBackgroundDrawable(new ColorDrawable());
        //设置结束时监听事件
        mpopupMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //设置TextView的颜色,把所有LinearLayout的文本颜色该为灰色
                mProductTv.setTextColor(Color.parseColor("#7A9DA4"));
                mSortTv.setTextColor(Color.parseColor("#7A9DA4"));
                mActivityTv.setTextColor(Color.parseColor("#7A9DA4"));

            }
        });
//设置点击popuwindow以外的地方,是popuwindow消失
        LinearLayout list_bottom = (LinearLayout) popview.findViewById(R.id.popwin_supplier_list_bottom);
        list_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpopupMenu.dismiss();
            }
        });
        //获取listview对象
        mPopListView = (ListView) popview.findViewById(R.id.popwin_supplier_list_lv);
        //创建SimpleAdapter,一个Listview原生封装的适配器
        mMenuAdapter1 = new SimpleAdapter(this, menuData1, R.layout.item_listview_popwin, new String[]{"name"}, new int[]{R.id.listview_popwind_tv});
        mMenuAdapter2 = new SimpleAdapter(this, menuData2, R.layout.item_listview_popwin, new String[]{"name"}, new int[]{R.id.listview_popwind_tv});
        mMenuAdapter3 = new SimpleAdapter(this, menuData3, R.layout.item_listview_popwin, new String[]{"name"}, new int[]{R.id.listview_popwind_tv});

       //设置popuwindow里的listview点击事件,当点击listview里的一个item时,把这个item数据显示到最上方
        mPopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //首先让popuwindow消失
                mpopupMenu.dismiss();

                switch (menuIndex){
                    case 0 :
                         String currentProduct = menuData1.get(i).get("name");
                mProductTv.setText(currentProduct);
                        break;
                    case 1:
                        String currentSort = menuData2.get(i).get("name");
                        mSortTv.setText(currentSort);
                        break;
                    case 2:
                        String currentActivity = menuData3.get(i).get("name");
                        mActivityTv.setText(currentActivity);
                        break;
                    default:
                        break;
                }

            }
        });
    }
    /**
     * 3.初始化数据,popuwindow所需,共有三个
     * 所以要封装好三个数据,这里是假数据,(常理是再网络请求的数据)
     */
    private void initData() {
        //创建一个popuwindow加载数据的大盒子,Map集合
        menuData1 = new ArrayList<>();
        //存放string的字符串数组
        String[] menuStr1 = new String[]{"全部", "粮油", "衣服", "图书", "电子产品", "酒水饮料", "水果"};
        //创建一个小盒子,放编号和值
        Map<String, String> map1;
        for (int i = 0; i < menuStr1.length; i++) {
            map1 = new HashMap<>();
            map1.put("name", menuStr1[i]);
            menuData1.add(map1);
        }


        menuData2 = new ArrayList<>();
        //存放string的字符串数组
        String[] menuStr2 = new String[]{"综合排序", "配送费最低"};
        //创建一个小盒子,放编号和值
        Map<String, String> map2;
        for (int i = 0; i < menuStr2.length; i++) {
            map2 = new HashMap<>();
            map2.put("name", menuStr2[i]);
            menuData2.add(map2);
        }

        menuData3 = new ArrayList<>();
        //存放string的字符串数组
        String[] menuStr3 = new String[]{"优惠活动", "特价活动", "免配送费", "可在线支付"};
        //创建一个小盒子,放编号和值
        Map<String, String> map3;
        for (int i = 0; i < menuStr3.length; i++) {
            map3 = new HashMap<>();
            map3.put("name", menuStr3[i]);
            menuData3.add(map3);
        }
//每完成一个步骤运行一下,测试看代码有什么问题
    }
    //设置一个标记,方便对点击不同linerlayout做对应操作
     int menuIndex;


    //6.三个popuwindow所衣服的linearlayout,设置点击事件,也就是三个linearlayout具备了点击事件
    @OnClick({R.id.supplier_list_product, R.id.supplier_list_sort, R.id.supplier_list_activity})
    public void onClick(View view) {
        switch (view.getId()) {
            //第一个popuwindow所之行的点击后的逻辑
            case R.id.supplier_list_product:
                //是指其textview点击是为绿色
                mProductTv.setTextColor(Color.parseColor("#39ac69"));
                //设置popuwindow里的listview适配器
                mPopListView.setAdapter(mMenuAdapter1);
                //让popwindow显示出来,参数1:view对象,约定了popuwindow再哪个控件下显示,
                //参数2.3:决定popuwindow的坐标,x轴,y轴
                mpopupMenu.showAsDropDown(mProduct, 0, 2);
                menuIndex=0;
                break;
            case R.id.supplier_list_sort:
                mProductTv.setTextColor(Color.parseColor("#39ac69"));
                mPopListView.setAdapter(mMenuAdapter2);
                mpopupMenu.showAsDropDown(mProduct, 0, 2);
                menuIndex=1;
                break;
            case R.id.supplier_list_activity:
                mProductTv.setTextColor(Color.parseColor("#39ac69"));
                mPopListView.setAdapter(mMenuAdapter3);
                mpopupMenu.showAsDropDown(mProduct, 0, 2);
                menuIndex=2;
                break;
        }
    }
}
