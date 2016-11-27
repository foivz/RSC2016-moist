package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StaticModel {

    public static final int SINGLE_ANSWER_QUESTION = 1;

    public static final int MULTIPLE_ANSWER_QUESTION = 2;

    public static final int EDIT_ANSWER_QUESTION = 3;

    @SerializedName("id")
    private static int questionId;

    @SerializedName("content")
    private static String question;

    @SerializedName("type_id")
    private static int questionType;

    @SerializedName("url")
    private static String url;

    @SerializedName("answers")
    private static ArrayList<Answer> answers;

    public static String getQuestion() {
        return question;
    }

    public static void setQuestion(String question) {
        StaticModel.question = question;
    }

    public static int getQuestionType() {
        return questionType;
    }

    public static void setQuestionType(int questionType) {
        StaticModel.questionType = questionType;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        StaticModel.url = url;
    }

    public static ArrayList<Answer> getAnswers() {
        return answers;
    }

    public static void setAnswers(ArrayList<Answer> answers) {
        StaticModel.answers = answers;
    }

    public static int getQuestionId() {
        return questionId;
    }

    public static void setQuestionId(int questionId) {
        StaticModel.questionId = questionId;
    }

    public static void setASingleAnswer(Answer a) {
        if (answers != null) {
            answers.add(a);
        }
        else {
            answers = new ArrayList<>();
            answers.add(a);
        }
    }
}
