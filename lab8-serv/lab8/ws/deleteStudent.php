<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include_once '../service/StudentService.php';
    $ss = new StudentService();

    $id = $_POST['id'] ?? null;
    if ($id !== null) {
        $ss->deleteById($id);
    }

    header('Content-Type: application/json');
    echo json_encode($ss->findAllApi());
}
?>