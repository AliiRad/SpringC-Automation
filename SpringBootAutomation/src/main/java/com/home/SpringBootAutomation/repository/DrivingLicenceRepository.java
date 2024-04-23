//package com.home.SpringBootAutomation.repository;
//
//import com.home.SpringBootAutomation.model.DrivingLicence;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//
//import java.time.LocalDate;
//import java.util.List;
//
//
//public interface DrivingLicenceRepository extends JpaRepository<DrivingLicence, Long> {
//
//    @Query("SELECT oo from DrivingLicenceEntity oo where oo.issuanceDate=:issuaceDate")
//    List<DrivingLicence> findByDate(LocalDate issuanceDate);
//
//    @Query("SELECT oo from DrivingLicenceEntity oo where oo.serialNumber=:serialNumber")
//    List<DrivingLicence> findBySerialNumber(String serialNumber );
//
//    //    @Modifying
////    @Query("update DrivingLicenceEntity  oo set oo.LicenseSuspension=true where oo.id=:id")
////    List<DrivingLicence> LicenseSuspension(Long id);
//
//    //   List<DrivingLicence> findByName(String name);
//
////   List<DrivingLicence> findByNameAndFamily(String name , String family);
////
////   List<DrivingLicence> findByLastname(String lastname);
//
////    @Query("select p.name from DrivingLicenceEntity  p where p.id  in :id")
////    List<String> findAllNamesById(@Param("id") ArrayList<Long> id);
//
////    List<DrivingLicence>findAllByNameAndLicenseSuspension(String name,boolean LicenseSuspension);
//
//
//}
