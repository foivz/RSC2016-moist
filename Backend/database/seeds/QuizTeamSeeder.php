<?php

use Illuminate\Database\Seeder;

class QuizTeamSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('quiz_team')->insert([
            [
                'team_id' => 1,
                'quiz_id' => 1,
            ],
            [
                'team_id' => 2,
                'quiz_id' => 1,
            ],
            [
                'team_id' => 3,
                'quiz_id' => 1,
            ],
        ]);
    }
}
