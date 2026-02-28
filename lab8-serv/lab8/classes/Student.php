<?php
class Student {

    private $id;
    private $lastName1h;
    private $firstName1h;
    private $city1h;
    private $gender1h;

    public function __construct($id, $lastName1h, $firstName1h, $city1h, $gender1h) {
        $this->id = $id;
        $this->lastName1h = $lastName1h;
        $this->firstName1h = $firstName1h;
        $this->city1h = $city1h;
        $this->gender1h = $gender1h;
    }

    public function getId() {
        return $this->id;
    }

    public function getLastName1h() {
        return $this->lastName1h;
    }

    public function getFirstName1h() {
        return $this->firstName1h;
    }

    public function getCity1h() {
        return $this->city1h;
    }

    public function getGender1h() {
        return $this->gender1h;
    }
}
?>