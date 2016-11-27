@extends('master')

@section('title', 'History')

@section('content')
  <table class="highlight centered responsive-table">
      <thead>
      <tr>
          <th>Quizz name</th>
          <th>Date</th>
          <th>Time</th>
          <th>History</th>
      </tr>
      </thead>
      <tbody>
      @for ($i = 0; $i < count($data); ++$i)
        <tr>
            <td>{!! $data[$i]->name !!}</td>
            <td>{!! $data[$i]->date !!}</td>
            <td>{!! $data[$i]->time !!}</td>
            <td><a class="waves-effect waves-light btn" href="#modal{!! $i+1 !!}">History</a></td>
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
        <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Close</a>
      </div>
    </div>
  @endfor
@endsection