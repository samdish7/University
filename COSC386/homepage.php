<?php
	session_start();

	if(!isset($_SESSION['loggedIn']) || $_SESSION['loggedIn'] == false){
		header("Location: secure.php");
	}

?>

//Add this to the beginning of our homepage file
