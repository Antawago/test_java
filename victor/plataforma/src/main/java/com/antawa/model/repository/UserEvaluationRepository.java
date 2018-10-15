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

import com.antawa.model.UserEvaluation;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Repository
public interface UserEvaluationRepository extends JpaRepository<UserEvaluation, Long> {

	/**
	 * 
	 * @param idUser
	 * @return
	 */
	@Query("SELECT ue FROM UserEvaluation ue INNER JOIN ue.evaluationParameter  WHERE ue.user.id=:idUser order by ue.id")
	List<UserEvaluation> findByUserId(@Param("idUser") Long idUser);
	
	@Query("SELECT ue FROM UserEvaluation ue INNER JOIN ue.evaluationParameter ep WHERE ep.code=:parameterCode and  ue.user.id=:idUser")
	UserEvaluation findByUserIdAndEvaluationParameterCode(@Param("idUser") Long idUser, @Param("parameterCode") String parameterCode);

	@Query("SELECT ue.evaluationParameter.id FROM UserEvaluation ue WHERE ue.user.id=:idUser")
	List<Long> findEvaluationParametersByUserId(@Param("idUser") Long idUser);

	/**
	 * 
	 * @param status
	 * @param id
	 */
	@Modifying(clearAutomatically = true)
	@Query("update UserEvaluation ud set ud.status=:status where ud.id=:id")
	@Transactional
	void updateStatusById(@Param("status") String status, @Param("id") Long id);

}
