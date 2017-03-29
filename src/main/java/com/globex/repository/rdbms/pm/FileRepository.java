package com.globex.repository.rdbms.pm;

import com.globex.model.entity.common.File;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Sunil Golla on 2/11/2017.
 */
public interface FileRepository extends PagingAndSortingRepository<File, Long> {


}
