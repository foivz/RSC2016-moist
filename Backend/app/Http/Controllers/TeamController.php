<?php

namespace App\Http\Controllers;

use App\Team;
use App\TeamMember;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Validator;

class TeamController extends Controller
{
    /**
     * @Middleware("auth:api")
     * @Get("/api/team/{id}", as="api.team.show")
     */
    public function show(Request $request, $id)
    {
        $ret['response']['team'] = Team::find($id);

        if ($ret['response']['team'] != null) {
            $ret['response']['team'] = ($ret['response']['team'])->first();
            $ret['response']['team']['members'] = DB::select('SELECT u.nickname FROM users u, team_members tm, teams t WHERE tm.user_id = u.id AND tm.team_id = t.id AND t.id = ' . $id);

            return response($ret, Response::HTTP_OK);
        }

        $ret['response']['error'] = "Team not found";

        return response($ret, Response::HTTP_BAD_REQUEST);
    }

    /**
     * @Middleware("auth:api")
     * @Post("/api/team", as="api.team.create")
     */
    public function create(Request $request)
    {
        $user = $request->user();
        $requestTeam = $request->get('request')['team'];

        if (Validator::make($requestTeam, ['name' => 'unique:teams'])->passes()) {
            $teamData = [
                "name" => $requestTeam['name'],
                "size" => $requestTeam['size'],
            ];

            $team = Team::firstOrCreate($teamData);

            $teamUserData = [
                "team_id" => $team->id,
                "user_id" => $user->id
            ];

            TeamMember::firstOrCreate($teamUserData);

            return response("{}", Response::HTTP_OK);
        }

        $ret['response']['error'] = "Team name in use.";

        return response($ret, Response::HTTP_BAD_REQUEST);
    }
}
