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

import com.antawa.model.UserDocument;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Repository
public interface UserDocumentRepository extends JpaRepository<UserDocument, Long> {

	@Modifying(clearAutomatically = true)
	@Query("update UserDocument ud set ud.status=:status, ud.content=:content,ud.formatFile=:formatFile where ud.id=:id")
	@Transactional
	void updateById(@Param("status") String status, @Param("content") String content, @Param("formatFile") String formatFile, @Param("id") Long id);

	@Modifying(clearAutomatically = true)
	@Query("update UserDocument ud set ud.status=:status where ud.id=:id")
	@Transactional
	void updateStatusById(@Param("status") String status, @Param("id") Long id);

	@Modifying(clearAutomatically = true)
	@Query("update UserDocument ud set ud.comment=:comment, ud.status=:status where ud.id=:id")
	@Transactional
	void updateStatusById(@Param("comment") String comment, @Param("status") String status, @Param("id") Long id);

	// @Query("Select UserDocument ud where ud.type =:type and ud.user.uuid =:id")
	UserDocument findOneByDocTypeAndUserUuid(String docType, String uuid);

	@Query("select count(ud.id)>0 from UserDocument ud where ud.docType=:docType and ud.user.uuid =:uuid")
	boolean exist(@Param("docType") String docType, @Param("uuid") String uuid);

	@Query("select ud.id from UserDocument ud where ud.docType=:docType and ud.user.uuid =:uuid")
	Long findIdByTypeAndUuid(@Param("docType") String docType, @Param("uuid") String uuid);

	@Query("select ud.id from UserDocument ud where ud.docType=:docType and ud.user.id =:id")
	Long findIdByTypeAndId(@Param("docType") String docType, @Param("id") Long id);

	List<UserDocument> findByUserIdOrderById(Long id);
	
	@Query("select ud.content from UserDocument ud where ud.id =:id")
	String findDocumentContent(@Param("id") Long id);
	
	@Query("select ud from UserDocument ud where ud.status=:status and ud.user.id =:id order by ud.id")
	List<UserDocument> findByUserIdAndStatus(@Param("status") String status, @Param("id") Long id);

}
