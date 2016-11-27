package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("id")
    private int answerId;

    @SerializedName("content")
    private String content;

    @SerializedName("isCorrect")
    private boolean isCorrect;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
