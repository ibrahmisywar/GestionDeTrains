package tn.esprit.spring.repository;


import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Train;


public interface TrainRepository extends CrudRepository<Train, Long> {


}
