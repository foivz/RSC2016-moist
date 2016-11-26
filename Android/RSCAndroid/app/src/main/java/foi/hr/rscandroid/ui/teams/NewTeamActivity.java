package foi.hr.rscandroid.ui.teams;

import com.rengwuxian.materialedittext.MaterialEditText;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;

public class NewTeamActivity extends AppCompatActivity {

    @BindView(R.id.et_team_name)
    MaterialEditText etTeamName;

    @BindView(R.id.et_max_team_members)
    MaterialEditText etMaxTeamMembers;

    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_team);
        ButterKnife.bind(this);
    }
}
