<?php

$connect=mysql_connect("localhost","root","");
mysql_select_db("prisonersrehabilitation",$connect);

				$sqlCheckUname1 = mysql_query("SELECT * FROM pdfdetails");
				$u_name_query =  mysql_num_rows($sqlCheckUname1);
				if($u_name_query > 0)
				{
					while($res=mysql_fetch_array($sqlCheckUname1))
					{
					 $tem[] =array("sb_id"=>$res['id'],"ser_id"=> $res['description'],"sname"=>$res['pathToFile'],"link"=>$res['description']);
 					 $json = json_encode($tem);
					}
					echo $json;
				}
				else{
				echo "Failed";
					}
		
	


?>