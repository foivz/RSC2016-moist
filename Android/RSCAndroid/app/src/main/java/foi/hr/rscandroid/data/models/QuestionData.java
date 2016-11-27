package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionData implements Serializable {

    public static final int SINGLE_ANSWER_QUESTION = 1;

    public static final int MULTIPLE_ANSWER_QUESTION = 2;

    public static final int EDIT_ANSWER_QUESTION = 3;

    @SerializedName("content")
    private String question;

    @SerializedName("type")
    private int questionType;

    @SerializedName("url")
    private String url;

    @SerializedName("answers")
    private ArrayList<Answer> answers;


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
