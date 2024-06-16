<?php

$connect=mysql_connect("localhost","root","");
mysql_select_db("prisonersrehabilitation",$connect);

 $eusername = $_GET["username"];
 $epassword = $_GET["password"];
	 if($connect)
	 	{
			$sqlCheckUname = mysql_query("SELECT * FROM adminlogin WHERE username='$eusername' && password ='$epassword'");
				$u_name_query =  mysql_num_rows($sqlCheckUname);
				if($u_name_query == 1)
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
