package czh.lib

import android.content.Context
import android.graphics.Color
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.item.view.*


class ItemLayout : LinearLayout {
    private var mContext: Context
    private var onItemClickListener: OnItemClickListener? = null
    private var defaultIcon: Int = R.mipmap.ico_next
    private val mViewList = arrayListOf<View>()
    private val mLineViewList = arrayListOf<View>()
    private var items: List<ItemBean>? = null
    private var itemHeight = 48

    private var leftTextSize = 28f
    private var leftTextColor = 0
    private var leftPadding = 0
    private var leftDrawablePadding = 0

    private var rightTextSize = 28f
    private var rightTextColor = 0
    private var rightPadding = 0
    private var rightDrawablePadding = 0

    var lineHeight = 1
    private var lineColor = 0
    private var lineMarginLeft = 0
    private var lineMarginRight = 0

    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mContext = context
        orientation = VERTICAL
        context.theme.obtainStyledAttributes(attrs, R.styleable.ItemLayout, defStyleAttr, 0).let {
            (0 until it.indexCount).forEach { index ->
                val type = it.getIndex(index)
                when (type) {
                    R.styleable.ItemLayout_itemHeight -> itemHeight = it.getDimensionPixelOffset(type, 48)
                    R.styleable.ItemLayout_leftTextSize -> leftTextSize = it.getDimension(type, 28f)
                    R.styleable.ItemLayout_leftTextColor -> leftTextColor = it.getColor(type, Color.parseColor("#333333"))
                    R.styleable.ItemLayout_leftPadding -> leftPadding = it.getDimensionPixelOffset(type, 0)
                    R.styleable.ItemLayout_leftDrawablePadding -> leftDrawablePadding = it.getDimensionPixelOffset(type, 0)

                    R.styleable.ItemLayout_rightTextSize -> rightTextSize = it.getDimension(type, 28f)
                    R.styleable.ItemLayout_rightTextColor -> rightTextColor = it.getColor(type, Color.parseColor("#333333"))
                    R.styleable.ItemLayout_rightPadding -> rightPadding = it.getDimensionPixelOffset(type, 0)
                    R.styleable.ItemLayout_rightDrawablePadding -> rightDrawablePadding = it.getDimensionPixelOffset(type, 0)

                    R.styleable.ItemLayout_lineHeight -> lineHeight = it.getDimensionPixelOffset(type, 1)
                    R.styleable.ItemLayout_lineColor -> lineColor = it.getColor(type, Color.parseColor("#e5e5e5"))
                    R.styleable.ItemLayout_lineMarginLeft -> lineMarginLeft = it.getDimensionPixelOffset(type, 0)
                    R.styleable.ItemLayout_lineMarginRight -> lineMarginRight = it.getDimensionPixelOffset(type, 0)
                }
            }
            it.recycle()
        }
    }

    fun setData(items: List<ItemBean>): ItemLayout {
        this.items = items
        return this
    }

    fun setDefaultIcon(@DrawableRes id: Int): ItemLayout {
        this.defaultIcon = id
        return this
    }

    fun getView(position: Int): View {
        return mViewList[position]
    }

    fun getLineView(position: Int): View {
        return mLineViewList[position]
    }

    fun getLeftTextView(position: Int): TextView {
        return mViewList[position].leftTextView
    }

    fun getRightTextView(position: Int): TextView {
        return mViewList[position].rightTextView
    }


    fun replaceRightText(position: Int, text: String) {
        getRightTextView(position).text = text
    }

    fun replaceLeftText(position: Int, text: String) {
        getLeftTextView(position).text = text
    }

    fun create() {
        items?.forEachIndexed { position, it ->
            val view = LayoutInflater.from(mContext).inflate(R.layout.item, null).apply {
                itemLayout.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight)
                leftTextView.apply {
                    textSize = leftTextSize
                    setTextColor(leftTextColor)
                    setPadding(leftPadding, 0, 0, 0)
                    it.leftText?.let { text = it }
                    it.leftIcon?.let {
                        compoundDrawablePadding = leftDrawablePadding
                        setCompoundDrawables(ContextCompat.getDrawable(mContext, it)?.apply {
                            setBounds(0, 0, minimumWidth, minimumHeight)
                        }, null, null, null)
                    }
                }

                rightTextView.apply {
                    textSize = rightTextSize
                    it.rightText?.let { text = it }
                    setPadding(0, 0, rightPadding, 0)
                    setTextColor(rightTextColor)
                    setCompoundDrawables(null, null, ContextCompat.getDrawable(mContext, defaultIcon)?.apply {
                        compoundDrawablePadding = rightDrawablePadding
                        setBounds(0, 0, minimumWidth, minimumHeight)
                    }, null)
                }
            }
            addView(view)
            mViewList.add(view)
            it.height?.let {
                createLineView(it).let {
                    addView(it)
                    mLineViewList.add(it)
                }

            } ?: createLineView().let {
                addView(it)
                mLineViewList.add(it)
            }

            view.setOnClickListener {
                onItemClickListener?.onItemClick(view, position)
            }
        }
    }

    /**
     * 创建线
     * @param margin
     * @return
     */
    private fun createLineView(height: Int? = null): View {
        return View(mContext).apply {
            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height
                    ?: lineHeight)
            if (height == null) {
                lp.leftMargin = lineMarginLeft
                lp.rightMargin = lineMarginRight
            }
            layoutParams = lp
            setBackgroundColor(lineColor)
        }
    }

    /**
     * convert dp to its equivalent px
     *
     * 将dp转换为与之相等的px
     */
    fun dp2px(dipValue: Float): Int {
        val scale = mContext.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }


    /**
     * item点击事件监听
     */
    internal interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


    fun setOnItemClickListener(action: (view: View, position: Int) -> Unit): ItemLayout {
        onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                action(view, position)
            }
        }
        return this
    }
}