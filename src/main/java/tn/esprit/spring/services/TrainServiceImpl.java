package tn.esprit.spring.services;

import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Train;
import tn.esprit.spring.entities.Ville;
import tn.esprit.spring.entities.Voyage;
import tn.esprit.spring.repository.TrainRepository;
import tn.esprit.spring.repository.VoyageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.repository.VoyageurRepository;

import tn.esprit.spring.entities.Voyageur;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.scheduling.annotation.Scheduled;

@Service
public class TrainServiceImpl implements ITrainService {


    @Autowired
    VoyageurRepository voyageurRepository;


    @Autowired
    TrainRepository trainRepository;

    @Autowired
    VoyageRepository voyageRepository;


    public Train ajouterTrain(Train t) {

        return trainRepository.save(t);
    }

    public void supprimerTrain(Train t) {
        trainRepository.delete(t);
    }
    
    @Override
	public Train deleteSTrain(Long trainId) {
    	
    	trainRepository.deleteById(trainId);
		return trainRepository.findById(trainId).orElse(null);

	}

    public int trainPlacesLibres(Ville nomGareDepart) {
        int cpt = 0;
        int occ = 1;
        List<Voyage> listvoyage = (List<Voyage>) voyageRepository.findAll();
        for (int i = 0; i < listvoyage.size(); i++) {
                cpt = cpt + listvoyage.get(i).getTrain().getNbPlaceLibre();
                occ = occ + 1; 
            
        
        }
        
        return cpt / occ;
            
    }


    public List<Train> listerTrainsIndirects(Ville nomGareDepart, Ville nomGareArrivee) {

        List<Train> lestrainsRes = new ArrayList<>();
        List<Voyage> lesvoyage = new ArrayList<>();
        for (int i = 0; i < lesvoyage.size(); i++) {
            if (lesvoyage.get(i).getGareDepart() == nomGareDepart) {
                for (int j = 0; j < lesvoyage.size(); j++) {
                        lestrainsRes.add(lesvoyage.get(i).getTrain());
                        lestrainsRes.add(lesvoyage.get(j).getTrain());
                }
            }
        }


        return lestrainsRes;
        
    }


    @Transactional
    public void affecterTainAVoyageur(Long idVoyageur, Ville nomGareDepart, Ville nomGareArrivee, double heureDepart) {


        Voyageur c = voyageurRepository.findById(idVoyageur).get();
        List<Voyage> lesvoyages = new ArrayList<>();
        lesvoyages = voyageRepository.rechercheVoyage(nomGareDepart, nomGareDepart, heureDepart);
        for (int i = 0; i < lesvoyages.size(); i++) {
            if (lesvoyages.get(i).getTrain().getNbPlaceLibre() != 0) {
                lesvoyages.get(i).getMesVoyageurs().add(c);
                lesvoyages.get(i).getTrain().setNbPlaceLibre(lesvoyages.get(i).getTrain().getNbPlaceLibre() - 1);
            }else
            		voyageRepository.save(lesvoyages.get(i));
        }
    }

    @Override
    public void desaffecterVoyageursTrain(Ville nomGareDepart, Ville nomGareArrivee, double heureDepart) {
        List<Voyage> lesvoyages = new ArrayList<>();
        for (int i = 0; i < lesvoyages.size(); i++) {
            lesvoyages.get(i).getTrain().setNbPlaceLibre(lesvoyages.get(i).getTrain().getNbPlaceLibre() + 1);
            voyageRepository.save(lesvoyages.get(i));
            trainRepository.save(lesvoyages.get(i).getTrain());
        }
    }

    @Scheduled(fixedRate = 2000)
    public void trainsEnGare() {
        List<Voyage> lesvoyages = new ArrayList<>();
        lesvoyages = (List<Voyage>) voyageRepository.findAll();
        System.out.println("taille" + lesvoyages.size());
        for (int i = 0; i < lesvoyages.size(); i++) {
            lesvoyages.get(i).getTrain().getCodeTrain();
        }
    }

    @Override
    public List<Train> recupererAll() {
        return (List<Train>) trainRepository.findAll();
    }


}

    
