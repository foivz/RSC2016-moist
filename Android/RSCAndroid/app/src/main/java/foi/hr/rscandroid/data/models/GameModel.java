package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GameModel {

    @SerializedName("answer")
    private ArrayList<Answer> answerArrayList;

    public ArrayList<Answer> getAnswerArrayList() {
        return answerArrayList;
    }

    public void setAnswerArrayList(ArrayList<Answer> answerArrayList) {
        this.answerArrayList = answerArrayList;
    }
}
