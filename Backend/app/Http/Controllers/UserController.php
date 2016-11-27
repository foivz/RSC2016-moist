<?php

namespace App\Http\Controllers;

use App\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Validator;

/**
 * @Middleware("auth:api")
 */
class UserController extends Controller
{
    /**
     * @Post("/api/users/create", as="api.users.create")
     */
    public function create(Request $request)
    {
        $ret['debug']['tokenuser'] = $tokenUser = $request->user();
        $ret['debug']['requser'] = $requestUser = $request->get('request')['user'];

        $rules = ['nickname' => 'unique:users'];

        if ($tokenUser->email == $requestUser['email']) {
            if (Validator::make($requestUser, $rules)->passes()) {
                User::where('email', $tokenUser->email)
                    ->update(['nickname' => $requestUser['nickname'], 'registered' => true]);

                return response("{}", Response::HTTP_OK);
            }

            $ret['response']['error'] = "Nickname already in use.";

            return response($ret, Response::HTTP_BAD_REQUEST);
        }

        $ret['response']['error'] = "User and token do not match.";

        return response($ret, Response::HTTP_BAD_REQUEST);
    }

    /**
     * @Put("/api/users/{id}", as="api.users.update")
     */
    public function update(Request $request, $id)
    {
        $tokenUser = $request->user();
        $requestUser = $request->get('request')['user'];

        $rules = ['nickname' => 'unique:users'];

        if (Validator::make($requestUser, $rules)->passes()) {
            $tokenUser->nickname = $requestUser['nickname'];
            $tokenUser->save();

            return response("{}", Response::HTTP_OK);
        }

        $ret['response']['error'] = "Nickname already in use.";

        return response($ret, Response::HTTP_BAD_REQUEST);

        $ret['response']['error'] = "User and token do not match.";

        return response($ret, Response::HTTP_BAD_REQUEST);
    }
}
