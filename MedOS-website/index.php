<!DOCTYPE html>
<html>
  <head>
	<title>MedOS | The open source platform</title>
	<link rel="icon" type="png" href="images/favicon.png" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>

	
	<link href="css/font.css" rel="stylesheet" media="screen">

	<link href="css/grid12.css" rel="stylesheet" media="screen">							  	 <!-- Grid System -->
	<link href="css/james_typography.css" rel="stylesheet" media="screen">					   <!-- James Framework - TYPOGRAPHY -->
	<link href="css/main.css" rel="stylesheet" media="screen">								   <!-- Main CSS file -->
	<link href="css/color_scheme_dark.css" rel="stylesheet" media="screen">					 <!-- Color scheme (dark/light)-->
	<link href="css/colors/color_palette_indigo.css" rel="stylesheet" media="screen">			  <!-- Color palette -->
	<link href="css/responsivity.css" rel="stylesheet" media="screen">						   <!-- Responsive Fixes -->
	<link href="css/materialize.min.css" rel="stylesheet" media="screen">

	<link rel="stylesheet" href="css/style.css?v=1.1">

	
  </head>

  <body>
  	
	<div id="preloader_container" class="loaded">

		<header class="preloader_header">
			<div class="preloader_loader">
				<svg class="preloader_inner" width="60px" height="60px" viewBox="0 0 80 80">
					<path class="preloader_loader_circlebg" d="M40,10C57.351,10,71,23.649,71,40.5S57.351,71,40.5,71 S10,57.351,10,40.5S23.649,10,40.5,10z"/>
					<path id="preloader_loader_circle" class="preloader_loader_circle" d="M40,10C57.351,10,71,23.649,71,40.5S57.351,71,40.5,71 S10,57.351,10,40.5S23.649,10,40.5,10z"/>
				</svg>
			</div>
		</header>


		<section id="maini" class="hero_fullscreen background_solid" 
        style="background-color:#0e1f34 !important">
		  
		  	<div class="container align-center" id="main_content">
				<div class="content_container row" >
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			  			<img src="images/logo.png" alt="logo" class="logo"/>
		  			</div>
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-8 col-lg-offset-2">
						<h2  style="margin-top: 40px; letter-spacing : 3px; font-size: 43px; line-height: 50px ">
								MedOS<br>
						</h2>	
						<h6 style="padding-top: 5px ;  font-weight: 500 ; line-height: 20px; font-size: 18px!important; padding-bottom: 30px">
						We are a community, we work together to build the world's leading open source enterprise for medical record system platform.
						</h6>
						
                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6  linkini">
                            <a href="#">MedOS on Github</a>
                        </div>
                         <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6  linkini">
                            <a href="#demo" >Demo our software</a>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-lg-offset-3">
                    <a href="#signup" id="do_signup" onclick="open_registration()">Sign up</a>
                        </div>				
                    </div>
				</div>
			</div>
		</section>
        <section id="demo" class="subsection darker_bg align-center ">
            <h4 id="demot">Demonstration</h4>
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <img src="images/andro1.png" alt="Oooy" id="and1">
                <h5></h5>
            </div>

            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <img src="images/desk1.png" alt="Oooy" id="desk1">
            </div>


        </section>
				
		<section id="signup" class="align-center" style="background-color:#0e1f34 !important">
			<div class="container">
				<div class="row">

			<form action="index.php" method="POST" id="signup_form">
            <div class="col-md-4 col-lg-12 col-sm-4 col-xs-6" id="t">
            <h2 id="tt">Registration</h2>
            </div>
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
    			<div class="input-field col-md-4 col-lg-4 col-sm-4 col-xs-6">
    	          <input id="lname" name="lname" type="text" class="validate" required />
    	          <label for="name">Family name</label>
    	        </div>
    			<div class="input-field col-md-4 col-lg-4 col-sm-4 col-xs-6">
    	          <input id="lname" name="fname" type="text" class="validate" required />
    	          <label for="name">First name</label>
    	        </div>

    			<div class="input-field col-md-4 col-lg-4 col-sm-4 col-xs-6">
    	          <input id="email" name="email" type="email"  class="validate" required />
    	          <label for="email">Email</label>
    	        </div>
            </div>

            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
    	        <div class="input-field col-md-4 col-lg-4 col-sm-4 col-xs-6">
    	          <input id="phone" name="phone" type="text" class="validate" required />
    	          <label for="phone">Phone number</label>
    	        </div>
    	        <div class="input-field col-md-4 col-lg-4 col-sm-4 col-xs-6">
    	          <input id="birth" name="birth" type="text" class="validate" required />
    	          <label for="birth">Birthday</label>
    	        </div>
    			<div class="input-field col-md-4 col-lg-4 col-sm-4 col-xs-6">
    				<select name="gender" id="field_selector" required>
    					<option value="" selected disabled>---</option>
    					<option value="1">Female</option>
    					<option value="0">Male</option>
    				</select>
    				<label for="gender">Gender</label>
    			</div>
            </div>

            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
    			<div class="input-field col-md-4 col-lg-4 col-sm-4 col-xs-6">
    	          <input id="password" name="password" type="password"  class="validate" required />
    	          <label for="password">Password</label>
    	        </div>
    	        <div class="input-field col-md-4 col-lg-4 col-sm-4 col-xs-6">
    	          <input id="cpass" name="cpassword" type="password" class="validate" required />
    	          <label for="cpass">Confirm password</label>
    	        </div>
            </div>

            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12" id="sub" >
                <button class="btn waves-effect indigo waves-light" type="submit" 
                    name="signup_submit">Submit</button>
            </div>
		  </form>
		  	<?php
				 	include("php/registration.php");
				 	include("php/notif_registration.php");
 			?> 
				</div>
			</div>
		</section>
		
	</div>


	<!-- JavaScript plugins comes here -->
	<script src="js/jquery-2.1.3.min.js"></script>		<!-- jQuery -->
    <script src="js/materialize.min.js"></script>             <!-- Materialize -->
    <script src="js/script.js?v=1.1"></script>




  </body>
</html>
