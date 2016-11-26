<?php

namespace App\Http\Controllers;

use App\Team;
use App\TeamMember;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\DB;

class TeamUserController extends Controller
{
    /**
     * @Middleware("auth:api")
     * @Post("/api/team/{team_id}/user/{user_id}")
     */
    public function addMember($team_id, $user_id)
    {
        $team = Team::find($team_id);
        $teamMemberData = TeamMember::where('user_id', $user_id)->where('team_id', $team_id)->first();

        if ($team != null && $teamMemberData == null) {
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

        $ret['response']['error'] = "Team not found or User already a member.";

        return response($ret, Response::HTTP_BAD_REQUEST);
    }
}
