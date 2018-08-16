package czh.itemlayout

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import czh.widget.ItemBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DensityUtils.setDefault(this)
        setContentView(R.layout.activity_main)
        val items = arrayListOf<ItemBean>()
        items.add(ItemBean(R.mipmap.ic_launcher, "测试"))
        items.add(ItemBean(R.mipmap.ic_launcher, "测试", "测试"))
        items.add(ItemBean(R.mipmap.ic_launcher, "测试", height = 30))
        items.add(ItemBean(R.mipmap.ic_launcher, "测试"))
        items.add(ItemBean(R.mipmap.ic_launcher, "测试"))
        items.add(ItemBean(R.mipmap.ic_launcher, "测试", height = itemLayout.lineHeight))
        itemLayout.apply {
            mData = items
            //右边箭头
            defaultIcon=R.mipmap.ico_next
            //item高度
            itemHeight = resources.getDimensionPixelOffset(R.dimen.x98)
            //左边TextView
            leftTextColor = Color.parseColor("#333333")
            leftTextSize = resources.getDimension(R.dimen.x24)
            leftPadding = 30
            leftDrawablePadding = 10
            //右边TextView
            rightTextColor = Color.parseColor("#666666")
            rightTextSize = resources.getDimension(R.dimen.x20)
            rightPadding = 30
            rightDrawablePadding = 10
            //底部分割线
            lineColor = Color.parseColor("#e5e5e5")
            lineHeight = 1
            lineMarginLeft = 30
//            lineMarginRight=0
            setOnItemClickListener { view, position ->
                Toast.makeText(this@MainActivity, position.toString(), Toast.LENGTH_LONG).show()
            }
        }.create()
        itemLayout.replaceRightText(2, "修改3")
        itemLayout.replaceLeftText(4, "修改5")
//        itemLayout.getView(5).visibility=View.GONE
//        itemLayout.getLineView(5).visibility=View.GONE
    }
}
