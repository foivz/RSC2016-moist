package foi.hr.rscandroid.data.models;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("question")
    private QuestionData questionData;

    public QuestionData getQuestionData() {
        return questionData;
    }

    public void setQuestionData(QuestionData questionData) {
        this.questionData = questionData;
    }
}
