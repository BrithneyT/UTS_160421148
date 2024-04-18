<?php

include 'koneksi.php'; 

$username = $_POST['username'];
$password = $_POST['password'];
$namaDepan = $_POST['namaDepan'];
$namaBelakang = $_POST['namaBelakang'];
$email = $_POST['email'];


$queryRegister = "SELECT * FROM user WHERE username = ?";
$stmt = mysqli_prepare($koneksi, $queryRegister);
mysqli_stmt_bind_param($stmt, 's', $username);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);

if (!$result) {
    die("Error in query: " . mysqli_error($koneksi));
}

$count = mysqli_num_rows($result);

if (!empty($username) && !empty($password) && !empty($namaDepan) && !empty($email)) {
    if ($count == 0) {
        // Hash password
        $hashedPassword = hash('sha256', $password);
        
        // Insert new user
        $regis = "INSERT INTO user (username, password, namaDepan, namaBelakang, email)
                VALUES (?, ?, ?, ?, ?)";
        
        $stmt = mysqli_prepare($koneksi, $regis);
        mysqli_stmt_bind_param($stmt, 'sssss', $username, $hashedPassword, $namaDepan, $namaBelakang, $email);
        
        if (mysqli_stmt_execute($stmt)) {
            $response = array('status' => 'success', 'message' => 'Registrasi berhasil');
            echo json_encode($response);
        } else {
            echo "Error: " . mysqli_error($koneksi);
        }
        
        
        mysqli_stmt_close($stmt);
    } else {
        echo "Username already exists";
    }
} else {
    echo "Please fill all the fields";
}

mysqli_close($koneksi);
?>