<?php

use Illuminate\Database\Seeder;

class TeamMembersSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('team_members')->insert([
            [
                "team_id" => 1,
                "user_id" => 1
            ],
            [
                "team_id" => 1,
                "user_id" => 2
            ],
            [
                "team_id" => 2,
                "user_id" => 1
            ],
            [
                "team_id" => 3,
                "user_id" => 1
            ],
        ]);
    }
}
