package foi.hr.rscandroid.ui.game.singleanswer;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.QuestionData;
import foi.hr.rscandroid.ui.BaseFragment;
import foi.hr.rscandroid.ui.shared.FragmentCommandListener;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;

public class SingleAnswerFragment extends BaseFragment implements SingleAnswerView {

    public static final String EXTRA_QUESTION = "EXTRA_QUESTION";

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @BindView(R.id.tv_question)
    TextView tvQuestion;

    private FragmentCommandListener fragmentCommandListener;

    private SingleAnswerPresenter presenter;

    public static SingleAnswerFragment newInstance(QuestionData q) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_QUESTION, q);
        SingleAnswerFragment fragment = new SingleAnswerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentCommandListener = (FragmentCommandListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    private QuestionData question;

    public SingleAnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_answer, container, false);
        ButterKnife.bind(this, view);
        presenter = MvpFactoryUtil.getPresenter(this);
        question = (QuestionData) getArguments().get(EXTRA_QUESTION);

        if (question != null) {
            tvQuestion.setText(question.getQuestion());
        }

        initRadio();
        presenter.analyzeQuestion(question);
        return view;
    }

    private void initRadio() {
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < question.getAnswers().size(); i++) {
            RadioButton radioButton = new RadioButton(getBaseActivity());
            radioButton.setId(i);
            radioButton.setText(question.getAnswers().get(i).getContent());
            radioGroup.addView(radioButton);
        }

        fragmentCommandListener.startTimer();
    }

}
