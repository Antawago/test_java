/**
 * 
 */
package com.antawa.model.repository;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.antawa.model.UserInformation;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
	/**
	 * This metodo find the user information about user.
	 * 
	 * @param idUser
	 * @return
	 * @throws ServiceException
	 */
	UserInformation findOneByUserId(Long idUser) throws ServiceException;

	/**
	 * 
	 * @param requiredCar
	 * @param avatar
	 * @param pdfFile
	 * @param idUser
	 */
	@Modifying(clearAutomatically = true)
	@Query("update  UserInformation up set up.requiredCar=:requiredCar, up.avatar=:avatar, up.pdfFile=:pdfFile WHERE up.user.id=:idUser")
	void updateInformatio(@Param("requiredCar") Boolean requiredCar, @Param("avatar") String avatar,
			@Param("pdfFile") String pdfFile, @Param("idUser") Long idUser);

}
