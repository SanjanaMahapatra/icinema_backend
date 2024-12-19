package com.infy.seating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.seating.entity.Seating;


public interface SeatingRepository extends CrudRepository<Seating,Integer>{
	
	@Query("SELECT s FROM Seating s WHERE s.theatreId=?1 AND s.showId=?2 AND s.status=AVAILABLE")
	public List<Seating> getAvailableSeatingList(Integer theatreId, Integer showId);
	
	@Query("SELECT s FROM Seating s WHERE s.theatreId=?1 AND s.showId=?2 AND s.status=BOOKED")
	public List<Seating> getBookedSeatingList(Integer theatreId, Integer showId);
	
	public List<Seating> findByTheatreIdAndShowId(Integer theatreId, Integer showId);

}
