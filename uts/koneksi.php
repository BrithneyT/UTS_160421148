<?php

$hostname = "localhost";
$username = "root";
$password = "";
$dbName = "hobby";

$koneksi = mysqli_connect($hostname,$username,$password,$dbName);

if (!$koneksi){
    echo "koneksi gagal";
}
?>