<?php
include 'models/whitepages_lib.php';
include 'models/person.php';

if (isset($_POST['submit'])) {
    if (!empty($_POST['first_name']) && !empty($_POST['last_name']) && !empty($_POST['where'])) {
        $param = array('first_name' => trim($_POST['first_name']), 'last_name' => trim($_POST['last_name']), 'address' => trim($_POST['where']));
        $whitepages_obj = new Models\WhitepagesLib();
        $whitepages_obj->find_person($param);

        if (!empty($whitepages_obj->response['error'])) {
            $error = $whitepages_obj->response['error']['message'];
        } elseif (!empty($whitepages_obj->response['results'])) {
            $person_obj = new Models\Person($whitepages_obj->response);
            $person_obj->formattedResult();
            $result_data = $person_obj->resultData;
        } else {
            $error = 'No records found';
        }
    } else {
        if(empty($_POST['first_name']))
            $error = 'Please enter first name';
        if(empty($_POST['last_name']))
            $error = 'Please enter last name';
        if(empty($_POST['where']))
            $error = 'Please enter address';
    }
}

