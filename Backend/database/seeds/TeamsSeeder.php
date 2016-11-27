<?php

use Illuminate\Database\Seeder;

class TeamsSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('teams')->insert([
            [
                'name' => "Team #1",
                'size' => 4
            ],
            [
                'name' => "Team #2",
                'size' => 5
            ],
            [
                'name' => "Team #3",
                'size' => 6
            ],
        ]);
    }
}
