<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class QuizTeam extends Model
{
    protected $table = 'quiz_team';

    protected $fillable = ['quiz_id', 'team_id'];
}
