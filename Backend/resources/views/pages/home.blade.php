@extends('master')

@section('title', 'Home')

@section('content')
<div class="content">
  <h1 class="content-heading">Team moist presents</h1>
  <hr />
  <h1 class="content-heading">GetQuizzd</h1>
  <p class="content-teaser">Meet GetQuizzd - an app that allowes your team to attend various quizzes. <br/> Prove your worth and win great prizes!</p>
  <div class="social-login-container">
	  <a href="#" class="fb-login"><i class="fa fa-facebook-official" aria-hidden="true"></i></a>  	
	  <a href="#" class="gp-login"><i class="fa fa-google-plus-square" aria-hidden="true"></i></a>  	
	  <a href="#" class="tw-login"><i class="fa fa-twitter-square" aria-hidden="true"></i></a>  	
  </div>
</div>

<video id="my-video" class="video" muted loop>
  <source src="media/demo.mp4" type="video/mp4">
  <source src="media/demo.ogv" type="video/ogg">
  <source src="media/demo.webm" type="video/webm">
</video>

<script>
(function() {
  var video = document.getElementById("my-video");

  video.addEventListener("canplay", function() {
    video.play();
  });
})();
</script>
@endsection