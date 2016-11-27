<?php

namespace App\Http\Controllers;

use App\Answers;
use App\Quiz;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\DB;

class QuizController extends Controller
{
    /**
     * @Get("/api/quiz/", as="api.quiz.index")
     */
    public function index()
    {
        $ret['response']['quizzes'] = Quiz::whereDate('date', '>=', Carbon::now()->toDateString())->get();

        for ($i = 0; $i < count($ret['response']['quizzes']); ++$i) {
            $quiz = $ret['response']['quizzes'][$i];
            $quiz['teams'] = DB::select('SELECT t.* FROM teams t, quiz_team qt WHERE qt.team_id = t.id AND qt.quiz_id = ' . $quiz['id']);
            $ret['response']['quizzes'][$i]['teams'] = $quiz['teams'];
        }

        for ($i = 0; $i < count($ret['response']['quizzes']); ++$i) {
            $quiz = $ret['response']['quizzes'][$i];
            $quiz['questions'] = DB::select('SELECT * FROM questions q, quiz_questions qt WHERE qt.quiz_id = '. $quiz['id'] .' AND qt.question_id = q.id');
            $ret['response']['quizzes'][$i]['questions'] = $quiz['questions'];
        }

        for ($i = 0; $i < count($ret['response']['quizzes']); ++$i) {
             for ($j = 0; $j < count($ret['response']['quizzes'][$i]['questions']); ++$j) {
                 $question = $ret['response']['quizzes'][$i]['questions'][$j];
                 $answers = DB::select('SELECT * FROM answers WHERE question_id = ' . $question->id);
                 $question->answers = $answers;
             }
        }

        return response($ret, Response::HTTP_OK);
    }

    /**
     * @Get("/api/quiz/users/")
     */
    public function userSignedUpQuizzes(Request $request)
    {
        $user = $request->user();
    }

    /**
     * @Get("/api/quiz/{id}", as="api.quiz.show")
     */
    public function show($id)
    {
        $ret['response']['quiz'] = Quiz::find($id);
        return response($ret, Response::HTTP_OK);
    }

    /**
     * @Middleware("auth:web")
     * @Middleware("auth:api")
     * @Post("/api/quiz/", as="api.quiz.create")
     */
    public function create(Request $request)
    {
        $data = $request->get('request')['quiz'];

        $this->saveModel($data, new Quiz);

        return response("{}", Response::HTTP_OK);
    }

    /**
     * @Middleware("auth:api")
     * @Put("/api/quiz/{id}", as="api.quiz.update")
     */
    public function update(Request $request, $id)
    {
        $data = $request->get('request')['quiz'];

        $quiz = Quiz::find($id);

        if ($quiz != null) {
            $this->saveModel($data, $quiz);

            return response("{}", Response::HTTP_OK);
        }

        $ret['response']['error'] = "Quiz not found.";

        return response($ret, Response::HTTP_BAD_REQUEST);
    }

    private function saveModel($data, $quiz)
    {
        $quiz->moderator_id = $data['moderator_id'];
        $quiz->name = $data['name'];
        $quiz->latitude = $data['latitude'];
        $quiz->longitude = $data['longitude'];
        $quiz->date = $data['date'];
        $quiz->time = $data['time'];
        $quiz->description = $data['description'];
        $quiz->prizes = $data['prizes'];
        $quiz->rules = $data['rules'];

        $quiz->save();
    }
}
