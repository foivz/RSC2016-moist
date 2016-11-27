package foi.hr.rscandroid.ui.game;


import android.support.v4.app.Fragment;

import java.util.ArrayList;

import foi.hr.rscandroid.ui.BaseMvp;

public interface GameView extends BaseMvp.View {

    void returnFragments(ArrayList<Fragment> fragmentsList);

    void switchFragments(Fragment fragment);
}
