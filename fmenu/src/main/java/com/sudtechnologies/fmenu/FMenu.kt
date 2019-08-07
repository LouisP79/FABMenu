package com.sudtechnologies.fmenu

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.graphics.PorterDuff
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import kotlin.math.hypot

class FMenu: FrameLayout {

    val listener: OnFMenuClick? = null

    private lateinit var overlay: View
    private lateinit var fab: FloatingActionButton
    private lateinit var card: CardView
    private lateinit var btn1: Button
    private lateinit var btn2: Button

    var fSrc: Int = R.drawable.ic_add_black_24dp
        set(value) {
            field = value
            fab.setImageDrawable(ContextCompat.getDrawable(context, field))
        }

    var fSrcTint: Int = android.R.color.white
        set(value) {
            field = value
            fab.setColorFilter(ContextCompat.getColor(context, field))
        }

    var fRadius: Float = context.resources.getDimension(R.dimen.card_radius)
        set(value) {
            field = value
            card.radius = field
        }

    var fBt1Text: String = context.getString(R.string.app_name)
        set(value) {
            field = value
            btn1.text = field
        }

    var fBt2Text: String = context.getString(R.string.app_name)
        set(value) {
            field = value
            btn2.text = field
        }

    var fBt1TextColor: Int = R.color.colorAccent
        set(value) {
            field = value
            btn1.setTextColor(ContextCompat.getColor(context, field))
        }

    var fBt2TextColor: Int = android.R.color.white
        set(value) {
            field = value
            btn2.setTextColor(ContextCompat.getColor(context, field))
        }

    var fBt1Background: Int = android.R.color.white
        set(value) {
            field = value
            btn1.setBackgroundResource(field)
        }

    var fBt2Background: Int = R.color.colorAccent
        set(value) {
            field = value
            btn2.setBackgroundResource(field)
        }

    var fBt1Drawable: Int = R.drawable.ic_edit_black_24dp
        set(value) {
            field = value
            btn1.setCompoundDrawables(ContextCompat.getDrawable(context,field),null,null,null)
        }

    var fBt2Drawable: Int = R.drawable.ic_edit_black_24dp
        set(value) {
            field = value
            btn2.setCompoundDrawables(ContextCompat.getDrawable(context,field),null,null,null)
        }

    var fBt1DrawableTint: Int = R.color.colorAccent
        set(value) {
            field = value
            btn1.compoundDrawables.forEach {
                it?.setColorFilter(ContextCompat.getColor(context, field), PorterDuff.Mode.SRC_ATOP)
            }
        }

    var fBt2DrawableTint: Int = android.R.color.white
        set(value) {
            field = value
            btn2.compoundDrawables.forEach {
                it?.setColorFilter(ContextCompat.getColor(context, field), PorterDuff.Mode.SRC_ATOP)
            }
        }

    var fBt1DrawablePadding: Float = context.resources.getDimension(R.dimen.fab_padding)
        set(value) {
            field = value
            btn1.compoundDrawablePadding = field.toInt()
        }

    var fBt2DrawablePadding: Float = context.resources.getDimension(R.dimen.fab_padding)
        set(value) {
            field = value
            btn2.compoundDrawablePadding = field.toInt()
        }

    var fShowMwnu: Boolean = false
        set(value) {
            field = value
            if(field) card.visibility = View.VISIBLE else card.visibility = View.INVISIBLE
        }

