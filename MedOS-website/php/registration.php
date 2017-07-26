<?php
require_once("dbConnex.php");

    $success = $fail = 0;
    $lname = $fname = $email = $phone = $birth = $gender = $password = $cpassword = " ";
    if(!empty($_POST)){
        if(isset($_POST["signup_submit"])){
            $P = $_POST;
            $fname = $P["fname"];
            $lname = $P["lname"];
            $email = $P["email"];
            $phone = $P["phone"];
            $birth = $P["birth"];
            $gender = $P["gender"];
            $password = $P["password"];
            $cpassword = $P["cpassword"];

            if($cpassword==$password){

                $passhash=md5($password);

                $query = $bdd->prepare('INSERT INTO `patient` (`fnamep`, `lnamep`, `gender`,  
                    `date_naissp`, `telp`, `emailp`, `pswdp`) VALUES (?,?,?,?,?,?,?)');

                $query->execute(array($fname, $lname, $gender, $birth, $phone, $email, $passhash));

                if ($query->rowCount()) {
                    $success = 1;
                }
                else{
                    $fail=1;
                }

            }

        }
    }