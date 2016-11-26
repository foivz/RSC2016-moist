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
    }
}
