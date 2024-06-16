<?php


$connect=mysql_connect("localhost","root","");
mysql_select_db("prisonersrehabilitation",$connect);

 $fpth = $_REQUEST['fpth'];
 $apname = $_REQUEST['apname'];
  $ttwork = $_REQUEST['ttwork'];

 $result = array();
	 if($connect)
	 	{
			$sqlCheckUname = mysql_query("Update pdfdetails set description='$apname' WHERE id ='$fpth'");

				if($sqlCheckUname)
				{
			array_push($result,array("name"=>"Success","status"=>"Success"));
			echo json_encode(array("result"=>$result));
				}
				else
				{
					array_push($result,array("name"=>"Failed","status"=>"Failed"));
			echo json_encode(array("result"=>$result));
				}
 		}
	else
		{
		echo "Connection Error";
		}
	

?>

