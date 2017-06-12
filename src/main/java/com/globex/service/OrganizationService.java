package com.globex.service;

import com.globex.model.entity.pm.Organization;
import com.globex.model.vo.OrganizationDO;
import com.globex.model.vo.PageModel;

/**
 * Created by Sunil Golla on 5/13/2017.
 */
public interface OrganizationService {

    public PageModel<OrganizationDO> list(PageModel<OrganizationDO> pageModel);

    public OrganizationDO getPMDetails(Long orgId);

    public OrganizationDO getLMDetails();

    public void deletePM(Long orgId);

    public void save(Organization organization);

    public Organization getOrganization(Long orgId);
}
