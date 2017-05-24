<?php
ini_set('display_errors', '1');
error_reporting(E_ALL);

	// Connect to the Database
	$dsn = 'mysql:host=cssgate.insttech.washington.edu;dbname=d1durham';
 	$username = 'd1durham';
	$password = 'VudPyirf';
   $userEmail = $_GET['UE'];
   $userName = $_GET['UN'];
   $userLoc = $_GET['UL'];
   $userAge = $_GET['UA'];
   $userEvents = $_GET['UEv'];
   $userBio = $_GET['UB'];
   $userTag = $_GET['UT'];
   $userEvents = $_GET['UEv'];

   $success = 1;


   try{
      $db = new PDO($dsn, $username, $password);
      $select_sql = "UPDATE User SET UName = '$userName', ULocation = '$userLoc',
      UAge = '$userAge',UEvents = '$userEvents', UBio = '$userBio', UTag = '$userTag' WHERE UEmail = '$userEmail'";
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
