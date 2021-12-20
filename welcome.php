<html">

    <head>
        <title>Welcome </title>
        <link rel="stylesheet" type="text/css" href="style.php" />
    </head>

    <body>
        <h1>Welcome <?php include('session.php');
                    echo $login_session; ?></h1>
        <h2><a href="logout.php">Sign Out</a></h2>



    </body>


    <?php

    $sql = "SELECT name FROM user WHERE NOT name = '$login_session' ";
    $result = mysqli_query($db, $sql);


    if (isset($_POST['send'])) {

        $sql2 = "INSERT INTO `messages`(`id`, `fromName`, `toName`, `content`) 
        VALUES ('DEFAULT','$login_session','$_POST[toname]','$_POST[content]');";

        $result2 = mysqli_query($db, $sql2);
    }

    if (isset($_POST['show'])) {

        $sql3 = "SELECT * FROM `messages` WHERE `fromName` = '$_POST[toname]' AND `toName` = '$login_session'
         OR `fromName` = '$login_session' AND `toName` = '$_POST[toname]' ORDER BY id;";

        $result3 = mysqli_query($db, $sql3);
        echo "<table>";
        while ($row = mysqli_fetch_assoc($result3)) {
            echo "<tr>";

            $className = "";

            if ($row['fromName'] == $login_session)
                $className = 'credit';
            else {
                $className = 'blantershow-chat';
            }
            echo "<td>";
            echo "<div class=$className>" . $row['content'] . " </div>";
            echo "</td>";

            echo "</tr>";
        }
        echo "</table>";
        $now = time();
    }




    echo  "show chat with: " . "<form action = '' method = 'post'>" . "<select name='toname'>";
    while ($row = mysqli_fetch_array($result)) {
        echo "<option name=usernames value='" . $row['name'] . "'>" . $row['name'] . "</option>";
    }
    echo " </select> ";

    ?>
    <input type="submit" name='show' value=" Show " />
    <br></br>
    <textarea type="input" id="content" name="content" rows="4" cols="50">Enter your message here.</textarea>


    <br></br>
    <input type="submit" name='send' value=" Send " />
    </form>

    </html>