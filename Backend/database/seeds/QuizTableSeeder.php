<?php

use Carbon\Carbon;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class QuizTableSeeder extends Seeder
{
    function run()
    {
        DB::table('quizzes')->insert([
            [
                "moderator_id" => 1,
                "name" => "Vindija Quizzzz #2",
                "latitude" => 46.311351,
                "longitude" => 16.347159,
                "date" => "2016-11-27",
                "time" => "14:22:00",
                "description" => "alkfhalkfh lkadflkh alkdhf lknadfhlkna dflkhnalkn",
                "prizes" => "sdfokhsdlkfhnsdlkfnhlskdfnhlsd",
                "rules" => "askhdfglaskhfgsalfksnlg",
                "created_at" => Carbon::now(),
                "updated_at" => Carbon::now()
            ],
            [
                "moderator_id" => 1,
                "name" => "Gimna Kviz #1",
                "latitude" => 46.307915,
                "longitude" => 16.339330,
                "date" => "2016-11-27",
                "time" => "14:22:00",
                "description" => "alkfhalkfh lkadflkh alkdhf lknadfhlkna dflkhnalkn",
                "prizes" => "sdfokhsdlkfhnsdlkfnhlskdfnhlsd",
                "rules" => "askhdfglaskhfgsalfksnlg",
                "created_at" => Carbon::now(),
                "updated_at" => Carbon::now()
            ],
            [
                "moderator_id" => 1,
                "name" => "Vindija Quizzzz #1",
                "latitude" => 46.306277,
                "longitude" => 16.336530,
                "date" => "2016-11-28",
                "time" => "14:22:00",
                "description" => "alkfhalkfh lkadflkh alkdhf lknadfhlkna dflkhnalkn",
                "prizes" => "sdfokhsdlkfhnsdlkfnhlskdfnhlsd",
                "rules" => "askhdfglaskhfgsalfksnlg",
                "created_at" => Carbon::now(),
                "updated_at" => Carbon::now()
            ],
            [
                "moderator_id" => 1,
                "name" => "FOI Kvisko",
                "latitude" => 46.305532,
                "longitude" => 16.338453,
                "date" => "2016-11-29",
                "time" => "14:22:00",
                "description" => "alkfhalkfh lkadflkh alkdhf lknadfhlkna dflkhnalkn",
                "prizes" => "sdfokhsdlkfhnsdlkfnhlskdfnhlsd",
                "rules" => "askhdfglaskhfgsalfksnlg",
                "created_at" => Carbon::now(),
                "updated_at" => Carbon::now()
            ],
        ]);
    }
}
