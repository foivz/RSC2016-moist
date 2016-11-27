package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {

    @SerializedName("id")
    private int answerId;

    @SerializedName("content")
    private String content;

    @SerializedName("answer")
    private int isCorrect;

    protected Answer(Parcel in) {
        answerId = in.readInt();
        content = in.readString();
        isCorrect = in.readInt();
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(answerId);
        parcel.writeString(content);
        parcel.writeInt(isCorrect);
    }
}
