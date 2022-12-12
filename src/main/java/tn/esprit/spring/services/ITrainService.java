package tn.esprit.spring.services;
import tn.esprit.spring.entities.Train;
import tn.esprit.spring.entities.Ville;

import java.util.List;

public interface ITrainService {
     Train ajouterTrain(Train t);
     void affecterTainAVoyageur(Long   idVoyageur, Ville nomGareDepart, Ville nomGareArrivee,  double heureDepart);
     int trainPlacesLibres(Ville nomGareDepart);
     List<Train> listerTrainsIndirects(Ville nomGareDepart, Ville nomGareArrivee);
     void desaffecterVoyageursTrain(Ville nomGareDepart, Ville nomGareArrivee, double heureDepart);
     void trainsEnGare();
     List<Train> recupererAll();
     void supprimerTrain(Train t);
     Train deleteSTrain(Long trainId) ;
}
