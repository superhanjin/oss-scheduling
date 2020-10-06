package oss;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SchedulingRepository extends PagingAndSortingRepository<Scheduling, Long>{

    List<Scheduling> findByOrderId(Long orderId);
}