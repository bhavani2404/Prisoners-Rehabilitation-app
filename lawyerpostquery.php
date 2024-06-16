<?php

$connect=mysql_connect("localhost","root","");
mysql_select_db("prisonersrehabilitation",$connect);

 $eusername = $_GET["eusername"];
  $pid = $_GET["pid"];

	 if($connect)
	 	{
			$sqlCheckUname = mysql_query("Insert into lawyerpostquery values('','$eusername','wait','$pid')");
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
