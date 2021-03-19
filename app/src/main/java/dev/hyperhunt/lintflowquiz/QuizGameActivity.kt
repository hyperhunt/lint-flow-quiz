package dev.hyperhunt.lintflowquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//  Your Result  22 %  5 of 22 answers correct

@Suppress("DEPRECATION")
class QuizGameActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var counterQuestion: TextView

    private lateinit var btn0: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    private var currentIndex = 0
    private var countWrongAnswers = 0
    private var countCorrectAnswers = 0

//    private var quizValue: Int = intent?.getIntExtra("quizValue", 5)!!
    private lateinit var quizValue: String

    private var savedMessage = ""
    private var keyLint = "SavedMessage"
    private var tagLint = "tagLint"

    private lateinit var question: QuizDataClass
    private lateinit var takeSetWords: List<QuizDataClass>

    val setOfWords = listOf(
        QuizDataClass("Awesome", "Потрясающий"),
        QuizDataClass("Word", "Слово"),
        QuizDataClass("Run", "Запустить"),
        QuizDataClass("Search", "Поиск"),
        QuizDataClass("Beanbag", "Погремушка"),
        QuizDataClass("Sleep", "Сон"),
        QuizDataClass("Winter", "Зима"),
        QuizDataClass("Time", "Время"),
        QuizDataClass("The sun", "Солнце"),
        QuizDataClass("Walk", "Гулять"),
        QuizDataClass("Nice", "Красивый"),
        QuizDataClass("Window", "Окно"),
        QuizDataClass("Walking", "Ходьба"),
        QuizDataClass("Bike", "Велосипед"),
        QuizDataClass("Programming", "Программирование"),
        QuizDataClass("Insect", "Насекомое"),
        QuizDataClass("Water", "Вода"),
        QuizDataClass("Sentence", "Предложение"),
        QuizDataClass("Paint", "Краска"),
        QuizDataClass("Learn", "Учиться"),
        QuizDataClass("Garden", "Сад"),
        QuizDataClass("Octopus", "Осминог"),
        QuizDataClass("Rust", "Ржавчина"),
        QuizDataClass("Sky", "Небо"),
        QuizDataClass("Crab", "Краб"),
        QuizDataClass("Singapore", "Сингапур"),
        QuizDataClass("Car", "Машина"),
    )

    val usedQuestions = mutableListOf<QuizDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_game_layout)

        questionTextView = findViewById(R.id.question_text_view)
        counterQuestion = findViewById(R.id.counterQuestion)

        quizValue = intent.getStringExtra("quizValue")!!

//            Log.d(tagLint, "onCreate: [${btn4.text}]")

        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)

        btn0.setOnClickListener(onClickListener)
        btn1.setOnClickListener(onClickListener)
        btn2.setOnClickListener(onClickListener)
        btn3.setOnClickListener(onClickListener)


