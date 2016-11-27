@extends('admin-master')

@section('title', 'Admin Edit')

@section('content')

<div class="admin-content-container">
	<div class="row">
    <form class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <input id="name" type="text" class="validate">
          <label for="name">Name</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input id="date" type="text" class="validate">
          <label for="date">Date</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input id="time" type="text" class="validate">
          <label for="time">Time</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input id="description" type="text" class="validate">
          <label for="description">Description</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input id="prizes" type="text" class="validate">
          <label for="prizes">Prizes</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input id="rules" type="text" class="validate">
          <label for="rules">Rules</label>
        </div>
      </div>
	  <button id="btnSubmitData" class="btn waves-effect waves-light submit-quiz" type="submit" name="action">Submit
		<i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
    </form> 
  </div>
</div>

@endsection