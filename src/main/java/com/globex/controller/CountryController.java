package com.globex.controller;

import com.globex.model.entity.common.Country;
import com.globex.model.vo.CountryDO;
import com.globex.model.vo.PageModel;
import com.globex.repository.rdbms.CountryRepository;
import com.globex.service.CountryService;
import com.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunil Golla on 2/22/2017.
 */
@Controller
public class CountryController {

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
    public Country getCountryDetails(@RequestParam(value = "countryId",required=true) Long countryId){

        Country country= countryService.getCountryDetails(countryId);
        return country;
    }


    @RequestMapping("/secure/saveCountry")
    @ResponseBody
    public String saveCountryDetails(@ModelAttribute("country")Country country){

        Long countryId= countryService.saveCountry(country);
        return "success";
    }

    @RequestMapping("/secure/deleteCountry")
    @ResponseBody
    public void deleteCountry(@RequestParam(value = "countryId",required=false) Long countryId){
        countryRepository.delete(countryId);
    }

}