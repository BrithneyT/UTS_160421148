<?php

include 'koneksi.php'; // Include the file containing the database connection

// Sanitize user input to prevent SQL injection (recommended)
$id = filter_var($_POST['id'], FILTER_SANITIZE_NUMBER_INT);
$namaDepan = filter_var($_POST['namaDepan'], FILTER_SANITIZE_STRING);
$namaBelakang = filter_var($_POST['namaBelakang'], FILTER_SANITIZE_STRING);
$password = filter_var($_POST['password'], FILTER_SANITIZE_STRING);

// If ID is not provided or invalid, return an error
if (!$id) {
    $response = array(
        'status' => 'error',
        'message' => 'Invalid user ID'
    );
    header('Content-Type: application/json');
    echo json_encode($response);
    exit();
}

// Hash the password using SHA-256
$hashedPassword = hash('sha256', $password);

// Query to update user data based on sanitized ID and new values
$sql = "UPDATE user SET namaDepan = ?, namaBelakang = ?, password = ? WHERE id = ?";
$stmt = mysqli_prepare($koneksi, $sql);
mysqli_stmt_bind_param($stmt, 'sssi', $namaDepan, $namaBelakang, $hashedPassword, $id); // Bind sanitized values

if (mysqli_stmt_execute($stmt)) {
    $response = array(
        'status' => 'success',
        'message' => 'Profile updated successfully'
    );
} else {
    $response = array(
        'status' => 'error',
        'message' => 'Failed to update profile: ' . mysqli_error($koneksi)
    );
}

// Set JSON response header and encode the response
header('Content-Type: application/json');
echo json_encode($response);

// Close statement
mysqli_stmt_close($stmt);

// Close database connection
mysqli_close($koneksi);

?>
