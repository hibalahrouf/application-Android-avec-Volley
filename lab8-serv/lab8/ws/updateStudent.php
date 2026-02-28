<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include_once '../service/StudentService.php';
    $ss = new StudentService();

    $id = $_POST['id'] ?? null;
    $nom = $_POST['nom'] ?? "";
    $prenom = $_POST['prenom'] ?? "";
    $ville = $_POST['ville'] ?? "";
    $sexe = $_POST['sexe'] ?? "";

    if ($id !== null) {
        $ss->updateStudent($id, $nom, $prenom, $ville, $sexe);
    }

    header('Content-Type: application/json');
    echo json_encode($ss->findAllApi());
}
?>