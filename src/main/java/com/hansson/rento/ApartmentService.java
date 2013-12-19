package com.hansson.rento;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.apartments.multiple.CAFastigheter;
import com.hansson.rento.apartments.multiple.Heimstaden;
import com.hansson.rento.apartments.multiple.Krebo;
import com.hansson.rento.apartments.multiple.PBAFastigheter;
import com.hansson.rento.apartments.multiple.SvenskaBostadsfonden;
import com.hansson.rento.apartments.karlskrona.BengtAkessonFastigheter;
import com.hansson.rento.apartments.karlskrona.HansAkessonFastigheter;
import com.hansson.rento.apartments.karlskrona.KSFastigheter;
import com.hansson.rento.apartments.karlskrona.Karlskronahem;
import com.hansson.rento.apartments.karlskrona.KarlskronahemStudent;
import com.hansson.rento.apartments.karlskrona.LindebergFastigheter;
import com.hansson.rento.apartments.karlskrona.MagistratusFastigheter;
import com.hansson.rento.apartments.karlskrona.PBAStudent;
import com.hansson.rento.apartments.karlskrona.TrossoWamoFastigheter;
import com.hansson.rento.apartments.karlskrona.Utklippan;
import com.hansson.rento.dao.ApartmentDAO;
import com.hansson.rento.entities.Apartment;

@Service
public class ApartmentService {
	
	// * ********CAUTION********
	// * Entering html scraping area.. prepare yourself for some nasty stuff!
	// * ********CAUTION********
	private static final Logger mLog = LoggerFactory.getLogger("rento");
	private List<ApartmentsInterface> mLandlords = new LinkedList<ApartmentsInterface>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2411798345463453006L;
		// Add new implementations of the ApartmentsInterface here to include them in the scan loop
		{
			add(new Karlskronahem());
			add(new KarlskronahemStudent());
			add(new Krebo());
			add(new PBAStudent());
			add(new LindebergFastigheter());
			add(new HansAkessonFastigheter());
			add(new BengtAkessonFastigheter()); 
			add(new Heimstaden());
			add(new CAFastigheter());
			add(new TrossoWamoFastigheter());
			add(new KSFastigheter());
			add(new MagistratusFastigheter());
			add(new PBAFastigheter());
			add(new SvenskaBostadsfonden());
			add(new Utklippan());
		}
	};
	
	@Autowired
	private ApartmentDAO mApartmentDAO;

	@Scheduled(fixedDelayString = "14400000") //Every 4 hours
	public void updateApartmentList() {
		for(ApartmentsInterface landlord : mLandlords) {
			List<Apartment> currentApartments = landlord.getAvailableApartments();
			List<Apartment> storedApartments = mApartmentDAO.findAllByLandlord(landlord.getLandlord());
			
			if(currentApartments == null) {
				currentApartments = new LinkedList<Apartment>();
			}
			if(storedApartments == null) {
				storedApartments = new LinkedList<Apartment>();
			}
			
			for(Apartment apartment : currentApartments) {
				if(!storedApartments.contains(apartment)) {
					mApartmentDAO.create(apartment);
					mLog.info("Created: " + apartment);
				}
			}
			
			for(Apartment apartment : storedApartments) {
				if(!currentApartments.contains(apartment)) {
					mApartmentDAO.delete(apartment);
					mLog.info("Deleted: " + apartment);
				}
			}
		}
	}
}
