package id.co.edtslib.roundbottomview.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import id.co.edtslib.roundbottomview.RoundBottomView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        findViewById<View>(R.id.textView).setOnClickListener {
            RoundBottomView.canClose = false
            RoundBottomView.show(this, "Title", R.layout.view_dialog_content, R.layout.view_header,
                ::processHeaderContent)
        }
    }

    private fun processHeaderContent(content: View?, header: View?): Unit {

    }
}