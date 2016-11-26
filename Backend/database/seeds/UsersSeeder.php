<?php

use Carbon\Carbon;
use Illuminate\Database\Seeder;

class UsersSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('users')->insert([
            [
                "name" => "Stefano Kliba",
                "nickname" => "skliba",
                "email" => "rajkov.stefano@gmail.com",
                "registered" => true,
                "created_at" => Carbon::now(),
                "updated_at" => Carbon::now(),
            ],
            [
                "name" => "x x",
                "nickname" => "x",
                "email" => "x@foi.hr",
                "registered" => true,
                "created_at" => Carbon::now(),
                "updated_at" => Carbon::now()
            ],
            [
                "name" => "Y x",
                "nickname" => "y",
                "email" => "y@foi.hr",
                "registered" => true,
                "created_at" => Carbon::now(),
                "updated_at" => Carbon::now()
            ],
            [
                "name" => "Z x",
                "nickname" => "z",
                "email" => "z@foi.hr",
                "registered" => true,
                "created_at" => Carbon::now(),
                "updated_at" => Carbon::now()
            ],
        ]);
    }
}
