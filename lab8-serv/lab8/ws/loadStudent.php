<?php
include_once '../service/StudentService.php';
$es = new StudentService();
header('Content-Type: application/json');
echo json_encode($es->findAllApi());
?>