//        questionTextView.text = question.word
//
//        btn0.text = takeSetWords[0].translation
//        btn1.text = takeSetWords[1].translation
//        btn2.text = takeSetWords[2].translation
//        btn3.text = takeSetWords[3].translation

        takeNewQuestion()
    }


    @SuppressLint("ResourceAsColor")
    private val onClickListener = View.OnClickListener() {
        val delayBlocked: Long = 700L
        currentIndex++
        when (it) {
            btn0 -> {

                (it as Button).isSelected = !it.isSelected

                btnBlocked(it)
                checkAnswer(it, question)

                Handler().postDelayed({
                    btnBlocked(it)
                    takeNewQuestion()
                }, delayBlocked)
            }

            btn1 -> {

                (it as Button).isSelected = !it.isSelected

                btnBlocked(it)
                checkAnswer(it, question)

                Handler().postDelayed({
                    btnBlocked(it)
                    takeNewQuestion()
                }, delayBlocked)
            }

            btn2 -> {

                (it as Button).isSelected = !it.isSelected

                btnBlocked(it)
                checkAnswer(it, question)

                Handler().postDelayed({
                    btnBlocked(it)
                    takeNewQuestion()
                }, delayBlocked)
            }

            btn3 -> {

                (it as Button).isSelected = !it.isSelected

                btnBlocked(it)
                checkAnswer(it, question)

                Handler().postDelayed({
                    btnBlocked(it)
                    takeNewQuestion()
                }, delayBlocked)
            }
        }
    }

    private fun takeNewQuestion(
    ) {

        question = newQuestion(setOfWords, usedQuestions)

        if (question.word != "@") {

            counterQuestion.text = "${currentIndex + 1}/${Integer.parseInt(quizValue)}"

            takeSetWords = takeSetWords(question, setOfWords)

            questionTextView.text = question.word

            btn0.text = takeSetWords[0].translation
            btn1.text = takeSetWords[1].translation
            btn2.text = takeSetWords[2].translation
            btn3.text = takeSetWords[3].translation
        } else {

            counterQuestion.text = "" // End questions

//            questionTextView.text = "Верно: $countCorrectAnswers\nНе верно: $countWrongAnswers"

            btn0.text = ""
            btn1.text = ""
            btn2.text = ""
            btn3.text = ""

            quizResultActivity(this)
        }
    }

    private fun quizResultActivity(quizGameActivity: QuizGameActivity) {
        val intent = Intent(quizGameActivity, QuizResultActivity::class.java)
        intent.putExtra("countCorrectAnswers", countCorrectAnswers.toString())
        intent.putExtra("countWrongAnswers", countWrongAnswers.toString())

        startActivity(intent)
        finish()
    }

    private fun checkAnswer(btn: Button, question: QuizDataClass) {
        if (btn.text == question.translation) {
            questionTextView.setTextColor(resources.getColor(R.color.btn_check_good_answer))
            btn.setTextColor(resources.getColor(R.color.font_check_good_answer))
            btn.setBackgroundColor(resources.getColor(R.color.btn_check_good_answer))
            countCorrectAnswers++
        } else {
            questionTextView.setTextColor(resources.getColor(R.color.btn_check_bad_answer))
            btn.setTextColor(resources.getColor(R.color.font_check_bad_answer))
            btn.setBackgroundColor(resources.getColor(R.color.btn_check_bad_answer))
            countWrongAnswers++
        }
    }

    private fun btnBlocked(btn: Button) {
        btn0.isClickable = !btn0.isClickable
        btn1.isClickable = !btn1.isClickable
        btn2.isClickable = !btn2.isClickable
        btn3.isClickable = !btn3.isClickable
        questionTextView.setTextColor(resources.getColor(R.color.fontTextColor))
        btn.setTextColor(resources.getColor(R.color.fontTextColor))
        btn.setBackgroundColor(resources.getColor(R.color.btn_background))

    }

//    @SuppressLint("SetTextI18n")
//    private fun updateQuestion() {
//        counterQuestion.text = "${currentIndex + 1}/${questionBank.size}"
//        val questionTextResId = questionBank[currentIndex].textResId
//        questionTextView.setText(questionTextResId)
//    }


    fun takeSetWords(
        question: QuizDataClass,
        setOfWords: List<QuizDataClass>
    ): List<QuizDataClass> {
        val setIncorrectWords = mutableListOf<QuizDataClass>()

        setIncorrectWords.add(question)

        var takeWord: QuizDataClass = setOfWords.random()
        while (setIncorrectWords.size < 4) {
//        println("takeWord: $takeWord")
//        println("question: $question")
            if (takeWord !in setIncorrectWords) {
                setIncorrectWords.add(takeWord)

            } else {
                takeWord = setOfWords.random()
            }
        }
        return setIncorrectWords.shuffled()
    }

    fun newQuestion(
        setOfWords: List<QuizDataClass>,
        usedQuestions: MutableList<QuizDataClass>
    ): QuizDataClass {
        var question: QuizDataClass = setOfWords.random()


        if (usedQuestions.size < Integer.parseInt(quizValue)) {
            while (question in usedQuestions) {
                question = setOfWords.random()
            }
            usedQuestions.add(question)
        } else {
            question = QuizDataClass("@", "@")
        }

        return question
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
//        quizResultActivity(View(this))
        finish()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        finish()
    }
}