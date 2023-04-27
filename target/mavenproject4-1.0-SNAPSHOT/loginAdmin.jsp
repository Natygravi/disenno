<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>ATITUP ADMIN LOGIN</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
    <input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Login Administrator</h2>
					
						<form method="post" action="LoginServlet" class="register-form"
							id="register-form">							
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> 
                                                                <input
									type="email" name="email" id="email" placeholder="Your Email" />
							</div>
							<div class="form-group form-button">
								<input type="submit" name="login" id="signup"
									class="form-submit" value="Log in" />
							</div>
						</form>
					</div>
					<div class="signin-image">
						<figure>
							<img src="images/signin-image.png" alt="sing up image">
						</figure>
						<a href="login.jsp" class="signin-image-link">User Page</a>
					</div>
				</div>
			</div>
		</section>


	</div>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>        
        <link rel="stylesheet"href="alert/dist/sweetalert.css">
</body>
</html>