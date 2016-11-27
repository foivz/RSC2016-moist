package foi.hr.rscandroid.ui.qr;

import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.ui.BaseMvp;

public interface ScannerView extends BaseMvp.View {

    void showNewTeam(Team team);
}
