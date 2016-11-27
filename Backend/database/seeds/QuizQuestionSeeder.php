<?php

use Illuminate\Database\Seeder;

class QuizQuestionSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('quiz_questions')->insert([
            [
                "question_id" => 1,
                "quiz_id" => 1
            ],
            [
                "question_id" => 2,
                "quiz_id" => 1
            ],
            [
                "question_id" => 3,
                "quiz_id" => 1
            ],
            [
                "question_id" => 1,
                "quiz_id" => 2
            ]
        ]);
    }
}
