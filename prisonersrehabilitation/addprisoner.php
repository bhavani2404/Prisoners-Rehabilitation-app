<?php 

$connect=mysql_connect("localhost","root","");
mysql_select_db("prisonersrehabilitation",$connect);


 $username = $_GET['username'];

 $gender = $_GET["gender"];
 

 $epassword = $_GET["epassword"];

if($connect)
{

				$sqlCheckUname = mysql_query("SELECT * FROM  prisoner WHERE username LIKE '$username'");
				$u_name_query =  mysql_num_rows($sqlCheckUname);

				if($u_name_query > 0)
				{
					echo "User name allready used type another one";
				}
				else
				{
					

					$sql_register = mysql_query("INSERT INTO prisoner VALUES ('','$username','$gender','$epassword')");

					if($sql_register)
					{
						echo "You are registered successfully";
					}	
					else
					{
						echo "Failed to register you account";
					}
				}

}
else
{
echo "Connection Error";
}

?>
