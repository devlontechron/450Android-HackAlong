<?php
ini_set('display_errors', '1');
error_reporting(E_ALL);

	// Connect to the Database
	$dsn = 'mysql:host=cssgate.insttech.washington.edu;dbname=d1durham';
 	$username = 'd1durham';
	$password = 'VudPyirf';
   $userEmail = $_GET['UE'];
   $userPW = $_GET['UPW'];
   $success = 1;


   try{
      $db = new PDO($dsn, $username, $password);
      $select_sql = "INSERT INTO User (UEmail, UPW) VALUES ('$userEmail','$userPW')";
      $user_query = $db->query($select_sql);
      $events = $user_query->fetchAll(PDO::FETCH_ASSOC);

      if($events){
         echo json_encode($events);
      }else{
         echo json_encode($success);
      }
      $db = null;
   } catch (PDOException $e) {
      $error_message = $e->getMessage();
      echo 'There was an error connecting to the database.';
      echo $error_message;
   exit();
   }


?>
