@extends('admin-master')

@section('title', 'Admin')

@section('content')
<div class="admin-content-container">
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
            <td>{!! $data[$i]->name !!}</td>
            <td>{!! $data[$i]->date !!}</td>
            <td>{!! $data[$i]->time !!}</td>
            <td><a class="waves-effect waves-light btn" href="#modal{!! $i+1 !!}">Edit</a></td>
	    </tr>
	@endfor
	</tbody>
</table>
</div>

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