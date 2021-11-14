package com.smartfarmer.dao.interfaces;

import com.smartfarmer.entities.Farmer;

import java.util.List;

public interface FarmerDaoI extends DaoI<Farmer, Long>{
   Farmer add(Farmer farmer);

   List<Farmer> read() ;
   Farmer findUsername(Farmer farmer);
   double getTotalFarmSize();
}
