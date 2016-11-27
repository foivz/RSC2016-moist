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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
  
<ul id="slide-out" class="side-nav fixed">
    <li><div class="userView">
      <div class="background">
        <img src="img/nav-drawer-header.jpg">
      </div>
      <a href="#!user"><img class="circle" src="img/profile-pic-placeholder.png"></a>
      <a href="#!name"><span class="white-text name">Administrator</span></a>
      <a href="#!email"><span class="white-text email">admin@getquizzd.com</span></a>
    </div></li>
    <li><a class="subheader">Control panel</a></li>
    <li><a class="waves-effect" href="admin-edit">Add a quiz</a></li>
    <li><a class="waves-effect" href="admin">Quiz list</a></li>
  </ul>
<a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>

@yield('content')    

<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/custom.js"></script>

</body>
</html>