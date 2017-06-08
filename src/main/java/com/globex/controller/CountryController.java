package com.globex.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globex.model.vo.CountryDO;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.common.ClauseDO;
import com.globex.model.vo.common.RateRequirementDO;
import com.globex.model.vo.common.TaxDO;
import com.globex.repository.rdbms.CountryRepository;
import com.globex.service.CountryService;
import com.globex.constants.AppConstants;
import com.utils.FileUtils;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sunil Golla on 2/22/2017.
 */
@Controller
public class CountryController {

    final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    CountryService countryService;

    @Autowired
    CountryRepository countryRepository;

    @RequestMapping("/secure/viewCountries")
    @ResponseBody
    public Map<String, Object> viewCountries(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                                                  @RequestParam(value = "pageSize", required=false) Integer pageSize,
                                                  @RequestParam(value = "country", required=false) String country){

        pageNo=pageNo==null?0:pageNo;
        pageSize=pageSize==null? AppConstants.DEFAULT_PAGE_SIZE:pageSize;
        PageModel<CountryDO> pageModel=new PageModel<CountryDO>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        if(country!=null){
            Map<String,Object> filters=new HashMap<String, Object>();
            filters.put("country",country);
            pageModel.setFilters(filters);
        }
        PageModel<CountryDO> fileInfoPage=countryService.list(pageModel);
        List<CountryDO> countries=fileInfoPage.getContent();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        model.put("pageSize",pageSize);
        model.put("totalRecords",fileInfoPage.getTotalRecords());
        model.put("countries",countries);
        return model;
    }

    @RequestMapping("/secure/getCountryDetails")
    @ResponseBody
    public CountryDO getCountryDetails(@RequestParam(value = "countryId",required=true) Long countryId){
        CountryDO country= countryService.getCountryDetails(countryId);
        return country;
    }


    @RequestMapping("/secure/saveCountry")
    @ResponseBody
    public String saveCountryDetails(HttpServletRequest request,@ModelAttribute("country")CountryDO countryDO){

        ObjectMapper mapper = new ObjectMapper();
        try {
            Set<TaxDO> taxDOs=mapper.readValue(countryDO.getTaxTypesJsonStr(), new TypeReference<Set<TaxDO>>() {});
            Set<RateRequirementDO> rateRequirementDOs=mapper.readValue(countryDO.getTaxRequirementsJsonStr(), new TypeReference<Set<RateRequirementDO>>() {});
            Set<ClauseDO> clauseDOs=mapper.readValue(countryDO.getClausesJsonStr(), new TypeReference<Set<ClauseDO>>() {});
            countryDO.setTaxes(taxDOs);
            countryDO.setRateRequirements(rateRequirementDOs);
            countryDO.setClauses(clauseDOs);

            String rootPath = request.getServletContext().getRealPath("/");//System.getProperty("catalina.home");
            CommonsMultipartFile fileToUpload=countryDO.getInsuRequiredDocFile();
            if (fileToUpload != null && fileToUpload.getBytes().length > 0) {
                String relativePath = FileUtils.uploadFile(fileToUpload, rootPath);
                countryDO.setInsuRequiredDoc(relativePath);
            }

            CommonsMultipartFile generalAttachmentFile=countryDO.getGeneralAttachmentFile();
            if (generalAttachmentFile != null && generalAttachmentFile.getBytes().length > 0) {
                String relativePath = FileUtils.uploadFile(generalAttachmentFile, rootPath);
                countryDO.setGeneralAttachment(relativePath);
            }

            CommonsMultipartFile clauseAttachment=countryDO.getClauseAttachment();
            if (clauseAttachment != null && clauseAttachment.getBytes().length > 0) {
                String relativePath = FileUtils.uploadFile(clauseAttachment, rootPath);
                for(ClauseDO clauseDO:clauseDOs){//TODO:
                    clauseDO.setClauseAttach(relativePath);
                }
            }

            Long countryId= countryService.saveCountry(countryDO);
        }catch (JsonMappingException e) {
            logger.error("JsonMappingException error",e);
        } catch (Exception e) {
            logger.error("Exception error",e);
        }
        return "success";
    }

    @RequestMapping("/secure/deleteCountry")
    @ResponseBody
    public void deleteCountry(@RequestParam(value = "countryId",required=false) Long countryId){
        countryRepository.delete(countryId);
    }

}