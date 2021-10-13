package id.co.edtslib.roundbottomview

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.widget.TextViewCompat

class RoundBottomView(context: Context, theme: Int): Dialog(context, theme) {
    private var contentLayoutResId = 0
    private var tvTitle: TextView? = null
    private var title: String? = null
    private lateinit var process: (contentLayout: View?) -> Unit

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var dialog: RoundBottomView? = null
        var canClose = false
        var closeListener: () -> Unit = {  }

        fun show(context: Context, title: String, contentLayout: Int, process: (contentLayout: View?) -> Unit) {
            if (dialog == null) {
                dialog = RoundBottomView(context, R.style.Animation_Design_BottomSheetDialog)
                dialog?.setCancelable(true)
                dialog?.setCanceledOnTouchOutside(true)
                dialog?.show(title, contentLayout, process)
            }
        }

        fun close() {
            dialog?.dismiss()
            dialog = null
        }

        fun setTitleStyle(style: Int) {
            if (dialog?.tvTitle != null) {
                Handler(Looper.myLooper()!!).post {
                    TextViewCompat.setTextAppearance(dialog?.tvTitle!!, style)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.view_round_bottom)
        window?.decorView?.setBackgroundResource(android.R.color.transparent)



        setOnCancelListener {
            dialog = null
        }

        setOnDismissListener {
            closeListener()
            dialog = null
        }

        tvTitle = findViewById(R.id.tvTitle)
        tvTitle?.text = title

        val ivClose = findViewById<View>(R.id.ivClose)
        ivClose.setOnClickListener {
            close()
        }
        ivClose.visibility = if (canClose) View.VISIBLE else View.INVISIBLE

        if (contentLayoutResId != 0) {
            val clContent = findViewById<View>(R.id.clContent)
            clContent.visibility = View.INVISIBLE

            val inflater = LayoutInflater.from(context)
            val contentLayout = inflater.inflate(contentLayoutResId, null)

            val flContent = findViewById<FrameLayout>(R.id.flContent)
            flContent.addView(contentLayout)

            val clDialog = findViewById<View>(R.id.clDialog)
            clDialog.setOnClickListener {
                dismiss()
            }

            flContent.post {
                clContent.translationY = clContent.height.toFloat()

                clContent.visibility = View.VISIBLE
                clContent.animate()?.translationY(0f)

            }

            process(contentLayout)
        }

    }

    fun show(title: String?, contentLayout: Int, process: (contentLayout: View?) -> Unit) {
        this.title = title
        this.contentLayoutResId = contentLayout
        this.process = process

        super.show()
    }
}