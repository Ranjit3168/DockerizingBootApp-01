package com.bpl.service;

import java.util.List;

import com.bpl.entity.Touriest;
import com.bpl.error.TouriestNotFoundException;

public interface ITouriestMgmtService {
    public String registerTouriest(Touriest touriest);
    public List<Touriest> showTouriest();
    public List<Touriest> showTouriestByBudgetRange(double start,double  end);
    public Touriest showTouriestById(int id) throws TouriestNotFoundException;
    public String modifyTouriest(Touriest touriest);
    public String updateTouriestBudgetById(int id,double hikePercentage) ;
    public String removeTouriestById(int id);
    public String deleteTouriestByPackageType(String type);
}
