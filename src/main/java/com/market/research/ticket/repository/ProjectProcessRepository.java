package com.market.research.ticket.repository;

import com.market.research.ticket.entity.ProjectProcess;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectProcessRepository extends JpaRepository<ProjectProcess, Long> {
    
    Optional<ProjectProcess> findByProjectName(String projectName);

    @Transactional
    void deleteByProjectName(String projectName);

    @Transactional
    ProjectProcess updateByProjectName(String projectName, ProjectProcess projectProcess);

    boolean existsByProjectName(String projectName);

    @Query(value = "SELECT pp.* FROM project_process pp " +
           "JOIN industrial_party ip ON ip.ticket_name = :ticketName " +
           "WHERE pp.first_level LIKE CONCAT('%', ip.first_level, '%') " +
           "   OR ip.first_level LIKE CONCAT('%', pp.first_level, '%')", 
           nativeQuery = true)
    List<ProjectProcess> findMatchingByFirstLevel(@Param("ticketName") String ticketName);

    @Query(value = "SELECT pp.* FROM project_process pp " +
           "JOIN industrial_party ip ON ip.ticket_name = :ticketName " +
           "WHERE pp.first_level LIKE CONCAT('%', ip.third_level, '%') " +
           "   OR ip.third_level LIKE CONCAT('%', pp.first_level, '%') " +
           "   OR pp.sec_level LIKE CONCAT('%', ip.third_level, '%') " +
           "   OR ip.third_level LIKE CONCAT('%', pp.sec_level, '%')", 
           nativeQuery = true)
    List<ProjectProcess> findMatchingByThirdLevel(@Param("ticketName") String ticketName);
    
}