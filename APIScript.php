<?php
ini_set('display_errors', '1');
ini_set("allow_url_fopen", 1);
error_reporting(E_ALL);

	// Connect to the Database
	$dsn = 'mysql:host=cssgate.insttech.washington.edu;dbname=d1durham';
 	$username = 'd1durham';
	$password = 'VudPyirf';

   $json = file_get_contents('http://www.hackathonwatch.com/api//hackathons/coming.json?page=1');
   $obj = json_decode($json);
   $urlPoster=array();
   $id = 9000;
foreach ($obj as $value) {
   $id++;
   $name = $value->name;
   $url = $value->public_url;
   $desc = mysql_real_escape_string(substr($value->description, 0, 499));
   $address = substr($value->full_address,0,499);
   $date = date("Y, F j, g:i a" ,$value->start_timestamp);


   try{
      $db = new PDO($dsn, $username, $password);
      $select_sql = "INSERT INTO Events (EID, EName, ELink, ELocation,EDate,EDisc) VALUES ('$id','$name','$url','$address','$date','$desc')";
      //$select_sql = "INSERT INTO Events (EID, EName, ELink, ELocation, EDate, EDisc) VALUES ('$id','$name','$url','$address','$date','$desc')";

      $user_query = $db->query($select_sql);
      $users = $user_query->fetchAll(PDO::FETCH_ASSOC);

   } catch (PDOException $e) {
      $error_message = $e->getMessage();
      echo 'There was an error connecting to the database.';
      echo $error_message;
   exit();
   }
}

?>
