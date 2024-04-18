<?php

include 'koneksi.php';

$username = $_POST['username'];
$password = $_POST['password'];

$query = "SELECT id, password FROM user WHERE username = ?";
$stmt = mysqli_prepare($koneksi, $query);
mysqli_stmt_bind_param($stmt, 's', $username);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);

if (!$result) {
    die("Error in query: " . mysqli_error($koneksi));
}

if (mysqli_num_rows($result) == 1) {
    $row = mysqli_fetch_assoc($result);
    $storedPassword = $row['password'];

    // Hash the incoming password using SHA-256
    $hashedPassword = hash('sha256', $password);

    if ($hashedPassword === $storedPassword) {
        // Login berhasil, kirim respons dengan status "success" dan ID pengguna
        $response = array('status' => 'success', 'message' => 'Login berhasil', 'userId' => $row['id']);
        echo json_encode($response);
    } else {
        echo "Invalid username or password";
    }
} else {
    echo "Invalid username or password";
}

mysqli_close($koneksi);
?>
