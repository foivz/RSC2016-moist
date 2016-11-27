<?php

use Illuminate\Database\Seeder;

class QuestionsSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('questions')->insert([
            [
                "category_id" => 1,
                "type_id" => 1,
                "content" => "Which animal barks?"
            ],
            [
                "category_id" => 1,
                "type_id" => 1,
                "content" => "Fastest running animal?"
            ],
            [
                "category_id" => 2,
                "type_id" => 2,
                "content" => "Select punk bands..."
            ],
            [
                "category_id" => 3,
                "type_id" => 3,
                "content" => "Enter the name of the oldest continent..."
            ],
        ]);
    }
}
