/**
 * 
 */
package com.antawa.model.repository;

import java.util.List;

import org.hibernate.QueryException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antawa.model.EvaluationParameter;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Repository
public interface EvaluationParameterRepository extends JpaRepository<EvaluationParameter, Long> {
	
	/**
	 * 
	 * @param status
	 * @return
	 * @throws QueryException
	 */
	List<EvaluationParameter> findByStatus(String status) throws QueryException;
	
	/**
	 * 
	 * @param code
	 * @return
	 * @throws QueryException
	 */
	EvaluationParameter findByCode(String code) throws QueryException;
}
