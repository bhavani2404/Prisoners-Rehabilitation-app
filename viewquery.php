<?php 

$connect=mysql_connect("localhost","root","");
mysql_select_db("prisonersrehabilitation",$connect);

$sql = mysql_query("SELECT * FROM lawyerpostquery");


if ($sql) {
while($res=mysql_fetch_array($sql))
{

$tem[]=	array("name"=>$res['query'],"dept"=>$res['id'],"time"=>$res['id'],"place"=>$res['solution'],"date"=>$res['id']);
 $json = json_encode($tem);
}
}
else {
 echo "0 results";
}

 echo $json;


?>