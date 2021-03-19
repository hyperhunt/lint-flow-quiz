package dev.hyperhunt.lintflowquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class QuizStartActivity : AppCompatActivity() {

    private lateinit var btn0: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    private lateinit var q: TextView
    private var quizValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_start_layout)

        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)

        q = findViewById(R.id.question_text_view)

        btn0.setOnClickListener(onClickListener)
        btn1.setOnClickListener(onClickListener)
        btn2.setOnClickListener(onClickListener)
        btn3.setOnClickListener(onClickListener)

    }


    @SuppressLint("ResourceAsColor")
    private val onClickListener = View.OnClickListener {
        val delayBlocked: Long = 500L
//        currentIndex++

        when (it as Button) {
            btn0 -> {

//                it.setTextColor(resources.getColor(R.color.btn_font_selected))

                quizValue = Integer.parseInt(it.text.toString())
                q.text = quizValue.toString()

                btnBlocked(it)

                Handler().postDelayed({
                    btnBlocked(it)
                    quizGameActivity(it as View)
                }, delayBlocked)
            }

            btn1 -> {

                quizValue = Integer.parseInt(it.text.toString())
                q.text = quizValue.toString()

                btnBlocked(it)

                Handler().postDelayed({
                    btnBlocked(it)
                    quizGameActivity(it as View)
                }, delayBlocked)
            }

            btn2 -> {

                quizValue = Integer.parseInt(it.text.toString())
                q.text = quizValue.toString()

                btnBlocked(it)

                Handler().postDelayed({
                    btnBlocked(it)
                    quizGameActivity(it as View)
                }, delayBlocked)
            }

            btn3 -> {

                quizValue = Integer.parseInt(it.text.toString())
                q.text = quizValue.toString()

                btnBlocked(it)

                Handler().postDelayed({
                    btnBlocked(it)
                    quizGameActivity(it as View)
                }, delayBlocked)
            }

        }
    }

    private fun btnBlocked(btn: Button) {
        btn.isSelected = !btn.isSelected

        btn0.isClickable = !btn0.isClickable
        btn1.isClickable = !btn1.isClickable
        btn2.isClickable = !btn2.isClickable
        btn3.isClickable = !btn3.isClickable

        q.setTextColor(resources.getColor(R.color.fontTextColor))
        btn.setTextColor(resources.getColor(R.color.fontTextColor))
        q.setText(resources.getText(R.string.app_name))
//        btn.setBackgroundColor(resources.getColor(R.color.btn_background))

    }

    private fun quizGameActivity(view: View) {
        val intent = Intent(this.application, QuizGameActivity::class.java)
        intent.putExtra("quizValue", quizValue.toString())

        startActivity(intent)
    }

    // Exit из Activity в Home при нажатии на кнопку Back
    override fun onBackPressed() {
//        super.onBackPressed()
        finish()
    }
}