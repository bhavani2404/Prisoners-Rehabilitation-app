<?php

$connect=mysql_connect("localhost","root","");
mysql_select_db("prisonersrehabilitation",$connect);

 $eusername = $_GET["eusername"];
  $id = $_GET["id"];

	 if($connect)
	 	{
			$sqlCheckUname = mysql_query("update lawyerpostquery set solution='$eusername' where id='$id'");
				if($sqlCheckUname)
				{
				echo "Success";
				}
				else
				{
				echo "failed";
				}
 		}
	else
		{
		echo "Connection Error";
		}
	

?>
