<?php
 
/*
 * Following code will create a new game row
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['name']) && isset($_POST['roomcode']) ) {
 
    $name = $_POST['name'];
    $roomcode = $_POST['roomcode'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
    
    // get game info from roomcode
    $result = mysql_query("SELECT *FROM ActiveGames WHERE Room_Code = $roomcode");

    if (!empty($result)) { 
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
            $result = mysql_fetch_array($result);
            for ($i = 1; $i <13; $i++ ) {
               if($result["P".$i."_Name"] == ""){
                  $result = mysql_query("UPDATE Active_Games SET P".$i."_Name = $name WHERE Room_Code = $roomcode");
                  if ($result) {
                     // successfully updated game
                     $response["success"] = 1;
                     $response["message"] = "Product successfully created.";
                     $response["playernumber"] = $i;
                     // echoing JSON response
                     echo json_encode($response);
                  } else {
                     // failed to update row
                     $response["success"] = 0;
                     $response["message"] = "Oops! An error occurred.";
 
                     // echoing JSON response
                     echo json_encode($response);
                  }
                 $i = 100;   
               }   
            } 
        } else {
            // no roomcode found
            $response["success"] = 0;
            $response["message"] = "No such roomcode found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no roomcode found
        $response["success"] = 0;
        $response["message"] = "No such roomcode game found";
 
        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>
