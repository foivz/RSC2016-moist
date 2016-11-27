package foi.hr.rscandroid.data.interactors;


import foi.hr.rscandroid.data.networking.ApiService;

public class GameInteractor {

    private ApiService service;

    public GameInteractor(ApiService service) {
        this.service = service;
    }
}
