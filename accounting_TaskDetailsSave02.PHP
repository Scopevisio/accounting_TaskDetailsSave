<?php
    $url = "https://appload.scopevisio.com/api/soap/accounting/accounting.TaskDetailsSave";
    $customer = "1000001";
    $user = "test_user@scopevisio.com";
    $pass = "test_password";
    $organisation = "Scopevisio Demo AG";

    $client = new SoapClient(NULL,
                             array(
                               'trace'    => true,
                               'location' => $url,
                               'uri'      => "http://www.scopevisio.com/")
                             );
    $params = '
        <authn>
            <customer>' . $customer . '</customer>
            <user>' . $user . '</user>
            <pass>' . $pass . '</pass>
            <language>de_DE</language>
            <organisation>' . $organisation . '</organisation>
        </authn>
        <args>
            <id>0</id>
            <mode>new</mode>
            <data>{\"topic\":\"Testaufgabe\",\"tags\":\"Testaufgabe\",\"description\":\"Diese Testaufgabe wurde über den Service erzeugt\",\"status\":\"0\",\"dueDateTs\":\"1396656000000\"}</data>
        </args>';

    // request
    $response = NULL;
    try {
        $response = $client->req(new SoapVar($params,XSD_ANYXML));
        echo "<b style=\"font-size:small\">Request</b>";
        echo "<pre>" . print_r(htmlentities($client->__getLastRequest()), true) . "</pre>";
    } catch (SoapFault $exception) {
        echo "Exception: " . $exception;
        exit;
    }

    // response
    echo "<br/><b style=\"font-size:small\">Response</b>";
    echo "<pre>" . print_r(htmlentities($client->__getLastResponse()), true) . "</pre>";
?>
