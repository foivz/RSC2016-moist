<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>RSC Project</title>
  <meta name="description" content="RSC Project">
  <meta name="author" description="Team moist">

  <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Oxygen:400,300,700">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" type="text/css" href="css/materialize/css/materialize.css">

  <script src="https://use.fontawesome.com/5b0848ecb2.js"></script>
</head>
<body>
  
<nav>
    <div class="nav-wrapper">
        <a href="home" class="brand-logo"><i class="fa fa-graduation-cap"></i>GetQuizzd</a>
        <ul class="right">
            <li><a href="home">Home</a></li>
            <li><a href="quizzes">Quizzes</a></li>
            <li><a href="history">Previous quizzes</a></li>
            <li><a href="highscore">Leader board</a></li>
        </ul>
    </div>
</nav>

@yield('content')    

<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
<script type="text/javascript" src="/js/custom.js"></script>

</body>
</html>