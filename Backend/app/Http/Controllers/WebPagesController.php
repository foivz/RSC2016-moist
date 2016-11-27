<?php

namespace App\Http\Controllers;

use App\Quiz;
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
    	$data = DB::select('SELECT q.* FROM quizzes q');

    	return view('pages.quizzes')->with('data', $data);
    }

    /**
     * @Get("/history")
     */
    public function history()
    {
    	$data = Quiz::whereDate('date', '<=', Carbon::now()->toDateString())->get();

    	return view('pages.history')->with('data', $data);
    }

    /**
      * @Get("/highscore")
      */ 
    public function highscore()
    {
    	$data = DB::select('SELECT t.name team_name, q.name quiz_name, qt.score FROM teams t, quizzes q, quiz_team qt WHERE qt.team_id = t.id AND qt.quiz_id = q.id ORDER BY qt.score DESC');

    	return view('pages.highscore')->with('data', $data);
    }

    /**
     * @Get("/admin")
     */
    public function adminPanel()
    {
      $data = DB::select('SELECT q.* FROM quizzes q');

      return view('pages.admin')->with('data', $data);
    }

    /**
     * @Get("/admin-edit")
     */
    public function adminPanelAddQuiz()
    {
      return view('pages.admin-edit');
    }
}
