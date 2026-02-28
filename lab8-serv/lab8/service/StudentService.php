<?php
include_once '../classes/Student.php';
include_once '../connexion/Connexion.php';
include_once '../dao/IDao.php';

class StudentService implements IDao {

    private $connexion;

    function __construct() {
        $this->connexion = new Connexion();
    }

    public function create($o) {

    $query = "INSERT INTO student (nom, prenom, ville, sexe)
              VALUES (:nom, :prenom, :ville, :sexe)";

    $stmt = $this->connexion->getConnexion()->prepare($query);

    $stmt->execute([
        ':nom'    => $o->getLastName1h(),
        ':prenom' => $o->getFirstName1h(),
        ':ville'  => $o->getCity1h(),
        ':sexe'   => $o->getGender1h()
    ]);
}
public function deleteById($id) {
    $stmt = $this->connexion->getConnexion()->prepare("DELETE FROM student WHERE id = :id");
    $stmt->execute([':id' => $id]);
}

public function updateStudent($id, $nom, $prenom, $ville, $sexe) {
    $stmt = $this->connexion->getConnexion()->prepare(
        "UPDATE student SET nom=:nom, prenom=:prenom, ville=:ville, sexe=:sexe WHERE id=:id"
    );
    $stmt->execute([
        ':nom' => $nom,
        ':prenom' => $prenom,
        ':ville' => $ville,
        ':sexe' => $sexe,
        ':id' => $id
    ]);
}
    public function delete($o) {
        
    }

    public function update($o) {
        
    }

    public function findAll() {
        $req = $this->connexion->getConnexion()->query("SELECT * FROM Student");
        return $req->fetchAll(PDO::FETCH_ASSOC);
    }

    public function findById($id) {
        
    }

    // API method
    public function findAllApi() {
        return $this->findAll();
    }
}
?>