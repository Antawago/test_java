/**
 * 
 */
package com.antawa.model.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.antawa.enums.UserTypesEnum;
import com.antawa.model.UserProfile;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	/**
	 * This method list UserProfiles by status and code profile.
	 * 
	 * @param profileCode
	 * @param status
	 * @return
	 */
	@Query("SELECT up FROM UserProfile up INNER JOIN up.user WHERE up.status=:status and up.profile.code=:profileCode")
	List<UserProfile> findByStatusAndProfileCode(@Param("profileCode") String profileCode,
			@Param("status") String status);

	/**
	 * 
	 * @param idUser
	 * @param profileCode
	 * @return
	 */
	@Query("SELECT up FROM  UserProfile up INNER JOIN up.user u INNER JOIN up.profile p WHERE p.code=:profileCode and u.uuid=:uuid")
	UserProfile findOneByUserUuid(@Param("profileCode") String profileCode, @Param("uuid") String uuid);

	/**
	 * This methos update part ofo information about UserProfile:
	 * 
	 * @param type {@link UserTypesEnum}
	 * @param id
	 */
	@Modifying(clearAutomatically = true)
	@Query("update UserProfile up set up.type =:type where up.id =:id")
	@Transactional
	void updateTypeById(@Param("type") String type, @Param("id") Long id);
	
	
	@Modifying(clearAutomatically = true)
	@Query("update UserProfile up set up.status=:status where up.id=:id")
	@Transactional
	void updateStatusById(@Param("status") String status, @Param("id") Long id);

}
