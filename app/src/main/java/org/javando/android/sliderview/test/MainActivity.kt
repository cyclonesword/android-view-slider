package org.javando.android.sliderview.test

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.javando.android.sliderview.CenteredItemLayoutManager
import org.javando.android.sliderview.LinearLayoutManager
import org.javando.android.sliderview.R
import org.javando.android.sliderview.SliderView

internal class MainActivity : AppCompatActivity() {

    @SuppressLint("ObjectAnimatorBinding")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_scrollview)


        val books = mutableListOf(
            R.drawable.book1,
            R.drawable.book2,
            R.drawable.book3,
            R.drawable.book1,
            R.drawable.book2,
            R.drawable.book3
        )

        val series = mutableListOf(
            R.drawable.series1,
            R.drawable.series2,
            R.drawable.series3,
            R.drawable.series1,
            R.drawable.series2,
            R.drawable.series3
        )

        val resStrings = mutableListOf("Ciao ciao", "xxxxxxxxxxxx", "yyyyyyyyyyyy", "eoeoeoeoe", "ooooooooo", "aaaaaaaaa")

        var res: MutableList<Int> = series

        val viewSlider = findViewById<SliderView>(R.id.slider)

        viewSlider.selectedItemPosition = 3

        val centeredItemLayoutManager = CenteredItemLayoutManager()
        viewSlider.layoutManager = centeredItemLayoutManager
        centeredItemLayoutManager.itemsOverflow = 50
        res = books
//        val linearLayoutManager = LinearLayoutManager()
//        linearLayoutManager.itemsLeftMargin = 70f
//        viewSlider.layoutManager = linearLayoutManager

        //viewSlider.layoutManager.minimumScrollPercentage = 0.20f

        viewSlider.adapter = object : SliderView.Adapter<Int> {
            override val count: Int
                get() = res.size

            override fun getItem(position: Int): Int {
                return res[position]
            }

            override fun getView(position: Int, parent: SliderView, convertView: View?): View {
                var view = convertView
                if (convertView == null) {
                    println("Convertview $position is null. creating a new view object")
//                        view = LayoutInflater.from(this@MainActivity).inflate(R.layout.layout_test,null)
//                        val b1 = view.findViewById<Button>(R.id.b1).apply { setOnClickListener { println("Clicked button 1111!") }; setOnLongClickListener { println("On long click listener! ON BUTTON 1"); true } }
//                        val b2 = view.findViewById<Button>(R.id.b2).apply { setOnClickListener { println("Clicked button 2222!") } }
//                        val img = view.findViewById<ImageView>(R.id.img1).apply { setOnClickListener { println("Clicked imageview 2222!") } }
//                        val switch = view.findViewById<Switch>(R.id.switch1).apply { setOnClickListener { println("Clicked switch 2222!") } }
//                        val bext = findViewById<Button>(R.id.bext).apply { setOnClickListener { println("Clicked extra button 333!") } }
                     view = ImageView(this@MainActivity).apply {
                                            if(position==3)
                                                layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                                            else
                                                layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                                            setImageResource(getItem(position))
                                            scaleType = ImageView.ScaleType.CENTER_CROP
                         setOnClickListener { println("image $position clicked!") }
                         setOnLongClickListener { println("Image $position lONG clocked"); true }
                    background = ColorDrawable(ResourcesCompat.getColor(this@MainActivity.resources, R.color.design_default_color_error, null))
                                        }
                } else
                    println("convertView $position is NOT null, reusing previous view object...")
                return view!!
            }

        }


        //viewSlider.layoutManager = FixedItemWidthLayoutManager()
        viewSlider.onItemSelectedListener = { view, item, index ->
            println("Selected $view at index $index with item $item")
        }
        viewSlider.onItemClickListener = { view, item, index ->
            println("Clidked $view at index $index with item $item")
        }
        //viewSlider.disableOnItemTouchAnimations()
        viewSlider.onStartDragAnimation = ObjectAnimator.ofFloat(null, "translationY", -100f).apply { duration = 150 }
        viewSlider.onEndDragAnimation = ObjectAnimator.ofFloat(null, "translationY", 0f).apply { duration = 150 }
        viewSlider.initialize()



        //                    view = LayoutInflater.from(this@MainActivity).inflate(R.layout.layout_test,null)
        //                    val b1 = view.findViewById<Button>(R.id.b1).apply { setOnClickListener { println("Clicked button 1111!") }; setOnLongClickListener { println("On long click listener! ON BUTTON 1"); true } }
        //                    val b2 = view.findViewById<Button>(R.id.b2).apply { setOnClickListener { println("Clicked button 2222!") } }
        //                    val img = view.findViewById<ImageView>(R.id.img1).apply { setOnClickListener { println("Clicked imageview 2222!") } }
        //                    val switch = view.findViewById<Switch>(R.id.switch1).apply { setOnClickListener { println("Clicked switch 2222!") } }
        //                    val bext = findViewById<Button>(R.id.bext).apply { setOnClickListener { println("Clicked extra button 333!") } }


        //                     view = ImageView(this@MainActivity).apply {
        //                                            if(position==3)
        //                                                layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        //                                            else
        //                                                layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        //                                            setImageResource(getItem(position))
        //                                            scaleType = ImageView.ScaleType.CENTER_CROP
        //                    background = ColorDrawable(ResourcesCompat.getColor(this@MainActivity.resources, R.color.design_default_color_error, null))
        //                                        }

        //                    view = LinearLayout(this@MainActivity).apply {
        //                        orientation = LinearLayout.VERTICAL
        //                        layoutParams = FrameLayout.LayoutParams(500, 700)
        ////                            .apply { gravity = Gravity.CENTER }
        //                        background = ColorDrawable(
        //                            ResourcesCompat.getColor(this@MainActivity.resources, R.color.design_default_color_error, null)
        //                        )
        //                        val tv1 = TextView(this@MainActivity).apply {
        //                            text = resStrings[position]+"1";
        //                            background = ColorDrawable(
        //                                ResourcesCompat.getColor(
        //                                    this@MainActivity.resources,
        //                                    R.color.design_default_color_primary,
        //                                    null
        //                                )
        //                            )
        //                            setOnClickListener {
        //                                println("$text Clicked 1111!!!!")
        //                            }
        //
        //                            setOnLongClickListener {
        //                                println("on long click1111!!")
        //                                true
        //                            }
        //
        ////                            setOnTouchListener { v, event ->
        ////                                println("Touch evento on $v: $event")
        ////                                false
        ////                            }
        //                        }
        //                        val tv2 = TextView(this@MainActivity).apply {
        //                            text = resStrings[position] + "2";
        //                            background = ColorDrawable(
        //                                ResourcesCompat.getColor(
        //                                    this@MainActivity.resources,
        //                                    R.color.design_default_color_secondary,
        //                                    null
        //                                )
        //                            )
        //                            setOnClickListener {
        //                                println("$text Clicked 22222!!!!")
        //                            }
        //
        //                            setOnLongClickListener {
        //                                println("on long cklick222!!")
        //                                true
        //                            }
        //                        }
        //                        addView(tv1)
        //                        addView(tv2)
        //                    }

