//package com.home.SpringBootAutomation.repository;
//
//import com.home.SpringBootAutomation.Model.DrivingLicenceModel;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//
//import java.time.LocalDate;
//import java.util.List;
//
//
//public interface DrivingLicenceRepository extends JpaRepository<DrivingLicenceModel, Long> {
//
//    @Query("SELECT oo from DrivingLicenceEntity oo where oo.issuanceDate=:issuaceDate")
//    List<DrivingLicenceModel> findByDate(LocalDate issuanceDate);
//
//    @Query("SELECT oo from DrivingLicenceEntity oo where oo.serialNumber=:serialNumber")
//    List<DrivingLicenceModel> findBySerialNumber(String serialNumber );
//
//    //    @Modifying
////    @Query("update DrivingLicenceEntity  oo set oo.LicenseSuspension=true where oo.id=:id")
////    List<DrivingLicenceModel> LicenseSuspension(Long id);
//
//    //   List<DrivingLicenceModel> findByName(String name);
//
////   List<DrivingLicenceModel> findByNameAndFamily(String name , String family);
////
////   List<DrivingLicenceModel> findByLastname(String lastname);
//
////    @Query("select p.name from DrivingLicenceEntity  p where p.id  in :id")
////    List<String> findAllNamesById(@Param("id") ArrayList<Long> id);
//
////    List<DrivingLicenceModel>findAllByNameAndLicenseSuspension(String name,boolean LicenseSuspension);
//
//
//}
