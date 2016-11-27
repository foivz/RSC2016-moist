<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $this->call(QuizTableSeeder::class);
        $this->call(TeamsSeeder::class);
        $this->call(QuizTeamSeeder::class);
        $this->call(UsersSeeder::class);
        $this->call(TeamMembersSeeder::class);
        $this->call(QuestionTypeSeeder::class);
        $this->call(QuestionCategoriesSeeder::class);
        $this->call(QuestionsSeeder::class);
        $this->call(AnswersSeeder::class);
        $this->call(QuizQuestionSeeder::class);
    }
}
