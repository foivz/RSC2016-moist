<?php

namespace App\Http\Controllers;

use App\Quiz;
use App\QuizTeam;
use App\Team;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use sngrl\PhpFirebaseCloudMessaging\Client;
use sngrl\PhpFirebaseCloudMessaging\Message;
use sngrl\PhpFirebaseCloudMessaging\Notification;
use sngrl\PhpFirebaseCloudMessaging\Recipient\Topic;

class QuizTeamController extends Controller
{
    private $client;

    public function __construct()
    {
        $this->client = new Client();
        $this->client->setApiKey(env('FIREBASE_ID'));
        $this->client->injectGuzzleHttpClient(new \GuzzleHttp\Client());
    }

    /**
     * @Middleware("auth:api")
     * @Post("/api/quiz/{quiz_id}/team/{team_id}", as="api.quiz.team.create")
     */
    public function create($quiz_id, $team_id)
    {
        $ret['response']['debug']['quiz'] = $quiz = Quiz::find($quiz_id);
        $ret['response']['debug']['team'] =$team = Team::find($team_id);
        $ret['response']['debug']['quizTeam'] =$quizTeamData = QuizTeam::where('quiz_id', $quiz_id)->where('team_id', $team_id)->first();

        if ($quiz != null && $team != null && $quizTeamData == null) {
            $quizTeam = new QuizTeam;
            $quizTeam->team_id = $team_id;
            $quizTeam->quiz_id = $quiz_id;
            $quizTeam->save();

            return response("{}", Response::HTTP_OK);
        }

        $ret['response']['error'] = "Team / Quiz not found or already joined.";

        return response($ret, Response::HTTP_BAD_REQUEST);
    }

    /**
     * Store curr answer in table....this value will be stored as
     * their final answer when times up!
     *
     * @Middleware("auth:api")
     * @Post("/api/quiz/{quiz_id}/team/{team_id}/answer/submit")
     */
    public function submitAnswer(Request $request, $quiz_id, $team_id)
    {
        $this->pushNotification($request->get('request')['answer'], $team_id);
    }

    /**
     * Triggered when time is up (called by client device)
     * Actually scores the answer....
     *
     * @Middleware("auth:api")
     * @Post("/api/quiz/{quiz_id}/team/{team_id}/answer/evaluate")
     */
    public function evaluateAnswer(Request $request, $quiz_id, $team_id)
    {
        $quiz_team = QuizTeam::where('quiz_id', $quiz_id)->where('team_id', $team_id);
        $quiz_team->score = $quiz_team->score + 10;
        $quiz_team->save();

        return response("{}", Response::HTTP_OK);
    }

    private function pushNotification($answer, $team_id)
    {
        $message = new Message();

        $message->setPriority('high');
        $message->addRecipient(new Topic($team_id)); // topic name is team_id
        $message->setNotification(new Notification('New Answer!', 'Answer: ' . $answer['id']))
            ->setData(['answer_id' => $answer['id']]);

        $response = $this->client->send($message);

        $ret['response']['status'] = $response->getStatusCode();
        $ret['response']['body'] = $response->getBody()->getContents();

        return $ret;
    }
}
