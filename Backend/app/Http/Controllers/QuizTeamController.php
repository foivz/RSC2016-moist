<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class QuizTeamController extends Controller
{
    /**
     * @Middleware("auth:api")
     * @Post("/api/quiz/{quiz_id}/team/{team_id}", as="api.quiz.team.create")
     */
    public function create(Request $request, $quiz_id, $team_id)
    {

    }
}
