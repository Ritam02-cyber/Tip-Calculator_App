package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    var tvExpression: TextView = findViewById(R.id.tvExpression)
    var tvResult: TextView = findViewById(R.id.tvResult)
    var tvOne: TextView = findViewById(R.id.tvOne)
    var tvTwo: TextView = findViewById(R.id.tvTwo)
    var tvThree: TextView = findViewById(R.id.tvThree)
    var tvFour: TextView = findViewById(R.id.tvFour)
    var tvFive: TextView = findViewById(R.id.tvFive)
    var tvSix: TextView = findViewById(R.id.tvSix)
    var tvSeven: TextView = findViewById(R.id.tvSeven)
    var tvEight: TextView = findViewById(R.id.tvEight)
    var tvNine: TextView = findViewById(R.id.tvNine)
    var tvZero: TextView = findViewById(R.id.tvZero)
    var tvPlus: TextView = findViewById(R.id.tvPlus)
    var tvMinus: TextView = findViewById(R.id.tvMinus)
    var tvMultiply: TextView = findViewById(R.id.tvMultiply)
    var tvDivide: TextView = findViewById(R.id.tvDivide)
    var tvDot: TextView = findViewById(R.id.tvDot)
    var tvOpen: TextView = findViewById(R.id.tvOpen)
    var tvClose: TextView = findViewById(R.id.tvClose)
    var tvClear: TextView = findViewById(R.id.tvClear)
    var tvBack: ImageView = findViewById(R.id.tvBack)
    var tvEquals: TextView = findViewById(R.id.tvEquals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOne.setOnClickListener { appendOnExpression("1", true) }
        tvTwo.setOnClickListener { appendOnExpression("2", true) }
        tvThree.setOnClickListener { appendOnExpression("3", true) }
        tvFour.setOnClickListener { appendOnExpression("4", true) }
        tvFive.setOnClickListener { appendOnExpression("5", true) }
        tvSix.setOnClickListener { appendOnExpression("6", true) }
        tvSeven.setOnClickListener { appendOnExpression("7", true) }
        tvEight.setOnClickListener { appendOnExpression("8", true) }
        tvNine.setOnClickListener { appendOnExpression("9", true) }
        tvZero.setOnClickListener { appendOnExpression("0", true) }
        tvDot.setOnClickListener { appendOnExpression(".", true) }

        tvPlus.setOnClickListener { appendOnExpression("+", true) }
        tvMinus.setOnClickListener { appendOnExpression("-", true) }
        tvMultiply.setOnClickListener { appendOnExpression("*", true) }
        tvDivide.setOnClickListener { appendOnExpression("/", true) }
        tvOpen.setOnClickListener { appendOnExpression("(", true) }
        tvClose.setOnClickListener { appendOnExpression(")", true) }

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if (string.isNotEmpty()) {
                tvExpression.text = string.substring(0, string.length-1)
            }
            tvResult.text = ""
        }

        tvEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    tvResult.text = longResult.toString()
                } else {
                    tvResult.text = result.toString()
                }
            } catch (e:Exception) {
                Log.d("Exception", " message : " + e.message)
            }
        }
    }

    fun appendOnExpression(string: String, canClear: Boolean) {
        if (tvResult.text.isNotEmpty()) {
            tvExpression.text = ""
        }
        if (canClear) {
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}