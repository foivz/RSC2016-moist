<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Team extends Model
{
    protected $table = 'quiz_team';

    protected $fillable = [
        'name'
    ];
}
