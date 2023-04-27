<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>ATITUP </title><link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="main">
		<!-- Sign in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
							<img src="images/signInUser.jpg" alt="sing up image">
						</figure>
						<a href="LoginServlet" class="signup-image-link">Administrator</a>
					</div>

					<div class="signin-form">
						<h2 class="form-title">User Log In</h2>
						<form method="post" action="UserLoginServlet" class="register-form" 
							id="login-form" >
                                                <select name="select" id="language" style="width: 90px">
                                                  <option value="es">Spanish</option>
                                                  <option value="en" selected>English</option>
                                                  <option value="de">Deutsch</option>
                                                  <option value="fr">French</option>
                                                </select>
							<div class="form-group">
								<label for="username"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="email" name="email" id="username"
									placeholder="Enter your email" />
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />
							</div>
						</form>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>

	</div>

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>