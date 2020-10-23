<!DOCTYPE HTML>
<?php
	session_start();
	

	$username = "admin";
	$password = "12345";
	
	if(isset($_SESSION['loggedIn']) && $_SESSION['loggedIn'] == true){
		header("Location: )//add the name of the home page
	}
	
	if(isset($_POST['username']) && isset($_POST['password'])){
		if($_POST['username'] == $username && $_POST['password'] == $password){
			$_SESSION['loggedIn'] = true;
			header("Location: )//add the name of the home page
		}
	}
?>

<html>
	<body>
		<form method="post" action="secure.php">
			Username:<br/>
			<input type="text" name="Username"><br/>
			Password:<br/>
			<input type="password" name="Password"><br/>
			<input type="submit" value="Submit!"
		</form>
	</body>
</html>

//This is the password file. Very basic, so if you want to add color or a picture in the background, do that in the html section
