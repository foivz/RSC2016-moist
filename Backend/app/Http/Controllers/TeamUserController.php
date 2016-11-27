<?php

namespace App\Http\Controllers;

use App\Team;
use App\TeamMember;
use App\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\DB;

class TeamUserController extends Controller
{
    /**
     * @Get("/api/teams/users/{user_id}")
     */
    public function getUserTeams($user_id)
    {
        $ret['response']['teams'] = DB::select('SELECT t.* FROM teams t, team_members tm, users u WHERE tm.team_id = t.id AND u.id = tm.user_id AND u.id =' . $user_id);

        return response($ret, Response::HTTP_OK);
    }

    /**
     * @Middleware("auth:api")
     * @Post("/api/team/{team_id}/user/{user_id}")
     */
    public function addMember($team_id, $user_id)
    {
        $team = DB::select('SELECT * FROM teams WHERE id = ' . $team_id);
        $user = User::find($user_id);
        $teamMemberData = TeamMember::where('user_id', $user_id)->where('team_id', $team_id)->first();

        if ($team != null && $user != null && $teamMemberData == null) {
            $team = $team->first();

            $teamMembers = DB::select('SELECT u.nickname FROM users u, team_members tm, teams t WHERE tm.user_id = u.id AND tm.team_id = t.id AND t.id = ' . $team_id);

            if (count($teamMembers) < $team['size']) {
                $teamMember = new TeamMember;
                $teamMember->team_id = $team_id;
                $teamMember->user_id = $user_id;
                $teamMember->save();

                $ret['response']['team'] = $team;
                $ret['response']['team']['members'] = DB::select('SELECT u.nickname FROM users u, team_members tm, teams t WHERE tm.user_id = u.id AND tm.team_id = t.id AND t.id = ' . $team_id);

                return response($ret, Response::HTTP_OK);
            }

            $ret['response']['error'] = "Team full";

            return response($ret, Response::HTTP_BAD_REQUEST);
        }

        $ret['response']['error'] = "Team / User not found or User already a member.";

        return response($ret, Response::HTTP_BAD_REQUEST);
    }
}
