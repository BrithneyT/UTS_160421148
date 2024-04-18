<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update User Profile</title>
</head>
<body>
    <h2>Update User Profile</h2>
    <form method="POST" action="api-profile.php">
        <label for="id">User ID:</label><br>
        <input type="text" id="id" name="id" required><br><br>
        
        <label for="namaDepan">First Name:</label><br>
        <input type="text" id="namaDepan" name="namaDepan" required><br><br>

        <label for="namaBelakang">Last Name:</label><br>
        <input type="text" id="namaBelakang" name="namaBelakang" required><br><br>

        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Update Profile">
    </form>
</body>
</html>
