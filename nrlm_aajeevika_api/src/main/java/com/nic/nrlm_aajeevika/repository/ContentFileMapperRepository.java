package com.nic.nrlm_aajeevika.repository;

import com.nic.nrlm_aajeevika.entity.Content;
import com.nic.nrlm_aajeevika.entity.ContentFileMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentFileMapperRepository extends JpaRepository<ContentFileMapper, Long>, JpaSpecificationExecutor<ContentFileMapper> {
    List<ContentFileMapper> findAllByContentIdAndIsDeleted(Integer contentId,String isDeleted);

    @Query(nativeQuery = true, value = "Select distinct(file_type) as file_type from content_file_mapper ")
    List<Object[]> findByFileType();

    public void deleteAllByContentId(Long contentId);
}
