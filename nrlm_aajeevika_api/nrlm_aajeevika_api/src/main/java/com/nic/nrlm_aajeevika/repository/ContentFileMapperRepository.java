package com.nic.nrlm_aajeevika.repository;

import com.nic.nrlm_aajeevika.entity.Content;
import com.nic.nrlm_aajeevika.entity.ContentFileMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentFileMapperRepository extends JpaRepository<ContentFileMapper, Long> {
    List<ContentFileMapper> findAllByContentIdAndIsDeleted(Integer contentId,String isDeleted);

    public void deleteAllByContentId(Long contentId);
}
