@extends('master')

@section('title', 'Highscore')

@section('content')
<table class="highlight centered responsive-table">
    <thead>
    <tr>
        <th>Team name</th>
        <th>Quizz name</th>
        <th>Score</th>
    </tr>
    </thead>
    <tbody>
    @for ($i = 0; $i < count($data); ++$i)
        <tr>
            <td>{!! $i + 1 !!}</td>
            <td>{!! $data[$i]->team_name !!}</td>
            <td>{!! $data[$i]->quiz_name !!}</td>
            <td>{!! $data[$i]->score !!}</td>
        </tr>
    @endfor
    </tbody>
</table>
@endsection