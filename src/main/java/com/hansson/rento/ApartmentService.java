package com.hansson.rento;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.apartments.blekinge.TrossoWamoFastigheter;
import com.hansson.rento.apartments.blekinge.karlshamn.Karlshamnsbostader;
import com.hansson.rento.apartments.blekinge.karlshamn.KjellsonsSkogOchFastighetsForvaltning;
import com.hansson.rento.apartments.blekinge.karlshamn.StrandbergsFastigheter;
import com.hansson.rento.apartments.blekinge.karlskrona.BengtAkessonFastigheter;
import com.hansson.rento.apartments.blekinge.karlskrona.HansAkessonFastigheter;
import com.hansson.rento.apartments.blekinge.karlskrona.KSFastigheter;
import com.hansson.rento.apartments.blekinge.karlskrona.Karlskronahem;
import com.hansson.rento.apartments.blekinge.karlskrona.KarlskronahemStudent;
import com.hansson.rento.apartments.blekinge.karlskrona.LindebergFastigheter;
import com.hansson.rento.apartments.blekinge.karlskrona.MagistratusFastigheter;
import com.hansson.rento.apartments.blekinge.karlskrona.PBAStudent;
import com.hansson.rento.apartments.blekinge.karlskrona.Utklippan;
import com.hansson.rento.apartments.multiple.CAFastigheter;
import com.hansson.rento.apartments.multiple.HSBSydost;
import com.hansson.rento.apartments.multiple.Heimstaden;
import com.hansson.rento.apartments.multiple.Krebo;
import com.hansson.rento.apartments.multiple.PBAFastigheter;
import com.hansson.rento.apartments.multiple.SvenskaBostadsfonden;
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
		// Add new implementations of the ApartmentsInterface here to include
		// them in the scan loop
		{
			//Karlshamn
//			add(new Karlshamnsbostader());
			add(new KjellsonsSkogOchFastighetsForvaltning());
			add(new StrandbergsFastigheter());
			//Karlskrona
			add(new BengtAkessonFastigheter());
			add(new HansAkessonFastigheter());
			add(new Karlskronahem());
			add(new KarlskronahemStudent());
			add(new KSFastigheter());
			add(new LindebergFastigheter());
			add(new MagistratusFastigheter());
			add(new PBAStudent());
			add(new TrossoWamoFastigheter());
			add(new Utklippan());
			//Multiple
			add(new CAFastigheter());
			add(new Heimstaden());
			add(new HSBSydost());
			add(new Krebo());
			add(new PBAFastigheter());
			add(new SvenskaBostadsfonden());
		}
	};

	@Autowired
	private ApartmentDAO mApartmentDAO;

	@Scheduled(fixedDelayString = "14400000")
	// Every 4 hours
	public void updateApartmentList() {
		for (ApartmentsInterface landlord : mLandlords) {
			try {
				List<Apartment> currentApartments = landlord.getAvailableApartments();
				List<Apartment> storedApartments = mApartmentDAO.findAllByLandlord(landlord.getLandlord());

				if (currentApartments == null) {
					currentApartments = new LinkedList<Apartment>();
				}
				if (storedApartments == null) {
					storedApartments = new LinkedList<Apartment>();
				}

				for (Apartment apartment : currentApartments) {
					if (!storedApartments.contains(apartment)) {
						mApartmentDAO.create(apartment);
						mLog.info("Created: " + apartment);
					}
				}

				for (Apartment apartment : storedApartments) {
					if (!currentApartments.contains(apartment)) {
						mApartmentDAO.delete(apartment);
						mLog.info("Deleted: " + apartment);
					}
				}
			} catch (Exception e) {
				mLog.error("Error at: " + landlord.getLandlord());
				e.printStackTrace();
			}
		}

	}
}
