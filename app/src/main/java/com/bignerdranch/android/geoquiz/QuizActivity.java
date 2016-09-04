package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    /**
     * 初始化问题下标为0
     */
    private int mCurrentIndex = 0;

    /**
     * 使用upateQuestion()封装公共代码
     */
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    /**
     * 接收boolean类型的变量参数，判别用户单击了TRUE还是FALSE按钮，
     * 然后将用户的答案同当前Question对象中的答案作比较。
     * 最后判断答案正确与否，生成一个Toast消息反馈给用户
     */
    private void checkAnsewer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        /**
         * * 引用TextView，并将其文本内容设置为当前数组索引所指向的地理知识问题。
         */
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                updateQuestion();

            }
        });


        mTrueButton = (Button) findViewById(R.id.true_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnsewer(true);

            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnsewer(false);
            }
        });
        /**
         * 返回键监听事件
         */
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                                               if (mCurrentIndex < 0) {
                                                   mCurrentIndex = 4;
                                               }
                                               updateQuestion();
                                           }
                                       }
        );


        /**
         * 处理NEXT按钮，首先引用NEXT按钮，然后为其设置监听器View.OnClickListener.
         * 该监听器的作用是：递增数组索引并相应更新显示TextView的文本内容
         */
        mNextButton = (ImageButton)

                findViewById(R.id.next_button);

        mNextButton.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick(View view) {
                                               mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                                               updateQuestion();
                                           }
                                       }

        );

        updateQuestion();
    }
}
