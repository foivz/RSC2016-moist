package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionData implements Serializable, Parcelable {

    public static final int SINGLE_ANSWER_QUESTION = 1;

    public static final int MULTIPLE_ANSWER_QUESTION = 2;

    public static final int EDIT_ANSWER_QUESTION = 3;

    @SerializedName("id")
    private int questionId;

    @SerializedName("content")
    private String question;

    @SerializedName("type_id")
    private int questionType;

    @SerializedName("url")
    private String url;

    @SerializedName("answers")
    private ArrayList<Answer> answers;


    protected QuestionData(Parcel in) {
        questionId = in.readInt();
        question = in.readString();
        questionType = in.readInt();
        url = in.readString();
        answers = in.createTypedArrayList(Answer.CREATOR);
    }

    public QuestionData() {
    }

    public static final Creator<QuestionData> CREATOR = new Creator<QuestionData>() {
        @Override
        public QuestionData createFromParcel(Parcel in) {
            return new QuestionData(in);
        }

        @Override
        public QuestionData[] newArray(int size) {
            return new QuestionData[size];
        }
    };

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

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(questionId);
        parcel.writeString(question);
        parcel.writeInt(questionType);
        parcel.writeString(url);
        parcel.writeTypedList(answers);
    }


    public void setASingleAnswer(Answer a) {
        if (answers != null) {
            answers.add(a);
        } else {
            answers = new ArrayList<>();
            answers.add(a);
        }
    }
}
