@file:Suppress("DEPRECATION")

package dev.hyperhunt.lintflowquiz

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class QuizResultActivity : AppCompatActivity() {

    private lateinit var btn0: Button
    private lateinit var btn1: Button

    private lateinit var questionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_result_layout)

        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)

        questionTextView = findViewById(R.id.question_text_view)

        btn0.setText(R.string.text_start_again)
        btn1.setText(R.string.text_app_exit)

        val countCorrectAnswers = intent.getStringExtra("countCorrectAnswers")!!
        val countWrongAnswers = intent.getStringExtra("countWrongAnswers")!!

        questionTextView.text = "Верно: $countCorrectAnswers\nНеверно: $countWrongAnswers"

//        questionTextView.setTextColor(resources.getColor(R.color.btn_check_bad_answer))
//        btn.setTextColor(resources.getColor(R.color.font_check_bad_answer))
//        btn.setBackgroundColor(resources.getColor(R.color.btn_check_bad_answer))

        btn0.setOnClickListener(onClickListener)
        btn1.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        val delayBlocked: Long = 500L
//        currentIndex++

        when (it as Button) {
            btn0 -> {
                btnBlocked(it)

                Handler().postDelayed({
                    btnBlocked(it)
                    questionTextView.setText(resources.getText(R.string.app_name))
                    quizStartActity(it as View)
                }, delayBlocked)
            }

            btn1 -> {
                btnBlocked(it)

                Handler().postDelayed({
                    btnBlocked(it)
                    questionTextView.setText(resources.getText(R.string.app_name))
                    moveTaskToBack(true)
                    exitProcess(-1)
                }, delayBlocked)
            }
        }
    }

     fun quizStartActity(view: View) {
        val intent = Intent(this, QuizStartActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun btnBlocked(btn: Button) {
        btn.isSelected = !btn.isSelected

        btn0.isClickable = !btn0.isClickable
        btn1.isClickable = !btn1.isClickable

        questionTextView.setTextColor(resources.getColor(R.color.fontTextColor))
        btn.setTextColor(resources.getColor(R.color.fontTextColor))
//        btn.setBackgroundColor(resources.getColor(R.color.btn_background))

    }

    // If press button Back when transfer to quizStartActivity
    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        finish()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        finish()
    }
}