//        GlobalScope.launch {
//            delay(1500)
//            for(i in 0 until res.size-3)
//                res.removeAt(i)
//            runOnUiThread { viewSlider.notifyDataSetChanged() }
//        }

        //        val scaleAnim = ValueAnimator.ofFloat(1f, 1.2f).apply {
//            duration = 100
//            startDelay = 0
//            repeatCount = 0
//            this.addUpdateListener {
//                val v = animatedValue as Float
//                img1.scaleX = v
//                img1.scaleY = v
//            }
//        }

//        val upX = PropertyValuesHolder.ofFloat(SCALE_X, 1f, 1.2f)
//        val upY = PropertyValuesHolder.ofFloat(SCALE_Y, 1f, 1.2f)
//
//        val downX = PropertyValuesHolder.ofFloat(SCALE_X, 1.2f, 1f)
//        val downY = PropertyValuesHolder.ofFloat(SCALE_Y, 1.2f, 1f)
//
//        val scaleUpAnim = ObjectAnimator.ofPropertyValuesHolder(img1, upX, upY)
//        val scaleDownAnim = ObjectAnimator.ofPropertyValuesHolder(img1, downX, downY)
//
//        val views = listOf(
//            ImageView(this).apply {
//                layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
//                setImageResource(R.drawable.book1)
//            },
//            ImageView(this).apply {
//                layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
//                setImageResource(R.drawable.book2)
//            },
//            ImageView(this).apply {
//                layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
//                setImageResource(R.drawable.book3)
//            }, ImageView(this).apply {
//                layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
//                setImageResource(R.drawable.book1)
//            },
//            ImageView(this).apply {
//                layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
//                setImageResource(R.drawable.book2)
//            },
//            ImageView(this).apply {
//                layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
//                setImageResource(R.drawable.book3)
//            })
//
  //      val resStrings = mutableListOf("Ciao ciao", "xxxxxxxxxxxx, "yyyyyyyyyyyy", "eoeoeoeoe", "ooooooooo", "aaaaaaaaa")


        //                    view = LinearLayout(this@TestActivity).apply {
//                        orientation = LinearLayout.VERTICAL
//                        layoutParams = FrameLayout.LayoutParams(300, WRAP_CONTENT).apply { gravity = Gravity.CENTER }
//                        background = ColorDrawable(ResourcesCompat.getColor(this@TestActivity.resources, R.color.design_default_color_error, null))
//                        val tv1 = TextView(this@TestActivity).apply {
//                            text = resStrings[position];
//                            background = ColorDrawable(ResourcesCompat.getColor(this@TestActivity.resources, R.color.design_default_color_primary, null))
//                            setOnClickListener {
//                                println("$text Clicked222!!!!")
//                            }
//                        }
//                        val tv2 = TextView(this@TestActivity).apply {
//                            text = resStrings[position];
//                            background = ColorDrawable(ResourcesCompat.getColor(this@TestActivity.resources, R.color.design_default_color_secondary, null))
//                            setOnClickListener {
//                                println("$text Clicked1111!!!!")
//                            }
//                        }
//                        addView(tv1)
//                        addView(tv2)
//                    }
    }
}