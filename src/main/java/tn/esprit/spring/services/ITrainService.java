package tn.esprit.spring.services;
import tn.esprit.spring.entities.Train;
import tn.esprit.spring.entities.Ville;

import java.util.ArrayList;
import java.util.List;

public interface ITrainService {
     Train ajouterTrain(Train t);
     void affecterTainAVoyageur(Long   idVoyageur, Ville nomGareDepart, Ville nomGareArrivee,  double heureDepart);
     int TrainPlacesLibres(Ville nomGareDepart);
     List<Train> ListerTrainsIndirects(Ville nomGareDepart, Ville nomGareArrivee);
     void DesaffecterVoyageursTrain(Ville nomGareDepart, Ville nomGareArrivee, double heureDepart);
     void TrainsEnGare();
     List<Train> recupererAll();
     void supprimerTrain(Train t);
     Train deleteSTrain(Long trainId) ;
}
