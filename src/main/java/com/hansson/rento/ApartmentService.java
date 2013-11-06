package com.hansson.rento;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.apartments.BengtAkessonsApartments;
import com.hansson.rento.apartments.CAFastigheterApartments;
import com.hansson.rento.apartments.HeimstadenApartments;
import com.hansson.rento.apartments.KSFastigheterApartments;
import com.hansson.rento.apartments.KarlskronahemApartments;
import com.hansson.rento.apartments.MagistratusFastigheterApartments;
import com.hansson.rento.apartments.PBAApartments;
import com.hansson.rento.apartments.SBFApartments;
import com.hansson.rento.apartments.TrossoWamoApartments;
import com.hansson.rento.apartments.UtklippanApartments;
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
			add(new HeimstadenApartments());
			add(new CAFastigheterApartments());
			add(new TrossoWamoApartments());
			add(new KarlskronahemApartments());
			add(new BengtAkessonsApartments());
			add(new KSFastigheterApartments());
			add(new MagistratusFastigheterApartments());
			add(new PBAApartments());
			add(new SBFApartments());
			add(new UtklippanApartments());

		}
	};
	
	@Autowired
	private ApartmentDAO mApartmentDAO;

	@Scheduled(fixedDelayString = "3600000")
	public void updateApartmentList() {
		for(ApartmentsInterface landlord : mLandlords) {
			List<Apartment> currentApartments = landlord.getAvailableApartments();
			List<Apartment> storedApartments = mApartmentDAO.findAll(landlord.getLandlord());
			for(Apartment apartment : currentApartments) {
				if(!storedApartments.contains(apartment)) {
					mApartmentDAO.create(apartment);
				}
			}
			
			for(Apartment apartment : storedApartments) {
				if(!currentApartments.contains(apartment)) {
					mApartmentDAO.delete(apartment);
				}
			}
		}
	}
}
