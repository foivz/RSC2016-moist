package foi.hr.rscandroid.ui.shared;


public interface Listener<Type> {

    void onSuccess(Type type);

    void onError(String error);

}
