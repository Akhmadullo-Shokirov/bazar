package uz.bazar.backend.controller.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.bazar.backend.entity.product.ProductLine;
import uz.bazar.backend.entity.product.SubCategory;
import uz.bazar.backend.repository.product.LineOfBusinessRepository;
import uz.bazar.backend.repository.product.ProductLineRepository;
import uz.bazar.backend.repository.product.SubCategoryRepository;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryRestController {

    @Autowired
    LineOfBusinessRepository lineOfBusinessRepository;

    @Autowired
    ProductLineRepository productLineRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @CrossOrigin
    @GetMapping("/all")
    public List<LineOfBusiness> getCategoriesForTesting() {
//        for (LineOfBusiness lineOfBusiness : extractData()) {
//            uz.bazar.backend.entity.product.LineOfBusiness originalLineOfBusiness = new uz.bazar.backend.entity.product.LineOfBusiness(lineOfBusiness.getLineOfBusiness());
//            lineOfBusinessRepository.save(originalLineOfBusiness);
//            List<ProductLine> productLineList = new ArrayList<>();
//            List<SubCategory> subCategoryList = new ArrayList<>();
//            for (ProductLineObject productLineObject : lineOfBusiness.getProductLine()) {
//                ProductLine productLine = new ProductLine(productLineObject.getName());
//                productLineRepository.save(productLine);
//                for (String subcategory : productLineObject.getSubcategories()) {
//                    SubCategory subCategory = new SubCategory(subcategory);
//                    subCategory.setProductLine(productLine);
//                    subCategoryRepository.save(subCategory);
//                    subCategoryList.add(subCategory);
//                }
//                productLine.setSubCategoryList(subCategoryList);
//                productLine.setLineOfBusiness(originalLineOfBusiness);
//                productLineRepository.save(productLine);
//                productLineList.add(productLine);
//            }
//
//            originalLineOfBusiness.setProductLineList(productLineList);
//            lineOfBusinessRepository.save(originalLineOfBusiness);
//
//        }

            return (List) lineOfBusinessRepository.findAll();
        }

        List<LineOfBusiness> extractData() {
            List<LineOfBusiness> values = new ArrayList<>();
            try {
                ObjectMapper mapper = new ObjectMapper();
                values = Arrays.asList(mapper.readValue(Paths.get("/home/dell/projects/E_commerce/backend/src/main/resources/static/data.json").toFile(), LineOfBusiness[].class));

            } catch (Exception e) {
                e.printStackTrace();
            }
            return values;
        }

        // Read data.json file
        //


        //+++++++++++++++++++++++++++++++++++++++++++++
        // SubCategories
//        ProductLine cellPhonesAndAccessoriesProductLine = new ProductLine("Cell phones and Accessories");
//        List<SubCategory> cellPhonesAndAccessories = new ArrayList<>();
//            // Cell phones and Accessories
//            String[] cellPhonesAndAccessoriesNameArray = {"Smartphones", "Smartwatches","Tablets", "GSM Accessories", "Cases, Covers and Holsters"};
//            for(String subCategoryName : cellPhonesAndAccessoriesNameArray){
//                cellPhonesAndAccessories.add(new SubCategory(subCategoryName));
//            }
//        cellPhonesAndAccessoriesProductLine.setSubCategoryList(cellPhonesAndAccessories);
//        List<SubCategory> computers = new ArrayList<>();
//            // Computers
//            String[] computersNameArray = {"Laptops", "Laptop Parts", "PCs", "Computer Components", "Printers and Scanners"};
//            for(String subCategoryName: computersNameArray){
//                computers.add(new SubCategory(subCategoryName));
//            }
//        List<SubCategory> TVsAndAccessories = new ArrayList<>();
//            // TVs and accessories
//            String[] TVsAndAccessoriesNameArray = {"Televisions", "Projectors", "Headphones", "Home Audio", "Home Cinema"};
//            for(String subCategoryName:TVsAndAccessoriesNameArray){
//                TVsAndAccessories.add(new SubCategory(subCategoryName));
//            }
//        List<SubCategory> consolesAndGamingMachines = new ArrayList<>();
//            // Consoles and Gaming Machines
//            String[] consolesAndGamingMachinesNameArray = {"Console PlayStation 5", "Console Xbox Series X/S"};
//            for(String subCategoryName:consolesAndGamingMachinesNameArray){
//                consolesAndGamingMachines.add(new SubCategory(subCategoryName));
//            }
//        List<SubCategory> smallAppliances = new ArrayList<>();
//            // Small Appliances
//            String[] smallApplicancesNameArray = {"Kitchen, cooking", "Hygiene and Care", "Home", "Vacuum Cleaners"};
//            for(String subCategoryName:smallApplicancesNameArray){
//                smallAppliances.add(new SubCategory(subCategoryName));
//            }
//        List<SubCategory> homeAppliances = new ArrayList<>();
//            // Home Appliances
//            String[] homeAppliancesNameArray = {"Refrigerators", "Washing Machines", "Clothes Dryers", "Freestanding Kitchens"};
//            for(String subCategoryName: homeAppliancesNameArray){
//                homeAppliances.add(new SubCategory(subCategoryName));
//            }
//        List<SubCategory> builtinAppliances = new ArrayList<>();
//            // Built-in Applicance
//            String[] builtinAppliancesNameArray = {"Heating Plates", "Built-in ovens", "Built-in dishwashers", "Kitchen hoods"};
//            for(String subCategoryName:builtinAppliancesNameArray){
//                builtinAppliances.add(new SubCategory(subCategoryName));
//            }
//        List<SubCategory> photography = new ArrayList<>();
//            // Photography
//            String[] photographyNameArray = {"Digital cameras", "Lenses", "Photo Accessories", "Instant Cameras(Instax, Polaroid)"};
//            for(String subCategoryName: photographyNameArray){
//                photography.add(new SubCategory(subCategoryName));
//            }
        // Product Lines

        // LineOfBusiness

//        List<LineOfBusiness> lineOfBusinessList= new ArrayList<>();
//        lineOfBusinessList.add(new LineOfBusiness("Electronics"))

}