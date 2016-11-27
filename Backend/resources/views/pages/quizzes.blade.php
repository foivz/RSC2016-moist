@extends('master')

@section('title', 'Quizzes')

@section('content')
  <table class="highlight centered responsive-table">
      <thead>
      <tr>
          <th>Quizz name</th>
          <th>Date</th>
          <th>Time</th>
      </tr>
      </thead>
      <tbody>
      @for ($i = 0; $i < count($data); ++$i)
        <tr>
            <td>{!! $data[$i]->name !!}</td>
            <td>{!! $data[$i]->date !!}</td>
            <td>{!! $data[$i]->time !!}</td>
            <td><a class="waves-effect waves-light btn" href="#modal{!! $i+1 !!}">Details</a></td>
        </tr>
      @endfor
      </tbody>
  </table>

  @for ($i = 0; $i < count($data); ++$i)
    <div id="modal{!! $i+1 !!}" class="modal">
      <div class="modal-content">
        <h4>{!! $data[$i]->name !!}</h4>
        <p>{!! $data[$i]->description !!}</p>
      </div>
      <div class="modal-footer">
        <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
      </div>
    </div>
  @endfor
@endsection