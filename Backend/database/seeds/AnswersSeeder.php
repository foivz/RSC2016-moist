<?php

use Illuminate\Database\Seeder;

class AnswersSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('answers')->insert([
            [
                "question_id" => 1,
                "content" => "dog",
                "answer" => true
            ],
            [
                "question_id" => 1,
                "content" => "cat",
                "answer" => false
            ],
            [
                "question_id" => 1,
                "content" => "bird",
                "answer" => false
            ],


            [
                "question_id" => 2,
                "content" => "lion",
                "answer" => false
            ],
            [
                "question_id" => 2,
                "content" => "cat",
                "answer" => false
            ],
            [
                "question_id" => 2,
                "content" => "cheetah",
                "answer" => true
            ],


            [
                "question_id" => 3,
                "content" => "shakira",
                "answer" => false
            ],
            [
                "question_id" => 3,
                "content" => "justin bieber",
                "answer" => false
            ],
            [
                "question_id" => 3,
                "content" => "the ramones",
                "answer" => true
            ],
            [
                "question_id" => 3,
                "content" => "misfits",
                "answer" => true
            ],


            [
                "question_id" => 3,
                "content" => "asia",
                "answer" => true
            ],
        ]);
    }
}
