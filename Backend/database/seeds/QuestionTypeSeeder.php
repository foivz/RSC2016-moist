<?php

use Illuminate\Database\Seeder;

class QuestionTypeSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('question_types')->insert([
            [
                "name" => "single"
            ],
            [
                "name" => 'multiple'
            ],
            [
                "name" => 'edit'
            ]
        ]);
    }
}
