<?php

namespace App\Http\Controllers;

use Carbon\Carbon;
use Illuminate\Http\Request;

use App\Http\Requests;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class WebPagesController extends Controller
{
	/**
	 * @Get("/", as="web.index")
	 */
    public function index()
    {
		return view('pages.home');
    }

    /**
     * @Get("/home", as="web.index")
     */
    public function home()
    {
    	return view('pages.home');
    }

    /**
     * @Get("/quizzes")
     */
    public function quizzes()
    {
    	return view('pages.quizzes');
    }

    /**
     * @Get("/rules")
     */
    public function rules()
    {
    	return view('pages.rules');
    }

    /**
     * @Get("/history")
     */
    public function history()
    {
    	return view('pages.history');
    }

    /**
      * @Get("/highscore")
      */ 
    public function highscore()
    {
    	$data = DB::select('SELECT t.name team_name, q.name quiz_name, qt.score FROM teams t, quizzes q, quiz_team qt WHERE qt.team_id = t.id AND qt.quiz_id = q.id ORDER BY qt.score DESC');

    	return view('pages.highscore')->with('data', $data);
    }
}
