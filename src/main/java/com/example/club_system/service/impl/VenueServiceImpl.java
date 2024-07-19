package com.example.club_system.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.entity.Venue;
import com.example.club_system.repository.VenueDao;
import com.example.club_system.service.ifs.VenueService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.VenueCreateOrUpdateReq;
import com.example.club_system.vo.VenueSearchReq;
import com.example.club_system.vo.VenueSearchRes;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	private VenueDao venueDao;

	@Override
	public BasicRes createOrUpdate(VenueCreateOrUpdateReq req) {
		Optional<Venue> venueOpt = venueDao.findById(req.getId());
		if (venueOpt.isPresent()) {
			Venue venue = venueOpt.get();
			if (StringUtils.hasText(venue.getUser())) {
				if (StringUtils.hasText(req.getUser())) {
					return new BasicRes(ResMessage.VENUE_IS_RENTED.getCode(), ResMessage.VENUE_IS_RENTED.getMessage());
				}
			}
			// 更新現有場地的user
			venue.setUser(req.getUser());
			venueDao.save(venue);
		} else {
			// 創建新場地
			Venue newVenue = new Venue(req.getId(), req.getUser());
			venueDao.save(newVenue);
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public VenueSearchRes search(VenueSearchReq req) {
		String id = req.getId();

		if (!StringUtils.hasText(id)) {
			id = "";
		}

		List<Venue> venues = venueDao.findByIdContaining(id);
		return new VenueSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), venues);

	}
}
