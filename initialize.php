<?php
   include("connection.php");
   session_start();

    $sql = "CREATE TABLE IF NOT EXISTS user (
        id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(30) NOT NULL
    )";
    $result = mysqli_query($db,$sql);

    $sql = "CREATE TABLE IF NOT EXISTS messages (
        id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        fromName VARCHAR(30) NOT NULL,
        toName VARCHAR(30) NOT NULL,
        content VARCHAR(3000) NOT NULL
        )";

    $result = mysqli_query($db,$sql);

    echo "sql run";

?>