    constructor(context: Context): super(context) {setup()}
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {setup(attrs)}
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle) {setup(attrs,defStyle)}

    private fun setup(attrs: AttributeSet? = null, defStyle: Int = -1){
        // Creamos la interfaz a partir del layout
        val view = View.inflate(context, R.layout.fmenu, this)

        overlay = view.findViewById(R.id.overlay)
        fab = view.findViewById(R.id.fab)
        card = view.findViewById(R.id.card)
        btn1 = view.findViewById(R.id.btn1)
        btn2 = view.findViewById(R.id.btn2)

        fab.setOnClickListener {showFMenu()}
        overlay.setOnClickListener {hideFMenu()}
        btn1.setOnClickListener { listener?.onFBT1Click() }
        btn2.setOnClickListener { listener?.onFBT2Click() }

        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int){
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.FMenu, defStyle, 0)

        fab.setImageDrawable(ContextCompat.getDrawable(context, attributes.getResourceId(R.styleable.FMenu_fSrc, fSrc)))
        fab.setColorFilter(ContextCompat.getColor(context, attributes.getResourceId(R.styleable.FMenu_fSrcTint, fSrcTint)))
        card.radius = attributes.getDimension(R.styleable.FMenu_fRadius, fRadius)
        btn1.text = attributes.getString(R.styleable.FMenu_fBt1Text)
        btn2.text = attributes.getString(R.styleable.FMenu_fBt2Text)
        btn1.setTextColor(ContextCompat.getColor(context, attributes.getResourceId(R.styleable.FMenu_fBt1TextColor, fBt1TextColor)))
        btn2.setTextColor(ContextCompat.getColor(context, attributes.getResourceId(R.styleable.FMenu_fBt2TextColor, fBt2TextColor)))
        btn1.setBackgroundResource(attributes.getResourceId(R.styleable.FMenu_fBt1Background, fBt1Background))
        btn2.setBackgroundResource(attributes.getResourceId(R.styleable.FMenu_fBt2Background, fBt2Background))
        btn1.setCompoundDrawables(ContextCompat.getDrawable(context,attributes.getResourceId(R.styleable.FMenu_fBt1Drawable, fBt1Drawable)),null,null,null)
        btn2.setCompoundDrawables(ContextCompat.getDrawable(context,attributes.getResourceId(R.styleable.FMenu_fBt2Drawable, fBt2Drawable)),null,null,null)
        btn1.compoundDrawablePadding = attributes.getDimension(R.styleable.FMenu_fBt1DrawablePadding, fBt1DrawablePadding).toInt()
        btn2.compoundDrawablePadding = attributes.getDimension(R.styleable.FMenu_fBt2DrawablePadding, fBt2DrawablePadding).toInt()

        btn1.compoundDrawables.forEach {
            it?.setColorFilter(ContextCompat.getColor(context, attributes.getResourceId(R.styleable.FMenu_fBt1DrawableTint, fBt1DrawableTint)), PorterDuff.Mode.SRC_ATOP)
        }

        btn2.compoundDrawables.forEach {
            it?.setColorFilter(ContextCompat.getColor(context, attributes.getResourceId(R.styleable.FMenu_fBt2DrawableTint, fBt2DrawableTint)), PorterDuff.Mode.SRC_ATOP)
        }

        if(attributes.getBoolean(R.styleable.FMenu_fShowMenu, fShowMwnu)) card.visibility = View.VISIBLE else card.visibility = View.INVISIBLE

        //ocultando attributos
        overlay.visibility = View.GONE

        attributes.recycle()
    }

    fun isFMenuOpen(): Boolean = fab.visibility != View.VISIBLE

    fun showFMenu(){
        card.visibility = View.VISIBLE
        overlay.visibility = View.VISIBLE
        fab.hide()

        val animatorCard = ViewAnimationUtils.createCircularReveal(
            card,
            card.width - (fab.width / 2),
            card.height - (fab.height / 2),
            0F,
            hypot(card.width.toDouble(), card.height.toDouble()).toFloat()
        )

        animatorCard.interpolator = AccelerateDecelerateInterpolator()
        animatorCard.duration = 400
        animatorCard.start()
    }

    fun hideFMenu(){
        overlay.visibility = View.GONE
        card.visibility = View.INVISIBLE
        fab.show()
    }

    interface OnFMenuClick {
        fun onFBT1Click()
        fun onFBT2Click()
    }

}