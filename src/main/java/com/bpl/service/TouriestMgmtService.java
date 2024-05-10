package com.bpl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.entity.Touriest;
import com.bpl.error.TouriestNotFoundException;
import com.bpl.repository.ITouriestRepository;
@Service("touriestService")
public class TouriestMgmtService implements ITouriestMgmtService {

	@Autowired
	private ITouriestRepository touriestRepo;
	@Override
	public String registerTouriest(Touriest touriest) {
		Integer idVal=touriestRepo.save(touriest).getTid();
		return "Touriest are registered with id value"+idVal;
	}
	@Override
	public List<Touriest> showTouriest() {
		List<Touriest> list=touriestRepo.findAll();
	//	list.sort((t1,t2)->t1.getTid().compareTo(t2.getTid()));
		list.sort((t1,t2)->t1.getName().compareTo(t2.getName()));
		return list;
	}
	@Override
	public List<Touriest> showTouriestByBudgetRange(double start, double end) {
		
		return touriestRepo.showTouriestByBudget(start, end);
	}
	@Override
	public Touriest showTouriestById(int id) throws TouriestNotFoundException {
		
		return touriestRepo.findById(id).orElseThrow(()->new TouriestNotFoundException(id+"Touriest not Found"));
	}
	@Override
	public String modifyTouriest(Touriest touriest) {
		Optional<Touriest> opt=touriestRepo.findById(touriest.getTid());
		if(opt.isPresent()) {
		   touriestRepo.save(touriest);
		   return "Touriest Data Updated with id Value"+touriest.getTid();
		}
		return "Touriest not  found for updation with id "+touriest.getTid();
	}
	@Override
	public String updateTouriestBudgetById(int id, double hikePercentage) {
		Optional<Touriest> opt=touriestRepo.findById(id);
		if(opt.isPresent()) {
		Touriest touriest=opt.get();
		touriest.setBudget(touriest.getBudget()+touriest.getBudget()*hikePercentage/100.0f);
		touriestRepo.save(touriest);
		return "Touriest updated with id value"+id;
	}
    return "Touriest not found with id value"+id; 
	}
	@Override
	public String removeTouriestById(int id) {
		Optional<Touriest> opt=touriestRepo.findById(id);
		if(opt.isPresent()) {
			touriestRepo.deleteById(id);
			return "delete touriest with id"+id;
		}
		return "Not found touriest for delete"+id;
	}
	@Override
	public String deleteTouriestByPackageType(String type) {
		
		int count= touriestRepo.deleteTouriestByPackageType(type);
		return count+"Numbers of touriest are deleted";
	}	
	

}
