<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Quiz extends Model
{
    protected $table = 'quizzes';

    protected $fillable = [
        'moderator_id', 'latitude', 'longitude', 'date_time', 'description', 'prizes', 'rules'
    ];

    public function teams()
    {
        return $this->belongsToMany('App\Team', 'quiz_team', 'quiz_id', 'team_id');
    }
}
