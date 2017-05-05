<?php
ini_set('display_errors', '1');
error_reporting(E_ALL);

	// Connect to the Database
	$dsn = 'mysql:host=cssgate.insttech.washington.edu;dbname=d1durham';
 	$username = 'd1durham';
	$password = 'VudPyirf';


   try{
      $db = new PDO($dsn, $username, $password);
      $select_sql = 'SELECT * FROM Events';
      $user_query = $db->query($select_sql);
      $events = $user_query->fetchAll(PDO::FETCH_ASSOC);
      if($events){
         echo json_encode($events);
      }
      $db = null;
   } catch (PDOException $e) {
      $error_message = $e->getMessage();
      echo 'There was an error connecting to the database.';
      echo $error_message;
   exit();
   }


?>
