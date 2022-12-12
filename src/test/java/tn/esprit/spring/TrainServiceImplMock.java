package tn.esprit.spring;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Train;
import tn.esprit.spring.entities.etatTrain;
import tn.esprit.spring.repository.TrainRepository;
import tn.esprit.spring.services.ITrainService;
import tn.esprit.spring.services.TrainServiceImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)

public class TrainServiceImplMock {
    @Mock
    TrainRepository sr;

    @InjectMocks
    ITrainService ss = new TrainServiceImpl();

    Train train1 = new Train(1, etatTrain.prevu,10);
    Train train2 = new Train(2,etatTrain.en_gare,15);

    List<Train> listTrains = new ArrayList<Train>(){
    	{
			add(new Train(3,etatTrain.annule,10));
			add(new Train(4,etatTrain.en_gare,5));
    	}
    	
	};

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
/*    @Test
    public void retrieveAllTrainsTest() {
        when(sr.findAll()).thenReturn(listTrains);
        List<Train> result = ss.recupererAll();
        Assertions.assertEquals(listTrains, result);
    }

    @Test
    public void addTrainTest() {
        Train train4 = new Train();
        train4.setIdTrain(5L);
        when(ss.ajouterTrain(train4)).thenReturn(train4);
        Train result = ss.ajouterTrain(train4);
        Assertions.assertEquals(train4, result);
    }

    
    @Test
    public void deleteTrainTest() {
        doNothing().when(sr).deleteById(1L);
        when(sr.findById(1L)).thenReturn(Optional.ofNullable(null));
         Train result = ss.deleteSTrain(1L);
        Assertions.assertNull(result);
    } */

    }
