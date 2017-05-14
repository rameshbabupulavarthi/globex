package com.globex.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.globex.constants.Role;
import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import com.globex.model.vo.OrganizationDO;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.UserDO;
import com.globex.model.vo.pm.AccountInfoDO;
import com.globex.model.vo.pm.CoverageAreaDO;
import com.globex.service.OrganizationService;
import com.globex.service.UserService;
import com.utils.AppConstants;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sunil Golla on 5/13/2017.
 */
@Controller
public class RegistrationController {

    final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserService userService;

    @RequestMapping("/secure/listPMs")
    @ResponseBody
    public Map<String, Object> listPMs(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                                  @RequestParam(value = "pageSize", required=false) Integer pageSize){

        pageNo=pageNo==null?0:pageNo;
        pageSize=pageSize==null? AppConstants.DEFAULT_PAGE_SIZE:pageSize;

        PageModel<OrganizationDO> pageModel=new PageModel<OrganizationDO>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);

        User user=userService.getCurrentUser();
        Role roleType=Role.getValue(user.getUserType());
        if(!(Role.ROLE_GLOBEX==roleType || Role.ROLE_GLOBEX_ADMIN==roleType) ){
            Organization organization=user.getOrganization();
            Map<String,Object> filters=new HashMap<String,Object>();
            filters.put("orgId",organization.getId());
            pageModel.setFilters(filters);
        }
        PageModel<OrganizationDO> orgInfoPage=organizationService.list(pageModel);
        List<OrganizationDO> orgList=orgInfoPage.getContent();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        model.put("pageSize",pageSize);
        model.put("totalRecords",orgInfoPage.getTotalRecords());
        model.put("organizations",orgList);
        return model;
    }

    @RequestMapping("/secure/getPMDetails")
    @ResponseBody
    public OrganizationDO getPMDetails(@RequestParam(value = "orgId",required=false) Long orgId){
        OrganizationDO organizationDO=organizationService.getPMDetails(orgId);
        return organizationDO;
    }

    @RequestMapping("/secure/deletePM")
    @ResponseBody
    public void deletePM(@RequestParam(value = "orgId",required=false) Long orgId){
        organizationService.deletePM(orgId);
    }

    @RequestMapping("/secure/savePM")
    @ResponseBody
    public OrganizationDO savePM(HttpServletRequest request,@ModelAttribute("orgData") OrganizationDO organizationDO){

        ObjectMapper mapper = new ObjectMapper();
        try {
            Set<UserDO> userDOs= mapper.readValue(organizationDO.getUserJsonStr(),new TypeReference<Set<UserDO>>() {});
            Set<AccountInfoDO> accountInfoDOs= mapper.readValue(organizationDO.getAccountInfoJsonStr(), new TypeReference<Set<AccountInfoDO>>() {});
            Set<CoverageAreaDO> coverageAreaDOs= mapper.readValue(organizationDO.getCoverageAreaJsonStr(), new TypeReference<Set<CoverageAreaDO>>() {});

            organizationDO.setUsers(userDOs);
            organizationDO.setAccountInfos(accountInfoDOs);
            organizationDO.setCoverageAreas(coverageAreaDOs);
            organizationService.save(organizationDO);
        }catch (JsonMappingException e) {
            logger.error("JsonMappingException error",e);
        } catch (Exception e) {
            logger.error("Exception error",e);
        }
        return organizationDO;
    }


    @RequestMapping("/secure/downloadPMs")
    @ResponseBody
    public void downloadPMs(HttpServletRequest request,HttpServletResponse response,
                             @RequestParam(value = "filePath", required = false) String filePath) throws IOException {

        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet= workbook.createSheet();
        sheet.setDisplayGridlines(false);

        int startRow=1;
        int startCell=1;
        int WIDTH=7500;
        byte[] headerColor={(byte)150,(byte)54,(byte)52};
        byte[] dataColor={(byte)230,(byte)184,(byte)183};

        String [] headers={"Organization","Address","Registered Date","Country","Website"};

        HSSFRow row= sheet.createRow(startRow++);
        int cellNo=startCell;
        for(String header: headers) {
            HSSFCell cell = row.createCell(cellNo);
            cell.setCellStyle(getCellStyle(workbook,headerColor,(short)50));
            cell.setCellValue(header);
            sheet.setColumnWidth(cellNo,WIDTH);
            cellNo++;
        }

        PageModel<OrganizationDO> pageModel=new PageModel<OrganizationDO>();
        pageModel.setPageNo(1);
        pageModel.setPageSize(10);


        int rowNo=startRow;
        while(pageModel.getTotalRecords() ==null || pageModel.getPageNo()*pageModel.getPageSize() <= pageModel.getTotalRecords()) {
            PageModel<OrganizationDO> orgInfoPage=organizationService.list(pageModel);
            List<OrganizationDO> orgList=orgInfoPage.getContent();
            pageModel.setPageNo(pageModel.getPageNo()+1);
            for (OrganizationDO organizationDO : orgList) {
                cellNo = startCell;
                row = sheet.createRow(rowNo++);
                HSSFCell orgNameCell = row.createCell(cellNo++);
                orgNameCell.setCellStyle(getCellStyle(workbook, dataColor, (short) 51));
                orgNameCell.setCellValue(organizationDO.getOrgName());

                HSSFCell addressCell = row.createCell(cellNo++);
                addressCell.setCellStyle(getCellStyle(workbook, dataColor, (short) 51));
                addressCell.setCellValue(organizationDO.getAddress1());

                HSSFCell regDateCell = row.createCell(cellNo++);
                regDateCell.setCellStyle(getCellStyle(workbook, dataColor, (short) 51));
                regDateCell.setCellValue(organizationDO.getRegDate());

                HSSFCell countryCell = row.createCell(cellNo++);
                countryCell.setCellStyle(getCellStyle(workbook, dataColor, (short) 51));
                countryCell.setCellValue(organizationDO.getCountry());

                HSSFCell websiteCell = row.createCell(cellNo++);
                websiteCell.setCellStyle(getCellStyle(workbook, dataColor, (short) 50));
                websiteCell.setCellValue(organizationDO.getWebsite());
            }
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=partners.xls");
        workbook.write(response.getOutputStream());
    }

    private HSSFCellStyle getCellStyle(HSSFWorkbook workbook,byte[] rgb,short colorIndex){

        HSSFCellStyle style=workbook.createCellStyle();
        HSSFPalette palette= workbook.getCustomPalette();
        palette.setColorAtIndex(colorIndex,rgb[0],rgb[1],rgb[2]);
        style.setFillForegroundColor(colorIndex);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        HSSFFont font=workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);
        return style;
    }

}
