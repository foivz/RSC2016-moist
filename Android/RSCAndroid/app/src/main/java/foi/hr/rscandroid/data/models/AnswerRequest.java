package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

public class AnswerRequest {

    @SerializedName("answer")
    private Answer answer;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
