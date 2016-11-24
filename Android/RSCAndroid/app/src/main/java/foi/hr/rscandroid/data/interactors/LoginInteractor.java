package foi.hr.rscandroid.data.interactors;


import foi.hr.rscandroid.data.networking.ApiService;

public class LoginInteractor {

    private ApiService apiService;

    public LoginInteractor(ApiService apiService) {
        this.apiService = apiService;
    }
}
