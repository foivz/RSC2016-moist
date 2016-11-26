<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateQuizTeamTable extends Migration
{
    public function up()
    {
        Schema::create('quiz_team', function (Blueprint $table) {
            $table->integer('quiz_id')->unsigned()->nullable()->default(NULL);
            $table->foreign('quiz_id')->references('id')->on('quizzes');
            $table->integer('team_id')->unsigned()->nullable()->default(NULL);
            $table->foreign('team_id')->references('id')->on('teams');
        });
    }

    public function down()
    {
        Schema::drop('quiz_team');
    }
}
