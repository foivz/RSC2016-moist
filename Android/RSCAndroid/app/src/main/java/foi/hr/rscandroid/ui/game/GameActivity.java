package foi.hr.rscandroid.ui.game;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.QuestionData;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.shared.FragmentCommandListener;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;

public class GameActivity extends BaseActivity implements GameView, FragmentCommandListener {

    public static final String EXTRA_QUESTIONS = "EXTRA_QUESTIONS";

    public static final String EXTRA_QUESTION = "EXTRA_QUESTION";

    @BindView(R.id.btn_user_sync)
    Button btnUserSync;

    @BindView(R.id.btn_mod_next)
    Button moderatorNextQ;

    private QuestionData question;

    @BindView(R.id.tv_timer)
    TextView tvTimer;

    private ArrayList<QuestionData> questions;

    private GamePresenter presenter;

    private ArrayList<Fragment> fragments;

    private int currPos = 0;

    private int currAnswer = 0;

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

        initUi();
    }

    private void initUi() {
        if (SharedPrefsHelper.getSharedPrefsBoolean("isMod")) {
            moderatorNextQ.setVisibility(View.VISIBLE);
            moderatorNextQ.setEnabled(false);
            btnUserSync.setVisibility(View.GONE);
        } else {
            btnUserSync.setVisibility(View.VISIBLE);
            moderatorNextQ.setVisibility(View.GONE);
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

    private CountDownTimer countDownTimer = new CountDownTimer(20000, 1000) {
        @Override
        public void onTick(long l) {
            tvTimer.setText(l / 1000 + "");
        }

        @Override
        public void onFinish() {
            moderatorNextQ.setEnabled(true);
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

    @Override
    public void rbChecked(int i) {
        currAnswer = i;
    }

    @OnClick(R.id.btn_user_sync)
    public void syncClicked() {
        presenter.syncClicked(currAnswer);
    }

    @OnClick(R.id.btn_mod_next)
    public void nextClicked() {
        presenter.requestNewQuestion();
    }
}
