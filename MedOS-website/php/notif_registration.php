<?php
        if($success){
            echo
            '<div class="overlay"><div class="msg success">'.
                    '<div class="msg_inner success_inner">'.
                        '<h2>Bienvenue '.$fname.' !</h2>'.
                    '</div>'.
            '</div></div>';
        }

        if($fail){
            $msg_header = $fail=="email" ? "Email déjà utilisé :(" : "Valeurs manquantes :(";
            $msg = $fail=="email" ? "Soit vous êtes déjà inscrit, soit quelqu'un d'autre a utilisé votre e-mail." : "Veuillez vérifier les valeurs entrées.";

            echo
            '<div class="overlay"><div class="msg fail">'.
                    '<div class="msg_inner fail_inner">'.
                        '<h2>'.$msg_header.'</h2>'.
                        '<p>'.$msg.'</p>'.
                    '</div>'.
            '</div></div>';
        }
    ?>