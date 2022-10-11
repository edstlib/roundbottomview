package id.co.edtslib.roundbottomview.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import id.co.edtslib.roundbottomview.RoundBottomView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        findViewById<View>(R.id.textView).setOnClickListener {
            RoundBottomView.canClose = false
            RoundBottomView.show(this, "Title", R.layout.view_dialog_content) {
                it?.findViewById<View>(R.id.textView)?.setOnClickListener {
                    Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}