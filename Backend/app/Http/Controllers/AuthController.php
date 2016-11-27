<?php

namespace App\Http\Controllers;

use App\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Laravel\Socialite\Facades\Socialite;

/**
 * @Middleware("api")
 */
class AuthController extends Controller
{
    /**
     * @Post("/api/{driver}/authenticate/{token}", as="api.social.authenticate")
     */
    public function socialAuthenticate($driver, $token)
    {
        if ($driver !== "facebook" && $driver !== "google") {
            return response('{"error": "Unknown driver."}', Response::HTTP_BAD_REQUEST);
        }

        if ($driver !== "google") $socialUser = Socialite::driver($driver)->userFromToken($token);
        else $socialUser = json_decode(file_get_contents("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=$token"));

        $userData = [
            "name" => $socialUser->name,
            "email" => $socialUser->email,
            "avatar" => $driver !== "google" ? $socialUser->avatar : $socialUser->picture,
        ];

        $user = User::firstOrCreate($userData);

        $ret['response']['user'] = $user;
        $ret['response']['token'] = $user->createToken($user->email)->accessToken;

        return response($ret, Response::HTTP_OK);
    }

    /**
     * @Middleware("web")
     * @Get("/social/{driver}/redirect")
     *
     * Redirect the user to the GitHub authentication page.
     *
     * @return Response
     */
    public function redirectToProvider($driver)
    {
        return Socialite::driver($driver)->redirect();
    }

    /**
     * @Middleware("web")
     * @Get("/social/{driver}/callback")
     *
     * Obtain the user information from GitHub.
     *
     * @return Response
     */
    public function handleProviderCallback($driver)
    {
        $user = Socialite::driver($driver)->user();

        dd($user);
    }
}
