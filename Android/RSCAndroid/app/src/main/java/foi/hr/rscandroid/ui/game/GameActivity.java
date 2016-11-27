package foi.hr.rscandroid.ui.game;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.QuestionData;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.shared.FragmentCommandListener;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;

public class GameActivity extends BaseActivity implements GameView, FragmentCommandListener {

    public static final String EXTRA_QUESTIONS = "EXTRA_QUESTIONS";

    public static final String EXTRA_QUESTION = "EXTRA_QUESTION";

    private QuestionData question;

    @BindView(R.id.tv_timer)
    TextView tvTimer;

    private ArrayList<QuestionData> questions;

    private GamePresenter presenter;

    private ArrayList<Fragment> fragments;

    private int currPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        presenter = MvpFactoryUtil.getPresenter(this);
        questions = getIntent().getParcelableExtra(EXTRA_QUESTIONS);
        question = (QuestionData) getIntent().getSerializableExtra(EXTRA_QUESTION);


        if (question != null) {
            presenter.addNewFragment(question);
        }
    }

    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragmentContainer, fragment)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public void returnFragments(ArrayList<Fragment> fragmentsList) {
        fragments = fragmentsList;
    }

    @Override
    public void switchFragments(Fragment fragment) {
        switchFragment(fragment);
    }

    private CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long l) {
            tvTimer.setText(l / 1000 + "");
        }

        @Override
        public void onFinish() {
            tvTimer.setText("0:00");
        }
    };


    @Override
    public void startTimer() {
        countDownTimer.start();
    }

    @Override
    public void endTimer() {
        tvTimer.setText("20:00");
    }

    @Override
    public void pauseTimer() {

    }
}
