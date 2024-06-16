<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){
  	
  	
$connect=mysql_connect("localhost","root","");
mysql_select_db("prisonersrehabilitation",$connect);
  	
  

        $originalImgName= $_FILES['filename']['name'];
		$originalImgName=date('YmdHis').$originalImgName;
        $tempName= $_FILES['filename']['tmp_name'];
        $folder="uploadedFiles/";
        $url = "".$originalImgName; //update path as per your directory structure 
        
        if(move_uploaded_file($tempName,$folder.$originalImgName)){
                $query = "INSERT INTO pdfdetails (pathToFile) VALUES ('$url')";
                if(mysql_query($query)){
                
                	 $query= "SELECT * FROM pdfdetails WHERE pathToFile='$url'";
	                 $result= mysql_query($query);
	                 $emparray = array();
	                     if(mysql_num_rows($result) > 0){  
	                     while ($row = mysql_fetch_assoc($result)) {
                                     $emparray[] = $row;
                                   }
                                   echo json_encode(array( "status" => "true","message" => "Successfully file added!" , "data" => $emparray) );
                                   
	                     }else{
	                     		echo json_encode(array( "status" => "false","message" => "Failed!1") );
	                     }
			   
                }else{
                	echo json_encode(array( "status" => "false","message" => "Failed!") );
                }
        	//echo "moved to ".$url;
        }else{
        	echo json_encode(array( "status" => "false","message" => "Failed!") );
        }
  }
?>