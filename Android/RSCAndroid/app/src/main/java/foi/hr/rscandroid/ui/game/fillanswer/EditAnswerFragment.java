package foi.hr.rscandroid.ui.game.fillanswer;


import com.rengwuxian.materialedittext.MaterialEditText;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.QuestionData;
import foi.hr.rscandroid.ui.shared.FragmentCommandListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditAnswerFragment extends Fragment {

    public static final String EXTRA_QUESTION = "EXTRA_QUESTION";

    @BindView(R.id.tv_question)
    TextView tvQuestion;

    @BindView(R.id.et_answer)
    MaterialEditText etAnswer;

    private FragmentCommandListener fragmentCommandListener;

    private QuestionData question;

    public static EditAnswerFragment newInstance(QuestionData q) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_QUESTION, q);
        EditAnswerFragment fragment = new EditAnswerFragment();
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

    public EditAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_answer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        question = (QuestionData) getArguments().get(EXTRA_QUESTION);

        if (question != null) {
            tvQuestion.setText(question.getQuestion());
        }

    }
}
